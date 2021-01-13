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
public class Bicycle extends Vehicle {

    private String wheelSize;

    /**
     *
     */
    public Bicycle() {
    }

    /**
     *
     * @param wheelSize
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     */
    public Bicycle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) {
        super(id, idPark, weight, aerodynamicCoefficient, frontalArea);
        setWheelSize(wheelSize);
    }

    /**
     *
     * @return
     */
    public String getWheelSize() {
        return wheelSize;
    }

    /**
     *
     * @param wheelSize
     */
    public final void setWheelSize(String wheelSize) {
        if (wheelSize == null || wheelSize.isEmpty()){
            throw new IllegalArgumentException("WheelSize cannot be null nor empty.");
        }
        this.wheelSize = wheelSize;
    }

    @Override
    public String toString() {
        return "Bicycle{" + "id=" + super.getDescription() + '}';
    }

}
