/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class ScooterTest {

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
     * Test of getMaxBatteryLevel method, of class Scooter.
     */
    @Test
    public void testGetMaxBatteryLevel() {
        System.out.println("getMaxBatteryLevel");
        Scooter instance = new Scooter();
        instance.setMaxBatteryCapacity(10);
        int expResult = 10;
        int result = instance.getMaxBatteryCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActualBatteryLevel method, of class Scooter.
     */
    @Test
    public void testGetActualBatteryLevel() {
        System.out.println("getActualBatteryLevel");
        Scooter instance = new Scooter();
        instance.setActualBatteryCapacity(10);
        int expResult = 10;
        int result = instance.getActualBatteryCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScooterType method, of class Scooter.
     */
    @Test
    public void testGetType() {
        System.out.println("getScooterType");
        Scooter instance = new Scooter();
        instance.setType("off-road");
        String expResult = "off-road";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Scooter.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Scooter instance = new Scooter();
        instance.setID(1);
        int expResult = 1;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIDPark method, of class Scooter.
     */
    @Test
    public void testGetIDPark() {
        System.out.println("getIDPark");
        Scooter instance = new Scooter();
        instance.setIDPark(1);
        int expResult = 1;
        int result = instance.getIDPark();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailabilaty method, of class Scooter.
     */
    @Test
    public void testGetAvailabilaty() {
        System.out.println("getAvailabilaty");
        Scooter instance = new Scooter();
        instance.setAvailable('1');
        char expResult = '1';
        char result = instance.getAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaxBatteryLevel method, of class Scooter.
     */
    @Test
    public void testSetMaxBatteryLevel() {
        System.out.println("setMaxBatteryLevel");
        Scooter instance = new Scooter();
        instance.setMaxBatteryCapacity(1);
        assertEquals(1, instance.getMaxBatteryCapacity());

        try {
            instance.setMaxBatteryCapacity(0);
            fail("max battery capacity is zero");
        } catch (Exception e) {
        }

        try {
            instance.setMaxBatteryCapacity(-1);
            fail("max batery capacity is less than zero");
        } catch (Exception e) {
        }
    }

    /**
     * Test of setActualBatteryLevel method, of class Scooter.
     */
    @Test
    public void testSetActualBatteryLevel() {
        System.out.println("setActualBatteryLevel");
        Scooter instance = new Scooter();
        instance.setActualBatteryCapacity(1);
        assertEquals(1, instance.getActualBatteryCapacity());
        instance.setActualBatteryCapacity(0);
        instance.setActualBatteryCapacity(100);
        try {
            instance.setActualBatteryCapacity(-1);
            fail("actual batery capacity is less than zero");
        } catch (Exception e) {
        }
        
        try {
            instance.setActualBatteryCapacity(101);
            fail("actual batery capacity is more than 100");
        } catch (Exception e) {
        }
    }

    /**
     * Test of setScooterType method, of class Scooter.
     */
    @Test
    public void testSetType() {
        System.out.println("setScooterType");
        Scooter instance = new Scooter();
        instance.setType("off-road");
        assertEquals("off-road", instance.getType());

        try {
            instance.setType("");
            fail("scooter type is empty");
        } catch (Exception e) {
        }

        try {
            instance.setType(null);
            fail("scootertype is null");
        } catch (Exception e) {
        }
    }

    /**
     * Test of getDescription method, of class Scooter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Scooter instance = new Scooter();
        instance.setID(10);
        System.out.println("instance");
    }

    /**
     * Test of toString method, of class Scooter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Scooter instance = new Scooter();
        String expResult = "Scooter{id=ePT00}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxBatteryCapacity method, of class Scooter.
     */
    @Test
    public void testGetMaxBatteryCapacity() {
        System.out.println("getMaxBatteryCapacity");
        Scooter instance = new Scooter();
        instance.setMaxBatteryCapacity(2);
        int expResult = 2;
        int result = instance.getMaxBatteryCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaxBatteryCapacity method, of class Scooter.
     */
    @Test
    public void testSetMaxBatteryCapacity() {
        System.out.println("setMaxBatteryCapacity");
        int maxBatteryCapacity = 2;
        Scooter instance = new Scooter();
        instance.setMaxBatteryCapacity(maxBatteryCapacity);
        assertEquals(maxBatteryCapacity, instance.getMaxBatteryCapacity());
    }

    /**
     * Test of getActualBatteryCapacity method, of class Scooter.
     */
    @Test
    public void testGetActualBatteryCapacity() {
        System.out.println("getActualBatteryCapacity");
        Scooter instance = new Scooter();
        instance.setActualBatteryCapacity(4);
        int expResult = 4;
        int result = instance.getActualBatteryCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActualBatteryCapacity method, of class Scooter.
     */
    @Test
    public void testSetActualBatteryCapacity() {
        System.out.println("setActualBatteryCapacity");
        int actualBatteryCapacity = 7;
        Scooter instance = new Scooter();
        instance.setActualBatteryCapacity(actualBatteryCapacity);
        assertEquals(actualBatteryCapacity, instance.getActualBatteryCapacity());
    }

    /**
     * Test of getMotor method, of class Scooter.
     */
    @Test
    public void testGetMotor() {
        System.out.println("getMotor");
        Scooter instance = new Scooter();
        int expResult = 5;
        instance.setMotor(expResult);
        int result = instance.getMotor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMotor method, of class Scooter.
     */
    @Test
    public void testSetMotor() {
        System.out.println("setMotor");
        int motor = 1;
        Scooter instance = new Scooter();
        instance.setMotor(motor);
        assertEquals(motor, instance.getMotor());
        try {
            instance.setMotor(0);
        } catch (Exception ex) {
            assertFalse(!true);
        }
        try {
            instance.setMotor(-1);
        } catch (Exception ex) {
            assertFalse(!true);
        }
    }


}
