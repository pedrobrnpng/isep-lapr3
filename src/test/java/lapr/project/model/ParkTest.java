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
public class ParkTest {

    private Park park;

    public ParkTest() {
        park = new Park(1, "Porto", 2, 3, 4, "parque", 5, 6, 7, 8);
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
     * Test of getIdentification method, of class Park.
     */
    @Test
    public void testGetIdentification() {
        System.out.println("getIdentification");
        String expResult = "Porto";
        String result = park.getIdentification();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Park.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        double expResult = 2.0;
        double result = park.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class Park.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        double expResult = 3.0;
        double result = park.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getElevation method, of class Park.
     */
    @Test
    public void testGetElevation() {
        System.out.println("getElevation");
        double expResult = 4.0;
        double result = park.getElevation();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDescription method, of class Park.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "parque";
        String result = park.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxNumberBicycles method, of class Park.
     */
    @Test
    public void testGetMaxNumberBicycles() {
        System.out.println("getMaxNumberBicycles");
        int expResult = 5;
        int result = park.getMaxNumberBicycles();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxNumberScooter method, of class Park.
     */
    @Test
    public void testGetMaxNumberScooter() {
        System.out.println("getMaxNumberScooter");
        int expResult = 6;
        int result = park.getMaxNumberScooter();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInputVoltage method, of class Park.
     */
    @Test
    public void testGetInputVoltage() {
        System.out.println("getInputVoltage");

        double expResult = 7.0;
        double result = park.getInputVoltage();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getInputCurrent method, of class Park.
     */
    @Test
    public void testGetInputCurrent() {
        System.out.println("getInputCurrent");
        double expResult = 8.0;
        double result = park.getInputCurrent();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isActive method, of class Park.
     */
    @Test
    public void testIsActive() {
        System.out.println("isActive");
        boolean expResult = true;
        boolean result = park.isActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Park.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String identification = "Lisboa";
        park.setIdentification(identification);
    }

    /**
     * Test of setLatitude method, of class Park.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 0.0;
        park.setLatitude(latitude);
    }

    /**
     * Test of setLongitude method, of class Park.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 0.0;
        park.setLongitude(longitude);
    }

    /**
     * Test of setElevation method, of class Park.
     */
    @Test
    public void testSetElevation() {
        System.out.println("setElevation");
        double elevation = 0.0;
        park.setElevation(elevation);
    }

    /**
     * Test of setDescription method, of class Park.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        park.setDescription(description);
    }

    /**
     * Test of setMaxNumberBicycles method, of class Park.
     */
    @Test
    public void testSetMaxNumberBicycles() {
        System.out.println("setMaxNumberBicycles");
        int maxNumberBicycles = 0;
        park.setMaxNumberBicycles(maxNumberBicycles);
    }

    /**
     * Test of setMaxNumberScooter method, of class Park.
     */
    @Test
    public void testSetMaxNumberScooter() {
        System.out.println("setMaxNumberScooter");
        int maxNumberScooter = 0;
        park.setMaxNumberScooter(maxNumberScooter);
    }

    /**
     * Test of setInputVoltage method, of class Park.
     */
    @Test
    public void testSetInputVoltage() {
        System.out.println("setInputVoltage");
        double inputVoltage = 0.0;
        park.setInputVoltage(inputVoltage);
    }

    /**
     * Test of setInputCurrent method, of class Park.
     */
    @Test
    public void testSetInputCurrent() {
        System.out.println("setInputCurrent");
        double inputCurrent = 0.0;
        park.setInputCurrent(inputCurrent);
    }

    /**
     * Test of setActive method, of class Park.
     */
    @Test
    public void testSetActive() {
        System.out.println("setActive");
        boolean active = false;
        park.setActive(active);
    }

    /**
     * Test of hashCode method, of class Park.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 543011;
        int result = park.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = park.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = park;
        boolean result = park.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals3() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new ParkRegistry();
        boolean result = park.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals4() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = new Park(1, "Porto", 2, 3, 4, "parque", 5, 6, 7, 8);
        boolean result = park.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals5() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new Park(2, "Porto", 3, 3, 4, "parque", 5, 6, 7, 8);
        boolean result = park.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Park.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Park:\n"
                + "identification=Porto\n"
                + "latitude=2.0º\n"
                + "longitude=3.0º\n"
                + "elevation=4.0m\n"
                + "description=parque\n"
                + "maxNumberBicycles=5\n"
                + "maxNumberScooter=6\n"
                + "inputVoltage=7.0V\n"
                + "inputCurrent=8.0A\n"
                + "active=true";
        String result = park.toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdPark method, of class Park.
     */
    @Test
    public void testSetIdPark() {
        System.out.println("setIdPark");
        int id = 2;
        park.setIdPark(id);
    }

    /**
     * Test of getParkChargePower method, of class Park.
     */
    @Test
    public void testGetParkChargePower() {
        System.out.println("getParkChargePower");
        double expResult = 56.0;
        double result = park.getParkChargePower();
        assertEquals(expResult, result, 0.0);
    }
}
