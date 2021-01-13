/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;

/**
 *
 * @author Utilizador
 */
public class SeeParksController {
    
    ParkDB parkDB;
    
    /**
     * ´Constructor
     */
    public SeeParksController() {
        this.parkDB=new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }
    
    /**
     * Sets de parkDB
     * @param parkDB 
     */
    public void setParkDB(ParkDB parkDB) {
        this.parkDB=parkDB;
    }
    
    /**
     * Gets the existing parks from the database
     * 
     * @return set of parks
     */
    public Set<Park> getExistingParks() {
        return parkDB.getExistingParks();
    }
}
