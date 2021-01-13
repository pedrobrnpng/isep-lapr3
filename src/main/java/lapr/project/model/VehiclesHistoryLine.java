/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author bruno
 */
public class VehiclesHistoryLine {
    
    
    private final String LOCKED = "LOCKED";
    private final String UNLOCKED = "UNLOCKED";
    
    private String lockedUnlocked;
    private String date;
    private String vehicle;
    private String park;

    /**
     *
     * @param lockedUnlocked
     * @param date
     * @param vehicle
     * @param park
     */
    public VehiclesHistoryLine(char lockedUnlocked, String date, String vehicle, String park) {
        if (lockedUnlocked == '0'){
            setUnlocked();
        } 
        if (lockedUnlocked == '1'){
            setLocked();
        }
        this.date = date;
        this.vehicle = vehicle;
        this.park = park;
    }

    /**
     *
     * @return
     */
    public String getLockedUnlocked() {
        return lockedUnlocked;
    }

    /**
     *
     */
    public void setLocked() {
        this.lockedUnlocked = LOCKED;
    }
    
    /**
     *
     */
    public void setUnlocked(){
        this.lockedUnlocked = UNLOCKED;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getVehicle() {
        return vehicle;
    }

    /**
     *
     * @param vehicle
     */
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    /**
     *
     * @return
     */
    public String getPark() {
        return park;
    }

    /**
     *
     * @param park
     */
    public void setPark(String park) {
        this.park = park;
    }

    @Override
    public String toString() {
        return vehicle + " was " + lockedUnlocked + " at " + date + ", " + park;
    }
    
    
}
