/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Scooter;

/**
 *
 * @author pedro
 */
public class CalculateAutonomy {
    
    private final double VMAX = 20 ;
    
    /**
     * Returns the distance that the actual capacity of the bateries are able to reach.
     * The result is in METERS!
     * @param s scooter
     * @return (meters)
     */
    public double calculateEnergy(Scooter s) {
        double time = (s.getActualBatteryCapacity() / 100 * (s.getMaxBatteryCapacity() * 1000)) / s.getMotor();
        double distance = VMAX * time ;
        return (distance + distance * 0.1) * 0.7 ;
    }
    
}
