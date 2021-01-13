/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import static lapr.project.utils.maths.Mathematics.round;


/**
 *
 * @author bruno
 */
public abstract class Vehicle {
    
    private int id;
    private int idPark;
    private char available;
    private int weight;
    private double aerodynamicCoefficient;
    private double frontalArea;

    /**
     *
     */
    public Vehicle() {
    }

    /**
     *
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     */
    public Vehicle(int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) {
        setID(id);
        setIDPark(idPark);
        setAvailable('1');
        setWeight(weight);
        setAerodynamicCoefficient(aerodynamicCoefficient);
        setFrontalArea(frontalArea);
    }

    /**
     *
     * @return
     */
    public String getDescription(){
        return "PT0" + id;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return id;
    }

    /**
     *
     * @param id
     */
    public final void setID(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("ID cannot be equal or less than 0.");
        }
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getIDPark() {
        return idPark;
    }

    /**
     *
     * @param idPark
     */
    public void setIDPark(int idPark) {
        if (idPark < 0){
            throw new IllegalArgumentException("Park ID cannot be less than 0.");
        }
        this.idPark = idPark;
    }

    /**
     *
     * @return
     */
    public char getAvailable() {
        return available;
    }

    /**
     *
     * @param available
     */
    public final void setAvailable(char available) {
        if (available != '1' && available != '0'){
            throw new IllegalArgumentException("Availability cannot be different than '1' and '0'.");
        }
        this.available = available;
    }

    /**
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public final void setWeight(int weight) {
        if (weight <= 0){
            throw new IllegalArgumentException("Weight cannot be equal or less than 0.");
        }
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    public double getAerodynamicCoefficient() {
        return aerodynamicCoefficient;
    }

    /**
     *
     * @param aerodynamicCoefficient
     */
    public final void setAerodynamicCoefficient(double aerodynamicCoefficient) {
        if (aerodynamicCoefficient <= 0){
            throw new IllegalArgumentException("Aero dynamic coefficient cannot be equal or less than 0.");
        }
        this.aerodynamicCoefficient = round(aerodynamicCoefficient, 2);
    }

    /**
     *
     * @return
     */
    public double getFrontalArea() {
        return frontalArea;
    }

    /**
     *
     * @param frontalArea
     */
    public final void setFrontalArea(double frontalArea) {
        if (frontalArea <= 0){
            throw new IllegalArgumentException("Frontal area cannot be equal or less than 0.");
        }
        this.frontalArea = round(frontalArea, 1);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

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
        final Vehicle other = (Vehicle) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Vehicles{" + "id=" + getDescription() + '}';
    }
}
