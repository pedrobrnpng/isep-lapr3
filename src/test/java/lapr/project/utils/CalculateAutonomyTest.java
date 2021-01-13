/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Scooter;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author pedro
 */
public class CalculateAutonomyTest {
    

    /**
     * Test of calculateEnergy method, of class CalculateAutonomy.
     */
    @Test
    public void testCalculateEnergy() {
        System.out.println("calculateEnergy");
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(4);
        ss2.setMaxBatteryCapacity(350);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        CalculateAutonomy instance = new CalculateAutonomy();
        double expResult = 21560;
        double result = instance.calculateEnergy(ss2);
        assertEquals(expResult, result, 0.0);

    }
    
}
