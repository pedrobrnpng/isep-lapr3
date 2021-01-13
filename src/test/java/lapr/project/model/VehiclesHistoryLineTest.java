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
public class VehiclesHistoryLineTest {

    VehiclesHistoryLine instance;

    public VehiclesHistoryLineTest() {
    }

    /**
     * Test of getLockedUnlocked method, of class VehiclesHistoryLine.
     */
    @Test
    public void testGetLockedUnlocked() {
        System.out.println("getLockedUnlocked");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        String expResult = "LOCKED";
        String result = instance.getLockedUnlocked();
        assertEquals(expResult, result);
        
        instance = new VehiclesHistoryLine('0', "12-12-12", "PT050", "Trindade");
        expResult = "UNLOCKED";
        result = instance.getLockedUnlocked();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocked method, of class VehiclesHistoryLine.
     */
    @Test
    public void testSetLocked() {
        System.out.println("setLocked");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setLocked();
        assertEquals(instance.getLockedUnlocked(), "LOCKED");
    }

    /**
     * Test of setUnlocked method, of class VehiclesHistoryLine.
     */
    @Test
    public void testSetUnlocked() {
        System.out.println("setUnlocked");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setUnlocked();
        assertEquals(instance.getLockedUnlocked(), "UNLOCKED");
    }

    /**
     * Test of getDate method, of class VehiclesHistoryLine.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setDate("");
        String expResult = "";
        String result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class VehiclesHistoryLine.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        String date = "";
        instance.setDate(date);
        assertEquals(date, instance.getDate());
    }

    /**
     * Test of getVehicle method, of class VehiclesHistoryLine.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setVehicle("");
        String expResult = "";
        String result = instance.getVehicle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicle method, of class VehiclesHistoryLine.
     */
    @Test
    public void testSetVehicle() {
        System.out.println("setVehicle");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        String vehicle = "";
        instance.setVehicle(vehicle);
        assertEquals(vehicle, instance.getVehicle());
    }

    /**
     * Test of getPark method, of class VehiclesHistoryLine.
     */
    @Test
    public void testGetPark() {
        System.out.println("getPark");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setPark("");
        String expResult = "";
        String result = instance.getPark();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPark method, of class VehiclesHistoryLine.
     */
    @Test
    public void testSetPark() {
        System.out.println("setPark");
        String park = "";
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        instance.setPark(park);
        assertEquals(park, instance.getPark());
    }

    /**
     * Test of toString method, of class VehiclesHistoryLine.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        instance = new VehiclesHistoryLine('1', "12-12-12", "PT050", "Trindade");
        String expResult = instance.getVehicle() + " was " + instance.getLockedUnlocked() + " at " + instance.getDate() + ", " + instance.getPark();
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

}
