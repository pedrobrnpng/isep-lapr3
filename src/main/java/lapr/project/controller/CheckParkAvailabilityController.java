/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.Instant;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleRequest;

/**
 *
 * @author pedro
 */
public class CheckParkAvailabilityController {
    
    private static final String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String USERDB = "LAPR3_2019_G022";
    private static final String PASSDB = "qwerty";

    private BicycleDB bicycleDB;
    private ScooterDB scooterDB;
    private ParkDB parkDB;
    private VehicleRequestsDB vReqDB;
    private VehicleDB vDB;
    private Instant now = Instant.now();
    private boolean availabilty;

    /**
     *
     */
    public CheckParkAvailabilityController() {
        this.bicycleDB = new BicycleDB(URLDB, USERDB, PASSDB);
        this.scooterDB = new ScooterDB(URLDB, USERDB, PASSDB);
        this.parkDB = new ParkDB(URLDB, USERDB, PASSDB);
        this.vReqDB = new VehicleRequestsDB(URLDB, USERDB, PASSDB);
        this.availabilty = false;
    }

    public void setDB(ScooterDB s, BicycleDB b, ParkDB p, VehicleRequestsDB v, VehicleDB vDB, Instant now) {
        this.bicycleDB = b;
        this.scooterDB = s;
        this.parkDB = p;
        this.vReqDB = v;
        this.vDB = vDB;
        this.now = now;
    }

    /**
     * returns the free slots in a given park
     * @param park
     * @param user
     * @return 
     */
    public int getFreeSlots(String park, String user) {
        int b = 0;
        int s = 0;
        Set<Vehicle> vehicleList;
        Park p = parkDB.getPark(park);

        vehicleList = bicycleDB.getBicycles(p);
        vehicleList.addAll(scooterDB.getScooters(p));

        Set<VehicleRequest> vReq = vReqDB.getActiveRequestsGivenTime(now.toString());
        
        VehicleRequest vRequest = null;
        for(VehicleRequest v : vReq) {
            if(v.getUserEmail() == user) 
                vRequest = v;
        }
        
        Vehicle veh = null;
         for(Vehicle v1 : vehicleList){
            if(v1.getID() == vRequest.getVehicleID())
                veh = v1;
        }
         
        if (veh instanceof Bicycle) {
            for (Vehicle v : vehicleList) {
                if (v instanceof Bicycle) {
                    ++b;
                }
            }
            if (b < p.getMaxNumberBicycles()) {
                return Math.abs(b - p.getMaxNumberBicycles());
            }
        } else {
            for (Vehicle v : vehicleList) {
                if (v instanceof Scooter) {
                    ++s;
                }
            }
            if (s < p.getMaxNumberScooter()) {
                return Math.abs(s - p.getMaxNumberScooter());
            }
        }
        return 0;
    }

}
