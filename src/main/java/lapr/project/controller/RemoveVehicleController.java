/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.VehiclesRegistry;

/**
 *
 * @author bruno
 */
public class RemoveVehicleController {
    
    private final VehiclesRegistry vr;

    /**
     *
     */
    public RemoveVehicleController() {
        vr = new VehiclesRegistry();
    }
    
    /**
     *
     * @param vdb
     */
    public RemoveVehicleController(VehicleDB vdb) {
        vr = new VehiclesRegistry();
        vr.setVehicleDB(vdb);
    }
    
    /**
     *
     * @param idVehicle
     * @return
     */
    public boolean removeVehicle(int idVehicle){
        try {
            vr.removeVehicle(idVehicle);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    
}
