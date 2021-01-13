/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Utilizador
 */
public class POI  implements Place{
    
    /**
     * POI id
     */
    private int idPoi;
    /**
     * POI latitude
     */
    private double latitude;
    /**
     * ´POI longitude
     */
    private double longitude;
    /**
     * POI elevation
     */
    private double elevation;
    /**
     * POI description
     */
    private String description;
    private static int n=0;

    /**
     * Constructor
     * 
     * @param latitude - poi latitude
     * @param longitude - poi longitude
     * @param elevation - poi elevation
     * @param description - poi description
     */
    public POI(double latitude, double longitude, double elevation, String description) {
        this.idPoi=n--;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.description = description;
    }
    
    /**
     * Constructor
     * 
     * @param id - poi id
     * @param latitude - poi latitude
     * @param longitude -poi longitude
     * @param elevation - poi elevation
     * @param description - poi description
     */
    public POI(int id,double latitude, double longitude, double elevation, String description) {
        this.idPoi=id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.description = description;
    }
    
    /**
     * Gets poi id
     * @return poi id
     */
    public int getId() {
        return this.idPoi;
    }
    
    /**
     * Gets POI latitude
     * 
     * @return POI latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets POI longitude
     * @return POI longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets POI elevation
     * @return POI elevation
     */
    @Override
    public double getElevation() {
        return elevation;
    }

    /**
     * Gets POI description
     * @return POI description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets POI latitude
     * @param latitude 
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets POI longitude
     * @param longitude 
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets POI elevation
     * @param elevation 
     */
    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    /**
     * Sets POI description
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Hash Code
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    /**
     * Returns whether two objects of POI are equal or not
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final POI other = (POI) obj;
        return this.idPoi==other.idPoi;
    }

    /**
     * ToString of a POI
     * @return description of a POI
     */
    @Override
    public String toString() {
        return "POI:\n" + "latitude=" + latitude + "\nlongitude=" + longitude + "\nelevation=" + elevation + "\ndescription=" + description;
    }
    
    
    
}
