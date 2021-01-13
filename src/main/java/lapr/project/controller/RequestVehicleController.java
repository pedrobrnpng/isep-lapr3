/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Client;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.Place;
import lapr.project.model.Scooter;

import lapr.project.model.VehicleRequest;
import lapr.project.model.Vehicle;
import lapr.project.model.VehiclesRegistry;
import lapr.project.utils.CalculateAutonomy;

import lapr.project.utils.exceptions.InvalidInfoClientException;
/**
 *
 * @author pedro
 */
public class RequestVehicleController {
    
    private final String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private final String USERDB = "LAPR3_2019_G022";
    private final String PASSDB = "qwerty";
    
    private ParkDB parkDB ;
    private VehicleDB vehicleDB;
    private VehicleRequestsDB vehicleRequestDB ;
    private POIDB poiDB;
    private ClientDB cliDB ;
    private ScooterDB scooterDB;
   
    
    private ParkRegistry parkRegistry ;     

    private Client cli ;
    private Park parkOrigin ;
    private Park parkDestination ;
    private final CalculateAutonomy energy;
    private ShortestPathController spc;


    /**
     *
     */
    public RequestVehicleController() {
        vehicleDB = new VehicleDB(URLDB,USERDB,PASSDB);
        vehicleRequestDB = new VehicleRequestsDB(URLDB,USERDB,PASSDB);
        poiDB = new POIDB(URLDB,USERDB,PASSDB);
        parkRegistry = new ParkRegistry();
        this.spc = new ShortestPathController();
        energy = new CalculateAutonomy();
    }
    
    /**
     * Sets the DB for testing purposes
     * @param v
     * @param vr 
     * @param scooterDB 
     * @param spc 
     * @param cliDB 
     */
    public void setDB(VehicleDB v, VehicleRequestsDB vr, ScooterDB scooterDB, ShortestPathController spc, ClientDB cliDB, ParkDB pdb) {
        this.vehicleDB = v;
        this.vehicleRequestDB = vr;
        this.scooterDB = scooterDB;
        this.cliDB = cliDB;
        this.spc = spc;
        this.parkDB = pdb;
    }
    
    /**
     * Sets Registries for testing purposes
     * @param pr
     */
    public void setRegistry(ParkRegistry pr) {
        this.parkRegistry = pr;
    }
    
    /**
     * get park by id
     * @param idPark
     * @return 
     */
    public Park getPark(int idPark) {
        return parkDB.getPark(idPark);
    }
   
    /**
     * gets the available vehicles in the park, according to the energy needed to get to a given destination
     * @param parkID    id of the park
     * @param latitude latitude od desetination
     * @param longitude longitude of destination
     * @return 
     * @throws java.sql.SQLException 
     */
    public Set<Scooter> getAvailableScooter(String parkID, double latitude, double longitude) throws SQLException {
        Set<Scooter> scooterList = new HashSet<>();
        if (parkRegistry.validatePark(parkID)) {
            parkOrigin = parkRegistry.getParkDB(parkID);
             if (parkRegistry.validateCoordinates(latitude, longitude)) {
                parkDestination = parkRegistry.getParkByCoordinates(latitude, longitude);
                spc.createShortestPath(parkOrigin.getLatitude(), parkOrigin.getLongitude(), latitude, longitude, new HashSet<>(), new ArrayList<>());
        
                double distance = spc.pathDistanceTO(parkOrigin.getLatitude(), parkOrigin.getLongitude(), latitude, longitude, new LinkedList<>());
                scooterList = scooterDB.getScootersByParkID(parkOrigin.getId());
                Iterator<Scooter> iterator = scooterList.iterator();
                while (iterator.hasNext()) {
                    Scooter v = iterator.next();
                    double autDist = energy.calculateEnergy(v);
                    if (distance > autDist) {
                        iterator.remove();
                    }
                }

            }
        }
        return scooterList;
    }
    
    /**
     * gets the available vehicles in the park, according to the energy needed to get to a given destination
     * @param parkID    id of the park
     * @param user
     * @param latitude latitude od desetination
     * @param longitude longitude of destination
     * @return 
     * @throws java.sql.SQLException 
     * @throws lapr.project.utils.exceptions.InvalidInfoClientException 
     */
    public Scooter unlockAScooterAtParkForDestination(String parkID, String user, double latitude, double longitude) throws SQLException, InvalidInfoClientException {
        Set<Scooter> scooterList = new HashSet<>();
        if (parkRegistry.validatePark(parkID)) {
             parkOrigin = parkRegistry.getParkDB(parkID);
             if (parkRegistry.validateCoordinates(latitude, longitude)) {
                parkDestination = parkRegistry.getParkByCoordinates(latitude, longitude);
                spc.createShortestPath(parkOrigin.getLatitude(), parkOrigin.getLongitude(), latitude, longitude, new HashSet<>(), new ArrayList<>());
                double distance = spc.pathDistanceTO(parkOrigin.getLatitude(), parkOrigin.getLongitude(), latitude, longitude, new LinkedList<>());
                scooterList = scooterDB.getScootersByParkID(parkOrigin.getId());
                Iterator<Scooter> iterator = scooterList.iterator();
                while (iterator.hasNext()) {
                    Scooter v = iterator.next();
                    double autDist = energy.calculateEnergy(v);
                    if (distance > autDist) {
                        iterator.remove();
                    }
                }
            }
        }
        

        List<Scooter> scooters = new ArrayList<>(); 
        scooters.addAll(scooterList);
        
        return unlockVehicle(scooters.get(0).getID(), user);
    }
    
    /**
     * gets the available vehicles in the park, according to the energy needed to get to a given destination
     * @param parkID    id of the park
     * @param user
     * @return
     * @throws java.sql.SQLException
     * @throws lapr.project.utils.exceptions.InvalidInfoClientException
     */
    public Scooter getAvailableVehicleMost(String parkID, String user) throws SQLException, InvalidInfoClientException {
        
        Scooter s = new Scooter("city", 1, 0, 3, 1, 1, 1, 1, 1);
        if (parkRegistry.validatePark(parkID)) {
            parkOrigin = parkRegistry.getParkDB(parkID);

            Set<Scooter> scooterList = new HashSet<>();
            scooterList = scooterDB.getScootersByParkID(parkOrigin.getId());
            Iterator<Scooter> iterator = scooterList.iterator();

            while (iterator.hasNext()) {
                Scooter v = iterator.next();
                if (v.getActualBatteryCapacity() > s.getActualBatteryCapacity()) {
                    s = v;
                }
            }

        }
        return unlockVehicle(s.getID(), user);
    }

    /**
     * Unlocks the vehicle with the ID received by parameter
     *
     * @param vehicleID
     * @param user
     * @return
     * @throws lapr.project.utils.exceptions.InvalidInfoClientException
     * @throws java.sql.SQLException
     */
    public Scooter unlockVehicle(int vehicleID, String user) throws InvalidInfoClientException, SQLException {
        Scooter scooter = null;
        this.cli = cliDB.getClient(user);
        if (vehicleDB.unlockVehicle(vehicleID)) {

            for (Scooter s : scooterDB.getAllAvailableScooters()) {
                if (s.getID() == vehicleID) {
                    scooter = s;
                }
            }
            VehicleRequest vReq = new VehicleRequest(0, cli.getEmail(), vehicleID, scooter.getIDPark(), 0, "", "");

            vehicleRequestDB.addRequest(vReq);
        }
        return scooter;
    }

}
