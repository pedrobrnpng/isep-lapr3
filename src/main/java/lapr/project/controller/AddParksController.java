/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;

/**
 *
 * @author Utilizador
 */
public class AddParksController {
    
    private final ParkRegistry parkRegistry;
    private Park park;
    private ParkDB parkDB;
    
    /**
     * Sets the parkDB
     * @param parkDB 
     */
    public void setParkDB(ParkDB parkDB) {
        this.parkDB=parkDB;
    }
    
    /**
     * Gets the park registry
     * @return park registry
     */
    public ParkRegistry getParkRegistry() {
        return this.parkRegistry;
    }
    
    /**
     * Gets the park
     * @return park
     */
    public Park getPark() {
        return park;
    }

    /**
     * Constructor
     */
    public AddParksController() {
        parkDB = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        parkRegistry = new ParkRegistry();
    }

    /**
     * Creates a new park
     * 
     * @param identification
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param maxNumberBicycles
     * @param maxNumberScooter
     * @param inputVoltage
     * @param inputCurrent 
     */
    public void newPark(String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        park = parkRegistry.newPark(identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
    }

    /**
     * Adds a park to the database
     * 
     * @return boolean
     */
    public boolean addParks() {
        return parkDB.addPark(park);
    }
    
    /**
     * Adds several parks to the database
     * @return number of parks added
     */
    public int addAllParks() {
        return parkRegistry.addAllParks();
    }

}
