/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.POIDB;

/**
 *
 * @author Utilizador
 */
public class POIRegistry {

    /**
     * POI list
     */
    private Set<POI> poilist;
    /**
     * POI DB
     */
    private POIDB poidb;

    /**
     * Constructor
     */
    public POIRegistry() {
        this.poilist = new HashSet<>();
        poidb = new POIDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     * Sets the POI list
     *
     * @param poiList - list of POI
     */
    public void setPOIList(Set<POI> poiList) {
        this.poilist = poiList;
    }

    /**
     * Sets the POI DB
     *
     * @param pdb - POI DB
     */
    public void setPOIDB(POIDB pdb) {
        this.poidb = pdb;
    }

    /**
     * Creates a new POI
     *
     * @param latitude - POI latitude
     * @param longitude - POI longitude
     * @param elevation - POI elevation
     * @param description - POI description
     * @return POI created
     */
    public POI newPOI(double latitude, double longitude, double elevation, String description) {
        POI poi = new POI(latitude, longitude, elevation, description);
        this.poilist.add(poi);
        return poi;
    }

    /**
     * Adds a POI to the database
     *
     * @param poi - POIs
     * @return boolean that indicates 
     */
    public boolean addPoi(POI poi) {
        return poidb.addPOIDB(poi);
    }

    /**
     * Returns a list of existing POI in the database
     *
     * @return list of existing POI
     */
    public Set<POI> getExistingPOI() {
        this.poilist = this.poidb.getExistingPOI();
        return this.poilist;
    }

    /**
     * Adds several POI to the database
     *
     * @return list of POI
     */
    public int addPoiList() {
        return this.poidb.addPOIDB(this.poilist);
    }

    /**
     *
     * @param elements
     */
    public void getPOIS(Place[] elements) {
        int i;
        for (POI p : poilist) {
            for (i = 0; i < elements.length; i++) {
                if (elements[i].getLatitude() == p.getLatitude() && elements[i].getLongitude() == p.getLongitude()) {
                    elements[i] = p;
                }
            }
        }
    }

}
