/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.ParkDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Utilizador
 */
public class ParkRegistryTest {

    ParkRegistry parkRegistry;

    public ParkRegistryTest() {
        parkRegistry = new ParkRegistry();
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
     * Test of getParks method, of class ParkRegistry.
     */
    @Test
    public void testGetParks() {
        System.out.println("getParks");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> result = parkRegistry.getParks();
        HashSet<Park> expResult = new HashSet<>();
        assertEquals(expResult, result);
    }

    /**
     * Test of newPark method, of class ParkRegistry.
     */
    @Test
    public void testNewPark() {
        System.out.println("newPark");
        int identification = 1;
        double latitude = 2.0;
        double longitude = 3.0;
        double elevation = 4.0;
        String description = "parque";
        int maxNumberBicycles = 5;
        int maxNumberScooter = 6;
        double inputVoltage = 7.0;
        double inputCurrent = 8.0;
        Park result = parkRegistry.newPark("Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        Park expResult = new Park(result.getId(), "Porto", 2, 3, 4, "parque", 5, 6, 7, 8);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ParkRegistry.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Set<Park> pl = new HashSet<>();
        ParkRegistry instance = new ParkRegistry(pl);
        int expResult = 5;
        expResult = 67 * expResult + 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ParkRegistry.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        ParkRegistry instance = new ParkRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        ParkRegistry instance = new ParkRegistry();
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals3() {
        System.out.println("equals");
        ParkRegistry instance = new ParkRegistry();
        Object obj = new ParkRegistry();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals4() {
        System.out.println("equals");
        ParkRegistry instance = new ParkRegistry();
        Object obj = new Park(1, "Porto", 3, 3, 4, "parque", 5, 6, 7, 8);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateInformation method, of class ParkRegistry.
     */
    @Test
    public void testUpdateInformation() {
        System.out.println("updateInformation");
        Park p = new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2);
        double latitude = 1.0;
        double longitude = 1.0;
        double elevation = 01.0;
        String description = "Parque";
        int maxNumberBicycles = 1;
        int maxNumberScooter = 1;
        double inputVoltage = 100.0;
        double inputCurrent = 100.0;
        ParkRegistry instance = new ParkRegistry();
        boolean expResult = true;
        boolean result = instance.updateInformation(p, "Porto2", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        assertEquals(expResult, result);
        assertEquals(p.getIdentification(), "Porto2");
        assertEquals(p.getLatitude(), latitude, 0.0);
        assertEquals(p.getLongitude(), longitude, 0.0);
        assertEquals(p.getElevation(), elevation, 0.0);
        assertEquals(p.getDescription(), description);
        assertEquals(p.getMaxNumberBicycles(), maxNumberBicycles);
        assertEquals(p.getMaxNumberScooter(), maxNumberScooter);
        assertEquals(p.getInputVoltage(), inputVoltage, 0.0);
        assertEquals(p.getInputCurrent(), inputCurrent, 0.0);
    }

    /**
     * Test of updateInformation method, of class ParkRegistry.
     */
    @Test
    public void testUpdateInformation2() {
        System.out.println("updateInformation");
        Park p = null;
        double latitude = 1.0;
        double longitude = 1.0;
        double elevation = 01.0;
        String description = "Parque";
        int maxNumberBicycles = 1;
        int maxNumberScooter = 1;
        double inputVoltage = 1.0;
        double inputCurrent = 1.0;
        ParkRegistry instance = new ParkRegistry();
        boolean expResult = false;
        boolean result = instance.updateInformation(p, "Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkList method, of class ParkRegistry.
     */
    @Test
    public void testSetParkList() {
        System.out.println("setParkList");
        Set<Park> list = new HashSet<>();
        ParkRegistry instance = new ParkRegistry();
        instance.setParkList(list);
    }

    /**
     * Test of validateCoordinates method, of class ParkRegistry.
     */
    @Test
    public void testValidateCoordinates() {
        System.out.println("validateCoordinates");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> parks = new HashSet<>();
        parks.add(new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2));
        instance.setParkList(parks);
        boolean expResult = true;
        boolean result = instance.validateCoordinates(23, 42);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validateCoordinates(22, 42);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validateCoordinates(22, 41);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validateCoordinates(20, 30);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validateCoordinates(23, 30);
        assertEquals(expResult, result);

    }

    /**
     * Test of validatePark method, of class ParkRegistry.
     */
    @Test
    public void testValidatePark() {
        System.out.println("validatePark");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> parks = new HashSet<>();
        parks.add(new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2));
        instance.setParkList(parks);
        boolean expResult = true;
        boolean result = instance.validatePark("Porto");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validatePark("Giga");
        assertEquals(expResult, result);
    }

    /**
     * Test of setParkDB method, of class ParkRegistry.
     */
    @Test
    public void testSetParkDB() {
        System.out.println("setParkDB");
        ParkDB pdb = new ParkDB();
        ParkRegistry instance = new ParkRegistry();
        instance.setParkDB(pdb);
    }

    /**
     * Test of getAllParks method, of class ParkRegistry.
     */
    @Test
    public void testGetAllParks() {
        System.out.println("getAllParks");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> parks = new HashSet<>();
        instance.setParkDB(mock(ParkDB.class));
        Set<Park> result = instance.getAllParks();
        assertEquals(parks, result);
    }

    /**
     * Test of getParkByCoordinates method, of class ParkRegistry.
     */
    @Test
    public void testGetParkByCoordinates() {
        System.out.println("getParkByCoordinates");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> parks = new HashSet<>();
        parks.add(new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2));
        instance.setParkList(parks);
        Park expResult = new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2);
        Park result = instance.getParkByCoordinates(23, 42);
        assertEquals(expResult, result);
        expResult = null;
        result = instance.getParkByCoordinates(1, 0);
        assertEquals(expResult, result);
        expResult = null;
        result = instance.getParkByCoordinates(23, 1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPark method, of class ParkRegistry.
     */
    @Test
    public void testGetPark() {
        System.out.println("getPark");
        ParkRegistry instance = new ParkRegistry();
        Set<Park> parks = new HashSet<>();
        parks.add(new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2));
        instance.setParkList(parks);
        Park expResult = new Park(1, "Porto", 23, 42, 12, "Parque do Porto", 10, 20, 1, 2);
        Park result = instance.getPark(1);
        assertEquals(expResult, result);
        expResult = null;
        result = instance.getPark(3);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAllParks method, of class ParkRegistry.
     */
    @Test
    public void testAddAllParks() {
        System.out.println("addAllParks");
        ParkRegistry instance = new ParkRegistry();
        ParkDB pdb = mock(ParkDB.class);
        Set<Park> pl = new HashSet<>();
        when(pdb.AddAllParks(pl)).thenReturn(0);
        instance.setParkDB(pdb);
        int expResult = 0;
        int result = instance.addAllParks();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkReport method, of class ParkRegistry.
     */
    @Test
    public void testGetParkReport() {
        System.out.println("getParkReport");
        Park park = new Park(1, "Trindade", 20, 12, 20, "Park da trindade", 10, 12, 260, 16);
        Set<Scooter> vl = new LinkedHashSet<>();
        vl.add(new Scooter("Off-Road", 2, 100, 4, 1, 23, 0.2, 30, 100));
        vl.add(new Scooter("Off-Road", 2, 100, 3, 1, 23, 0.2, 30, 100));
        vl.add(new Scooter("Off-Road", 2, 100, 5, 1, 23, 0.2, 30, 100));
        vl.add(new Scooter("Off-Road", 5, 20, 2, 1, 23, 0.2, 30, 100));
        List<String> list = new ArrayList<>();
        ParkRegistry instance = new ParkRegistry();
        long expResult = 1;
        long result = instance.getParkReport(park, vl, list);
        assertEquals(expResult, result);
        List<String> expList = new ArrayList<>();
        expList.add("ePT02;20;5333\n");
        expList.add("ePT03;100;0\n");
        expList.add("ePT04;100;0\n");
        expList.add("ePT05;100;0\n");
        assertEquals(expList,list);
        list.clear();
        park = new Park(1, "Trindade", 20, 12, 20, "Park da trindade", 10, 12, 1000000, 1000000);
        result = instance.getParkReport(park, vl, list);
        expList.clear();
        expList.add("ePT02;20;5333\n" );
        expList.add("ePT03;100;0\n");
        expList.add("ePT04;100;0\n");
        expList.add("ePT05;100;0\n");
        assertEquals(expList, list);
        
    }
}
