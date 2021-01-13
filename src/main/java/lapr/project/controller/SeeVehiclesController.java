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
import lapr.project.data.VehicleDB;
import lapr.project.model.Vehicle;
import lapr.project.model.VehiclesRegistry;

/**
 *
 * @author bruno
 */
public class SeeVehiclesController {
    
    private final VehiclesRegistry vr;

    /**
     *
     */
    public SeeVehiclesController() {
        this.vr = new VehiclesRegistry();
    }

    /**
     *
     * @param vdb
     */
    public SeeVehiclesController(VehicleDB vdb) {
        this.vr = new VehiclesRegistry();
        vr.setVehicleDB(vdb);
    }
    
    /**
     *
     * @return
     */
    public Set<Vehicle> getAllVehicles(){
        try {
            return vr.getAllVehicles();
        } catch (SQLException ex) {
            Logger.getLogger(SeeVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return new HashSet<>();
        }
    }
    
    
    
}
