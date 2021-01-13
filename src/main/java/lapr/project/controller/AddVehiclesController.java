/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.VehiclesRegistry;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Park;

/**
 *
 * @author bruno
 */
public class AddVehiclesController {

    private final VehiclesRegistry vr;
    private final ParkRegistry pr;

    /**
     *
     */
    public AddVehiclesController() {
        vr = new VehiclesRegistry();
        pr = new ParkRegistry();
    }

    /**
     *
     * @param pdb
     * @param sdb
     * @param bdb
     */
    public AddVehiclesController(ParkDB pdb, ScooterDB sdb, BicycleDB bdb) {
        vr = new VehiclesRegistry();
        pr = new ParkRegistry();
        vr.setBicycleDB(bdb);
        vr.setScooterDB(sdb);
        pr.setParkDB(pdb);
    }
    
    public void setParkRegistry(ParkRegistry pr) {
        this.pr.setParkList(pr.getParks());
    }

    /**
     *
     * @return
     */
    public Set<Park> getAllParks() {
        return pr.getAllParks();
    }

    /**
     *
     * @param wheelSize
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @return
     */
    public boolean addBicycle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) {
        try {
            return vr.addBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean registerBicycles(){
         try {
            vr.registerBicycles();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param type
     * @param maxBatteryCapacity
     * @param actualBatteryCapacity
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @param motor
     * @return
     */
    public boolean addScooter(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) {
        try {
            vr.addScooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
            return true;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean registerScooters(){
         try {
            vr.registerScooters();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param parkLatitude
     * @param parkLongitude
     * @return
     */
    public int getParkByCoordinates(double parkLatitude, double parkLongitude) {
        return pr.getParkByCoordinates(parkLatitude, parkLongitude).getId();
    }
}
