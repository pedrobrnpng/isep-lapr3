/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.POI;
import lapr.project.model.POIRegistry;

/**
 *
 * @author Utilizador
 */
public class LoadPOIController {

    private final POIRegistry poiRegistry;
    private POI poi;

    /**
     * Gets the registry of poi
     * 
     * @return poi registry
     */
    public POIRegistry getPOIRegistry() {
        return this.poiRegistry;
    }
    
    /**
     * Returns the poi
     * @return poi
     */
    public POI getPOI()
    {
        return poi;
    }
    /**
     * Constructor
     */
    public LoadPOIController() {
        this.poiRegistry = new POIRegistry();
    }

    /**
     * Creates a new POI
     * 
     * @param latitude - latitude of the POI
     * @param longitude - longitude of the POI
     * @param elevation - elevation of the POI
     * @param description - description of the POI
     */
    public void newPOI(double latitude, double longitude, double elevation, String description) {
        poi = poiRegistry.newPOI(latitude, longitude, elevation, description);
    }

    /**
     * Adds a POi to the database
     * @return boolean that indicates if  the poi was added
     */
    public boolean addPOI() {
        return poiRegistry.addPoi(poi);
    }

    /**
     * Adds several POI to the database
     * @return number of POI added
     */
    public int addPOIlist() {
       return poiRegistry.addPoiList();
    }
}
