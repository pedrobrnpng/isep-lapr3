/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.BicycleDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.VehiclesRegistry;

/**
 *
 * @author bruno
 */
public class ChangeVehicleController {

    private final VehiclesRegistry vr;

    /**
     *
     */
    public ChangeVehicleController() {
        vr = new VehiclesRegistry();
    }
    
    /**
     *
     * @param sdb
     * @param bdb
     */
    public ChangeVehicleController(ScooterDB sdb, BicycleDB bdb) {
        vr = new VehiclesRegistry();
        vr.setBicycleDB(bdb);
        vr.setScooterDB(sdb);
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
    public boolean changeVehicle(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) {
        try {
            vr.changeVehicle(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ChangeVehicleController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
    public boolean changeVehicle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea){
        try {
            vr.changeVehicle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ChangeVehicleController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
