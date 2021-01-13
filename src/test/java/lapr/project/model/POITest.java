/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class POITest {

    public POITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLatitude method, of class POI.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        double expResult = 41.147997;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class POI.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        double expResult = -8.608182;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getElevation method, of class POI.
     */
    @Test
    public void testGetElevation() {
        System.out.println("getElevation");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        double expResult = 30.0;
        double result = instance.getElevation();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDescription method, of class POI.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        String expResult = "Fábrica Nortada";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLatitude method, of class POI.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 12.0;
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        instance.setLatitude(latitude);
        assertEquals(latitude, instance.getLatitude(), 0.0);
    }

    /**
     * Test of setLongitude method, of class POI.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 12.0;
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        instance.setLongitude(longitude);
        assertEquals(longitude, instance.getLongitude(), 0.0);
    }

    /**
     * Test of setElevation method, of class POI.
     */
    @Test
    public void testSetElevation() {
        System.out.println("setElevation");
        double elevation = 12.0;
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        instance.setElevation(elevation);
        assertEquals(elevation, instance.getElevation(), 0.0);
    }

    /**
     * Test of setDescription method, of class POI.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "ISEP";
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of hashCode method, of class POI.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        int expResult = -590283552;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = new ParkRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals5() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = new POI(40.147997, -8.608182, 30, "Fábrica Nortada");;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class POI.
     */
    @Test
    public void testEquals6() {
        System.out.println("equals");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        Object obj = new POI(41.147997, -2.608182, 30, "Fábrica Nortada");;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class POI.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        POI instance = new POI(41.147997, -8.608182, 30, "Fábrica Nortada");
        String expResult = "POI:\n"
                + "latitude=41.147997\n"
                + "longitude=-8.608182\n"
                + "elevation=30.0\n"
                + "description=Fábrica Nortada";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class POI.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        POI instance = new POI(1, 23, 42, 12, "Fábrica Nortada");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

}
