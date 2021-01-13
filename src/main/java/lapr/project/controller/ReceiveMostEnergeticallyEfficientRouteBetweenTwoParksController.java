/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.BicycleDB;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.PathDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
import lapr.project.model.RoutesPerVehicle;
import lapr.project.model.Vehicle;
import lapr.project.model.VehiclesRegistry;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author bruno
 */
public class ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController {
    
    private final POIRegistry poir;
    private final ParkRegistry pkr;
    private final PathRegistry pr;
    private final VehiclesRegistry vr;
    private final ClientRegistry cr;

    /**
     *
     */
    public ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController() {
        this.poir = new POIRegistry();
        this.pkr = new ParkRegistry();
        this.pr = new PathRegistry();
        this.vr = new VehiclesRegistry();
        this.cr = new ClientRegistry();
    }

    /**
     *
     * @param poidb
     * @param pdb
     * @param pkdb
     * @param bdb
     * @param sdb
     * @param cdb
     */
    public ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController(POIDB poidb, PathDB pdb, ParkDB pkdb, BicycleDB bdb, ScooterDB sdb, ClientDB cdb, VehicleDB vdb) {
        this.poir = new POIRegistry();
        poir.setPOIDB(poidb);
        this.pkr = new ParkRegistry();
        pkr.setParkDB(pkdb);
        this.pr = new PathRegistry();
        pr.setPathDB(pdb);
        this.vr = new VehiclesRegistry();
        vr.setBicycleDB(bdb);
        vr.setScooterDB(sdb);
        vr.setVehicleDB(vdb);
        this.cr = new ClientRegistry();
        cr.setClientDB(cdb);
        
    }
    
    /**
     *
     * @param username
     * @param beginPark
     * @param endPark
     * @return
     */
    public List<RoutesPerVehicle> receiveMostEnergeticallyRouteBetweenTwoParks(String username, int beginPark, int endPark){
        try {
            Client c = cr.getClient(username);
            Park origin = pkr.getPark(beginPark);
            Park destination = pkr.getPark(endPark);
            Set<Vehicle> vehiclesList = vr.getVehicleInPark(beginPark);
            Set<Path> pathList = pr.getAllPaths();
            Set<Place> placeList = new HashSet<>();
            placeList.addAll(poir.getExistingPOI());
            placeList.addAll(pkr.getAllParks());
            return pr.getEnergyRoutes(placeList, pathList, vehiclesList, c, origin, destination);
        } catch (InvalidInfoClientException | SQLException ex) {
            Logger.getLogger(ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController.class.getName()).log(Level.SEVERE, null, ex);
            return new LinkedList<>(); 
        }
    }
    
    /**
     *
     * @param route
     * @return
     */
    public double getRouteDistance(LinkedList<Place> route){
        return pr.getRouteDistance(route);
    }
    
    public void getRoutesOfTypeVehicle(String type, String vehicleSpecs, List<RoutesPerVehicle> routes){
        pr.routesForTypeVehicle(type, vehicleSpecs, routes);
        pr.getLeastWeightRoutes(routes);
        pr.orderRoutes(routes);
    }
    
    public double getElevation(RoutesPerVehicle rpv){
        return pr.calculateElevation(rpv.getRoutes());
    }
    
    
    
    
}
