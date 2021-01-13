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
public class ChangeParksController {

    ParkDB parkDB;
    ParkRegistry parkRegistry;
    Park park;
    boolean updated;

    /**
     * Sets the parkDB
     *
     * @param parkDB
     */
    public void setParkDB(ParkDB parkDB) {
        this.parkDB = parkDB;
    }

    /**
     * Constructor
     */
    public ChangeParksController() {
        parkDB = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        parkRegistry = new ParkRegistry();
    }

    /**
     * Gets the park that's going to be changed
     *
     * @param identification
     */
    public void getPark(int identification) {
        park = parkDB.getPark(identification);
    }

    /**
     * Updates the information relative to the park
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
    public void updateInformation(String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        updated = parkRegistry.updateInformation(park, identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
    }

    /**
     * Saves the new information in the database
     *
     * @return boolean
     */
    public boolean updatePark() {
        if (updated) {
            return parkDB.updatePark(park);
        }
        return false;
    }
}
