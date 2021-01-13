/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author bruno
 */
public class VehicleTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of toString method, of class VehicleImpl.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VehicleImpl instance = new VehicleImpl();
        instance.setID(10);
        System.out.println(instance);
    }
    /**
     * Test of getID method, of class Vehicle.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Vehicle instance = new VehicleImpl();
        instance.setID(1);
        int expResult = 1;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class Vehicle.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        Vehicle instance = new VehicleImpl();
        instance.setID(1);
        assertEquals(1, instance.getID());
        
        try {
            instance.setID(0);
            fail("id is zero");
        } catch (Exception e) {
        }
        
        try {
            instance.setID(-1);
            fail("id is less than zero");
        } catch (Exception e) {
        }
        
    }

    /**
     * Test of getIDPark method, of class Vehicle.
     */
    @Test
    public void testGetIDPark() {
        System.out.println("getIDPark");
        Vehicle instance = new VehicleImpl();
        instance.setIDPark(1);
        int expResult = 1;
        int result = instance.getIDPark();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIDPark method, of class Vehicle.
     */
    @Test
    public void testSetIDPark() {
        System.out.println("setIDPark");
        int idPark = 0;
        Vehicle instance = new VehicleImpl();
        instance.setIDPark(1);
        assertEquals(1, instance.getIDPark());
        
        try {
            instance.setIDPark(-1);
            fail("id park lower than zero");
        } catch (Exception e) {
        }
    }

    /**
     * Test of getAvailable method, of class Vehicle.
     */
    @Test
    public void testGetAvailable() {
        System.out.println("getAvailable");
        Vehicle instance = new VehicleImpl();
        instance.setAvailable('1');
        char expResult = '1';
        char result = instance.getAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAvailable method, of class Vehicle.
     */
    @Test
    public void testSetAvailable() {
        System.out.println("setAvailable");
        Vehicle instance = new VehicleImpl();
        instance.setAvailable('0');
        assertEquals('0', instance.getAvailable());
        
        instance.setAvailable('1');
        assertEquals('1', instance.getAvailable());
        
        try {
            instance.setAvailable('3');
            fail("availbility different than '1' and '0'.");
        } catch (Exception e) {
        }
    }

    /**
     * Test of getWeight method, of class Vehicle.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Vehicle instance = new VehicleImpl();
        instance.setWeight(1);
        int expResult = 1;
        int result = instance.getWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWeight method, of class Vehicle.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double weight = 0.0;
        Vehicle instance = new VehicleImpl();
        instance.setWeight(1);
        int expResult = 1;
        int result = instance.getWeight();
        assertEquals(expResult, result);
        
        
        try {
            instance.setWeight(0);
            fail("weight is zero");
        } catch (Exception e) {
        }
        
        
        try {
            instance.setWeight(-1);
            fail("weight is less than zero");
        } catch (Exception e) {
        }
    }

    /**
     * Test of getAerodynamicCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetAerodynamicCoefficient() {
        System.out.println("getAerodynamicCoefficient");
        Vehicle instance = new VehicleImpl();
        instance.setAerodynamicCoefficient(1);
        double expResult = 1;
        double result = instance.getAerodynamicCoefficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAerodynamicCoefficient method, of class Vehicle.
     */
    @Test
    public void testSetAerodynamicCoefficient() {
        System.out.println("setAerodynamicCoefficient");
        Vehicle instance = new VehicleImpl();
        instance.setAerodynamicCoefficient(1);
        
        
        try {
            instance.setAerodynamicCoefficient(0);
            fail("aerodynamic coefficient is zero");
        } catch (Exception e) {
        }
        
        
        try {
            instance.setAerodynamicCoefficient(-1);
            fail("aerodynamic coefficient is less than zero");
        } catch (Exception e) {
        }
    }

    /**
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        Vehicle instance = new VehicleImpl();
        instance.setFrontalArea(1);
        double expResult = 1;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setFrontalArea method, of class Vehicle.
     */
    @Test
    public void testSetFrontalArea() {
        System.out.println("setFrontalArea");
        double frontalArea = 1;
        Vehicle instance = new VehicleImpl();
        instance.setFrontalArea(frontalArea);
        
        
        try {
            instance.setFrontalArea(0);
            fail("frontal area is zero");
        } catch (Exception e) {
        }
        
        
        try {
            instance.setFrontalArea(-1);
            fail("frontal area is less than zero");
        } catch (Exception e) {
        }
    }

    /**
     * Test of hashCode method, of class Vehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Vehicle instance = new VehicleImpl();
        instance.setID(1);
        int expResult = 83 * 3 + Objects.hashCode(instance.getID());;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Vehicle instance = new VehicleImpl();
        instance.setID(1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        Bicycle b = new Bicycle();
        instance.setID(1);
        obj = b;
        result = instance.equals(obj);
        assertEquals(false, result);
        
        result = instance.equals(instance);
        assertEquals(true, result);
        
        Vehicle v = new VehicleImpl();
        v.setID(1);
        obj = v;
        result = instance.equals(obj);
        assertEquals(true, result);
    }

    public class VehicleImpl extends Vehicle {
    }

    /**
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Vehicle instance = new VehicleImpl();
        String expResult = "PT00";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
