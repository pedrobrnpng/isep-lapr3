/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.VehicleRequestRegistry;
import lapr.project.model.VehiclesHistory;

/**
 *
 * @author bruno
 */
public class RequestLockingUnlockingHistoryVehiclesController {
    private final VehicleRequestRegistry vrr;

    /**
     *
     * @param vrdb
     */
    public RequestLockingUnlockingHistoryVehiclesController(VehicleRequestsDB vrdb) {
        this.vrr = new VehicleRequestRegistry(vrdb);
    }
    
    /**
     *
     * @param userEmail
     * @return
     */
    public VehiclesHistory requestLockingUnlockingHistoryVehicles(String userEmail){
        try {
            return vrr.requestLockingUnlockingHistoryVehicles(userEmail);
        } catch (SQLException ex) {
            Logger.getLogger(RequestLockingUnlockingHistoryVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
}
