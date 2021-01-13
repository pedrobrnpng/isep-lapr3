/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author pedro
 */
public class Path {

    /**
     * path id
     */
    private int path_id;
    /**
     * origin place id
     */
    private int id_place_origin;
    /**
     * destination place id
     */
    private int id_place_destination;
    /**
     * kinectic coeficient
     */
    private double kinectic_coeficient;
    /**
     * wind direction
     */
    private double wind_dir;
    /**
     * wind speed
     */
    private double wind_speed;
    private static int n = 0;

    /**
     * Constructor
     *
     * @param path_id - path id
     * @param id_place_origin - origin place id
     * @param id_place_destination - destination place id
     * @param kinectic_coeficient - kinectic coeficient
     * @param wind_dir - wind direction
     * @param wind_speed - wind speed
     */
    public Path(int path_id, int id_place_origin, int id_place_destination, double kinectic_coeficient, double wind_dir, double wind_speed) {
        this.path_id = path_id;
        this.id_place_origin = id_place_origin;
        this.id_place_destination = id_place_destination;
        this.kinectic_coeficient = kinectic_coeficient;
        this.wind_dir = wind_dir;
        this.wind_speed = wind_speed;
    }

    /**
     * Constructor
     *
     * @param id_place_origin - origin place id
     * @param id_place_destination - destination place id
     * @param kinectic_coeficient - kinectic coeficient
     * @param wind_dir - wind direction
     * @param wind_speed - wind speed
     */
    public Path(int id_place_origin, int id_place_destination, double kinectic_coeficient, double wind_dir, double wind_speed) {
        this.path_id = n--;
        this.id_place_origin = id_place_origin;
        this.id_place_destination = id_place_destination;
        this.kinectic_coeficient = kinectic_coeficient;
        this.wind_dir = wind_dir;
        this.wind_speed = wind_speed;
    }

    //<editor-fold defaultstate="collapsed" desc="Get Methods">
    /**
     * Gets path id
     * @return path id
     */
    public int getPath_id() {
        return path_id;
    }

    /**
     * Gets origin place id
     * @return origin place id
     */
    public int getId_place_origin() {
        return id_place_origin;
    }

    /**
     * Gets destination place id
     * @return 
     */
    public int getId_place_destination() {
        return id_place_destination;
    }

    /**
     * Gets kinectic coeficient
     * @return kinectic coeficient
     */
    public double getKinectic_coeficient() {
        return kinectic_coeficient;
    }

    /**
     * Gets wind direction
     * @return wind direction
     */
    public double getWind_dir() {
        return wind_dir;
    }

    /**
     * Gets wind speed
     * @return wind speed
     */
    public double getWind_speed() {
        return wind_speed;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Set Methods">
    /**
     * Sets path id
     * @param path_id 
     */
    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    /**
     * Sets origin place
     * @param id_place_origin - origin place id
     */
    public void setId_place_origin(int id_place_origin) {
        this.id_place_origin = id_place_origin;
    }

    /**
     * Sets destination place id
     * @param id_place_destination 
     */
    public void setId_place_destination(int id_place_destination) {
        this.id_place_destination = id_place_destination;
    }

    /**
     * Sets the kinectic coeficient
     * @param kinectic_coeficient 
     */
    public void setKinectic_coeficient(double kinectic_coeficient) {
        this.kinectic_coeficient = kinectic_coeficient;
    }

    /**
     * Sets the wind direction
     * @param wind_dir - wind direction
     */
    public void setWind_dir(double wind_dir) {
        this.wind_dir = wind_dir;
    }

    /**
     *
     * @param wind_speed
     */
    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }
    //</editor-fold>

    /**
     * Hash Code
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.path_id;
        return hash;
    }

    /**
     * Compares if the paths are equal
     * @param obj
     * @return boolean wether they are equal or not
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
        final Path other = (Path) obj;
        return this.path_id == other.path_id;
    }

}
