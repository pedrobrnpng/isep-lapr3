/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bruno
 */
public class BicycleTest {

    

    /**
     * Test of getWheelSize method, of class Bicycle.
     */
    @Test
    public void testGetWheelSize() {
        System.out.println("getWheelSize");
        Bicycle instance = new Bicycle();
        instance.setWheelSize("0");
        String expResult = "0";
        String result = instance.getWheelSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWheelSize method, of class Bicycle.
     */
    @Test
    public void testSetWheelSize() {
        System.out.println("setWheelSize");
        Bicycle instance = new Bicycle();
        instance.setWheelSize("0");
        
        try {
            instance.setWheelSize("");
            fail("wheel size is empty");
        } catch (Exception e) {
        }
        
        try {
            instance.setWheelSize(null);
            fail("wheel size is empty");
        } catch (Exception e) {
        }
    }
    
    @Test
    public void testToString() {
        System.out.println("toString");
        Bicycle instance=new Bicycle();
        String result=instance.toString();
        String expResult="Bicycle{id=PT00}";
        assertEquals(expResult, result);
    }
    
}
