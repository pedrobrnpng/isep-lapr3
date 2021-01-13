/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Path;
import lapr.project.model.PathRegistry;

/**
 *
 * @author Utilizador
 */
public class AddPathController {

    private Path path;
    private PathRegistry pathRegistry;

    /**
     * Constructor
     */
    public AddPathController() {
        this.pathRegistry = new PathRegistry();
    }

    /**
     * Sets the path registry
     * @param pr 
     */
    public void setPathRegistry(PathRegistry pr) {
        this.pathRegistry = pr;
    }

    /**
     * Creates a new path
     * 
     * @param latitudeO - origin place latitude
     * @param longitudeO - origin place longitude
     * @param latitudeD - destination place latitude
     * @param longitudeD - destination place longitude
     * @param kineticCoefficient - kinetic coefficient
     * @param windDirection - wind direction
     * @param windSpeed - wind speed
     */
    public void newPath(double latitudeO, double longitudeO, double latitudeD, double longitudeD, double kineticCoefficient, double windDirection, double windSpeed) {
        int p1 = pathRegistry.getPlace(latitudeO, longitudeO);
        int p2 = pathRegistry.getPlace(latitudeD, longitudeD);
        if (p1 != -1 || p2 != -1) {
            path = pathRegistry.newPath(p1, p2, kineticCoefficient, windSpeed, windSpeed);
        }
    }

    /**
     * Adds a path to the database
     * @return boolean whether the path was added or not
     */
    public boolean addPath() {
        if (path == null) {
            return false;
        }
        return pathRegistry.addPath(path);
    }
    
    /**
     * Adds several paths to the database
     * @return number of paths added
     */
    public int addAllPaths() {
        return pathRegistry.addAllPaths();
    }
    
    
}
