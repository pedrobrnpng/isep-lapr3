/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;

/**
 *
 * @author Utilizador
 */
public class RemoveParksController {

    ParkDB parkDB;
    Park park;
    
    /**
     * Sets the parkDB
     * 
     * @param parkDB 
     */
    public void setParkDB(ParkDB parkDB) {
        this.parkDB=parkDB;
    }

    /**
     * Contructor
     */
    public RemoveParksController() {
        parkDB = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     * Gets the park that is going to be erased
     * 
     * @param identification 
     */
    public void getPark(String identification) {
        park = parkDB.getPark(identification);
        
    }

    /**
     * Erases the park
     * 
     * @return boolean
     */
    public boolean deactivatePark() {
        if (park != null) {
            return parkDB.deactivatePark(park);
        }
        return false;
    }
}
