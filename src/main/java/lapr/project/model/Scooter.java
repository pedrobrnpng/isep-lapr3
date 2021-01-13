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
public class Scooter extends Vehicle {

    private String type;
    private int maxBatteryCapacity;
    private int actualBatteryCapacity;
    private int motor;

    /**
     *
     */
    public Scooter() {
    }

    /**
     *
     * @param type
     * @param maxBatteryCapacity
     * @param actualBatteryCapacity
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @param motor
     */
    public Scooter(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) {
        super(id, idPark, weight, aerodynamicCoefficient, frontalArea);
        setType(type);
        setMaxBatteryCapacity(maxBatteryCapacity);
        setActualBatteryCapacity(actualBatteryCapacity);
        setMotor(motor);
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return "ePT0" + super.getID();
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Scooter type cannot be null nor empty.");
        }
        this.type = type.toLowerCase();
    }

    /**
     *
     * @return
     */
    public int getMaxBatteryCapacity() {
        return maxBatteryCapacity;
    }

    /**
     *
     * @param maxBatteryCapacity
     */
    public void setMaxBatteryCapacity(int maxBatteryCapacity) {
        if (maxBatteryCapacity <= 0) {
            throw new IllegalArgumentException("Max battery capacity cannot be less nor equal than 0.");
        }
        this.maxBatteryCapacity = maxBatteryCapacity;
    }

    /**
     *
     * @return
     */
    public int getActualBatteryCapacity() {
        return actualBatteryCapacity;
    }

    /**
     *
     * @param actualBatteryCapacity
     */
    public void setActualBatteryCapacity(int actualBatteryCapacity) {
        if (actualBatteryCapacity < 0 || actualBatteryCapacity > 100) {
            throw new IllegalArgumentException("Actual battery capacity cannot be less than 0 neither more than 100.");
        }
        this.actualBatteryCapacity = actualBatteryCapacity;
    }

    /**
     *
     * @return
     */
    public int getMotor() {
        return motor;
    }

    /**
     *
     * @param motor
     */
    public void setMotor(int motor) {
        if (motor <= 0) {
            throw new IllegalArgumentException("Motor's power cannot be less or equal than 0.");
        }
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Scooter{" + "id=" + getDescription() + '}';
    }

}
