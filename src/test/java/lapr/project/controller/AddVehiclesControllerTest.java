/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author bruno
 */
@RunWith(MockitoJUnitRunner.class)
public class AddVehiclesControllerTest {

    private BicycleDB bdb;
    private ScooterDB sdb;
    private ParkDB pdb;
    private AddVehiclesController avc;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        bdb = mock(BicycleDB.class);
        sdb = mock(ScooterDB.class);
        pdb = mock(ParkDB.class);
        HashSet<Park> h = new HashSet<>();
        h.add(new Park(1, "Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        when(pdb.getExistingParks()).thenReturn(h);
        when(pdb.getParkByCoordinates(1, 1)).thenReturn(new Park(1, "Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        avc = new AddVehiclesController(pdb, sdb, bdb);
    }

    /**
     * Test of getAllParks method, of class AddVehiclesController.
     */
    @Test
    public void testGetAllParks() {
        System.out.println("getAllParks");
        Set<Park> expResult = new HashSet<>();
        expResult.add(new Park(1, "porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        Set<Park> result = avc.getAllParks();
        assertEquals(expResult, result);
    }

    /**
     * Test of addBicycle method, of class AddVehiclesController.
     */
    @Test
    public void testAddBicycle() throws SQLException {
        System.out.println("addBicycle");
        int id = 1;
        int idPark = 1;
        String wheelSize = "0";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        assertEquals(true, avc.addBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea));

        doThrow(SQLException.class).when(bdb).insertBicycle(any(Bicycle.class));

        assertEquals(false, avc.addBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea));

        id = -1;
        assertFalse(avc.addBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea));

    }

    /**
     * Test of addScooter method, of class AddVehiclesController.
     */
    @Test
    public void testAddScooter() throws SQLException {
        System.out.println("addScooter");
        int id = 1;
        int idPark = 1;
        int maxBatteryCapacity = 1;
        int actualBatteryCapacity = 1;
        String scooterType = "off-road";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        int motor = 1;
        assertTrue(avc.addScooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor));

        doThrow(SQLException.class).when(bdb).insertBicycle(any(Bicycle.class));

        assertTrue(avc.addScooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor));

        id = -1;
        assertFalse(avc.addScooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor));
    }

    /**
     * Test of getParkByCoordinates method, of class AddVehiclesController.
     */
    @Test
    public void testGetParkByCoordinates() {
        System.out.println("getParkByCoordinates");
        double parkLatitude = 23;
        double parkLongitude = 43;
        int expResult = 1;
        ParkRegistry pr=new ParkRegistry();
        Set<Park> parkList=new HashSet<>();
        parkList.add(new Park(1, "porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        pr.setParkList(parkList);
        AddVehiclesController instance=new AddVehiclesController();
        instance.setParkRegistry(pr);
        int result = instance.getParkByCoordinates(parkLatitude, parkLongitude);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParkRegistry method, of class AddVehiclesController.
     */
    @Test
    public void testsetParkRegistry() {
        System.out.println("getParkRegistry");
        ParkRegistry expResult = new ParkRegistry();
        pdb = mock(ParkDB.class);
        HashSet<Park> h = new HashSet<>();
        h.add(new Park(1, "Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        when(pdb.getExistingParks()).thenReturn(h);
        expResult.setParkDB(pdb);
        avc.setParkRegistry(expResult);
        assertEquals(expResult.getAllParks(), avc.getAllParks());
    }

}
