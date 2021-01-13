/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ClientDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Park;
import lapr.project.model.VehicleRequest;
import lapr.project.model.Vehicle;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author pedro
 */
public class EndUseVehicleController {

    private static final String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String USERDB = "LAPR3_2019_G022";
    private static final String PASSDB = "qwerty";

    private ManagePointsController manPointsController;

    private VehicleDB vehicleDB;
    private BicycleDB bikeDB;
    private ScooterDB scooDB;
    private ParkDB parkDB;
    private VehicleRequestsDB vehicleRequestDB;

    private VehicleRequest vRequest;
    private Vehicle vehicle;
    private Park park;
    private Instant instant;


    /**
     * constructor
     */
    public EndUseVehicleController() {
        this.parkDB = new ParkDB(URLDB, USERDB, PASSDB);
        this.vehicleDB = new VehicleDB(URLDB, USERDB, PASSDB);
        this.vehicleRequestDB = new VehicleRequestsDB(URLDB, USERDB, PASSDB);
        this.bikeDB = new BicycleDB(URLDB, USERDB, PASSDB);
        this.scooDB = new ScooterDB(URLDB, USERDB, PASSDB);
        this.manPointsController = new ManagePointsController();
    }
    
    /**
     * testing purposes
     * @param v
     * @param vehicle
     * @param p
     * @param s
     * @param b
     * @param mpc
     * @param inst 
     */
    public void setDB(VehicleRequestsDB v, VehicleDB vehicle, ParkDB p, ScooterDB s, BicycleDB b, ManagePointsController mpc, Instant inst) {
        this.vehicleDB = vehicle;
        this.vehicleRequestDB = v;
        this.parkDB = p;
        this.scooDB = s;
        this.bikeDB = b;
        this.manPointsController = mpc;
        this.instant = inst;
    }

    /**
     * ends a trip with the id of park
     * @param vehicleID
     * @param parkID
     * @param userEmail
     * @throws lapr.project.utils.exceptions.InvalidInfoClientException
     */
    public void newEndUse(int vehicleID, String parkID, String userEmail) throws InvalidInfoClientException {
        Set<Vehicle> list = new HashSet<>();
        this.park = parkDB.getPark(parkID);
        list.addAll(bikeDB.getBicycles(park));
        list.addAll(scooDB.getScooters(park));
        for (Vehicle v : list) {
            if (vehicleID == v.getID()) {
                this.vehicle = v;
            } 
        }
        
        this.vRequest = vehicleRequestDB.getVReqByEmailAndVeh(vehicleID, userEmail);
    }

    /**
     * ends a trip with the location
     * @param vehicleID
     * @param latitude
     * @param longitude
     * @param userEmail
     */
    public void newEndUseLocation(int vehicleID, double latitude, double longitude, String userEmail) throws InvalidInfoClientException {
        Set<Vehicle> list = new HashSet<>();
        this.park = parkDB.getParkByCoordinates(latitude, longitude);
        list.addAll(bikeDB.getBicyclesByParkID(park.getId()));
        list.addAll(scooDB.getScootersByParkID(park.getId()));
        for (Vehicle v : list) {
            if (vehicleID == v.getID()) {
                this.vehicle = v;
            } 
        }
        this.vRequest = vehicleRequestDB.getVReqByEmailAndVeh(vehicleID, userEmail);

    }

    /**
     * sets the destination park in the vehicle to know where it has been
     * parked, and also registers the end time
     *
     * @return
     * @throws lapr.project.utils.exceptions.InvalidInfoClientException
     * @throws java.sql.SQLException
     */
    public long endUse() throws InvalidInfoClientException, SQLException {
        if(vehicle == null)
            return 0;
        
        vRequest.setEndTime(instant.toString()); 
        vehicleRequestDB.updateFinish(vRequest.getVehicleRequestID(), park.getId(), instant.toString());
        vehicleDB.parkVehicle(vehicle.getID(), park.getId());
        manPointsController.checkParking(vRequest.getVehicleRequestID());
        return instant.toEpochMilli();

    }

}
