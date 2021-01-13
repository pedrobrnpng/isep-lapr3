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
public class Park implements Place{
    
    /**
     *
     */
    public final double MAX_CHARGE_CURRENT_PER_POINT = 3; //kWh
    
    /**
     * park's id
     */
    private int idPark;
    /**
     * park's identification
     */
    private String identification;
    /**
     * park's latitude
     */
    private double latitude;
    /**
     * park's longitude
     */
    private double longitude;
    /**
     * park's elevation
     */
    private double elevation;
    /**
     * park's description
     */
    private String description;
    /**
     * park's max number of bicycles
     */
    private int maxNumberBicycles;
    /**
     * park's max number of scooters
     */
    private int maxNumberScooter;
    /**
     * park's inputVoltage
     */
    private double inputVoltage;
    /**
     * park's input current
     */
    private double inputCurrent;
    /**
     * Park's activation
     */
    private boolean active=true;
    private static int n=0;

    /**
     * Constructor
     * 
     * @param id
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
    public Park(int id,String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        this.idPark=id;
        this.identification = identification;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.description = description;
        this.maxNumberBicycles = maxNumberBicycles;
        this.maxNumberScooter = maxNumberScooter;
        this.inputVoltage = inputVoltage;
        this.inputCurrent = inputCurrent;
    }
    
    /**
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
    public Park(String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        this.idPark=n--;
        this.identification = identification;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.description = description;
        this.maxNumberBicycles = maxNumberBicycles;
        this.maxNumberScooter = maxNumberScooter;
        this.inputVoltage = inputVoltage;
        this.inputCurrent = inputCurrent;
    }

    /**
     *
     * @return
     */
    @Override
    public int getId() {
        return this.idPark;
    }
    /**
     * Gets park's identification
     * @return park's identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Gets park's latitude
     * @return park's latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets park's longitude
     * @return park's longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets park's elevation
     * @return the elevation of the park
     */
    public double getElevation() {
        return elevation;
    }

    /**
     * Gets park's descriptin
     * @return description of the park
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets park's number of bicycles
     * @return park's max number of bicycles
     */
    public int getMaxNumberBicycles() {
        return maxNumberBicycles;
    }

    /**
     * Gets park's max number of scooters
     * @return park's max number of scooter
     */
    public int getMaxNumberScooter() {
        return maxNumberScooter;
    }

    /**
     * Gets park's input voltage
     * @return park's input voltage
     */
    public double getInputVoltage() {
        return inputVoltage;
    }

    /**
     * Gets park's input current
     * @return park's input current
     */
    public double getInputCurrent() {
        return inputCurrent;
    }

    /**
     * Gets park's activation
     * @return park's activation
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     *
     * @param id
     */
    public void setIdPark(int id) {
        this.idPark=id;
    }
    /**
     * Sets park's identification
     * @param identification 
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets park's latitude
     * @param latitude 
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets park's longitude
     * @param longitude 
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Sets park's elevation
     * @param elevation 
     */
    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    /**
     * Sets park's description
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets park's max number of bicycles
     * @param maxNumberBicycles 
     */
    public void setMaxNumberBicycles(int maxNumberBicycles) {
        this.maxNumberBicycles = maxNumberBicycles;
    }

    /**
     * Sets park's max number of scooters
     * @param maxNumberScooter 
     */
    public void setMaxNumberScooter(int maxNumberScooter) {
        this.maxNumberScooter = maxNumberScooter;
    }

    /**
     * Sets park's input voltage
     * @param inputVoltage 
     */
    public void setInputVoltage(double inputVoltage) {
        this.inputVoltage = inputVoltage;
    }

    /**
     * Sets park's input current
     * @param inputCurrent 
     */
    public void setInputCurrent(double inputCurrent) {
        this.inputCurrent = inputCurrent;
    }

    /**
     * Sets parks activation
     * @param active 
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     *
     * @return
     */
    public double getParkChargePower() {
        return this.inputVoltage*this.inputCurrent;
    }

    /**
     * Sets park's hashcode
     * @return park's hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    /**
     * Method equals
     * @param obj
     * @return boolean
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
        final Park other = (Park) obj;
        return this.idPark==other.idPark;
    }

    /**
     * To string of class Park
     * @return description of park
     */
    @Override
    public String toString() {
        return "Park:\n" + "identification=" + identification + "\nlatitude=" + latitude + "º\nlongitude=" + longitude + "º\nelevation=" + elevation + "m\ndescription=" + description + "\nmaxNumberBicycles=" + maxNumberBicycles + "\nmaxNumberScooter=" + maxNumberScooter + "\ninputVoltage=" + inputVoltage + "V\ninputCurrent=" + inputCurrent + "A\nactive=" + active;
    }
    
    
}
