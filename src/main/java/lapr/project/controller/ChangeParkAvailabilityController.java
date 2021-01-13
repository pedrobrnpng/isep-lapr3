/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.BicycleDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Park;
import lapr.project.model.Vehicle;
import lapr.project.model.VehiclesRegistry;

/**
 *
 * @author Simao
 */
public class ChangeParkAvailabilityController {

    Set<Vehicle> vehicleList;
    Park park;
    private final VehiclesRegistry vr;

    /**
     *
     */
    public ChangeParkAvailabilityController() {
        vr = new VehiclesRegistry();
        VehicleDB vdb = new VehicleDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        vr.setVehicleDB(vdb);
    }

    /**
     *
     * @param vdb
     */
    public ChangeParkAvailabilityController(VehicleDB vdb) {
        vr = new VehiclesRegistry();
        vr.setVehicleDB(vdb);
    }
    
    /**
     *
     * @return
     */
    public Set<Vehicle> getExistingVehicles() {
        try {
            vehicleList = vr.getAllVehicles();
            return vehicleList;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return new HashSet<>();
        }
    }

    /**
     *
     * @param id_vehicle
     * @return
     */
    public boolean validateVehicle(int id_vehicle) {
        Set<Vehicle> vehicles = getExistingVehicles();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getID() == id_vehicle) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param idVehicle
     */
    public boolean updateAvailability(int idVehicle) {
        try {
            if (validateVehicle(idVehicle)) {
                vr.updateAvailability(idVehicle);
                return true;
            }
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(AddVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
