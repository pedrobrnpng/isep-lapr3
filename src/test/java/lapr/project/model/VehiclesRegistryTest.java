/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author bruno
 */
@RunWith(MockitoJUnitRunner.class)
public class VehiclesRegistryTest {

    private BicycleDB bdb;
    private ScooterDB sdb;
    private VehicleDB vdb;
    private VehiclesRegistry instance;

    @BeforeEach
    public void setUp() throws Exception {
        instance = new VehiclesRegistry();
        bdb = mock(BicycleDB.class);
        sdb = mock(ScooterDB.class);
        vdb = mock(VehicleDB.class);
        instance.setBicycleDB(bdb);
        instance.setScooterDB(sdb);
        instance.setVehicleDB(vdb);
    }

    /**
     * Test of registerScooter method, of class VehiclesRegistry.
     */
    @Test
    public void testRegisterScooter() {
        System.out.println("registerScooter");
        int id = 1;
        int idPark = 1;
        int maxBatteryCapacity = 1;
        int actualBatteryCapacity = 1;
        String scooterType = "off-road";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        int motor = 1;
        try {
            instance.addScooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
            instance.registerScooters();
        } catch (Exception e) {
            fail("registerScooter failed: must have passed");
        }
    }

    /**
     * Test of registerBicycle method, of class VehiclesRegistry.
     */
    @Test
    public void testRegisterBicycle() {
        System.out.println("registerBicycle");
        int id = 1;
        int idPark = 1;
        String size = "9";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        try {
            instance.addBicycle(size, id, idPark, weight, aerodynamicCoefficient, frontalArea);
            instance.registerBicycles();
        } catch (Exception e) {
            fail("registerBicycle failed: must have passed");
        }

    }

    /**
     * Test of setScooterDB method, of class VehiclesRegistry.
     */
    @Test
    public void testSetScooterDB() {
        System.out.println("setScooterDB");
        instance.setScooterDB(sdb);
    }

    /**
     * Test of setBicycleDB method, of class VehiclesRegistry.
     */
    @Test
    public void testSetBicycleDB() {
        System.out.println("setBicycleDB");
        instance.setBicycleDB(bdb);
    }

    /**
     * Test of changeVehicle method, of class VehiclesRegistry.
     */
    @Test
    public void testChangeVehicle_9args() throws Exception {
        System.out.println("changeVehicle");
        int id = 0;
        int idPark = -3;
        int maxBatteryCapacity = 1;
        int actualBatteryCapacity = 1;
        String scooterType = null;
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        int motor = 1;
        try {
            instance.changeVehicle(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
            fail("Must not pass");
        } catch (IllegalArgumentException | SQLException sQLException) {

        }
    }

    /**
     * Test of changeVehicle method, of class VehiclesRegistry.
     */
    @Test
    public void testChangeVehicle_6args() throws Exception {
        System.out.println("changeVehicle");
        int id = 0;
        int idPark = -1;
        String size = "";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        try {
            instance.changeVehicle(size, id, idPark, weight, aerodynamicCoefficient, frontalArea);
            fail("Must not pass");
        } catch (IllegalArgumentException | SQLException sQLException) {
        }
    }

    /**
     * Test of removeVehicle method, of class VehiclesRegistry.
     */
    @Test
    public void testRemoveVehicle() throws SQLException {
        System.out.println("removeVehicle");
        int id = 1;
        Scooter s = new Scooter("19", id, id, id, id, id, id, id, id);
        instance.addScooter(s.getType(), s.getMaxBatteryCapacity(), s.getActualBatteryCapacity(), s.getID(), s.getIDPark(), s.getWeight(), s.getAerodynamicCoefficient(), s.getFrontalArea(), s.getMotor());
        instance.removeVehicle(id);
        Bicycle b = new Bicycle("19", id, id, id, id, id);
        instance.addBicycle(b.getWheelSize(), b.getID(), b.getIDPark(), b.getWeight(), b.getAerodynamicCoefficient(), b.getFrontalArea());
        instance.removeVehicle(id);
    }

    /**
     * Test of setVehicleDB method, of class VehiclesRegistry.
     */
    @Test
    public void testSetVehicleDB() {
        System.out.println("setVehicleDB");
        VehicleDB vdb = null;
        VehiclesRegistry instance = new VehiclesRegistry();
        instance.setVehicleDB(vdb);
    }

    /**
     * Test of getVehicle method, of class VehiclesRegistry.
     */
    @Test
    public void testGetVehicle() throws SQLException {
        System.out.println("getVehicle");
        int id = 1;
        int idPark = 2;
        int maxBatteryCapacity = 1;
        int actualBatteryCapacity = 1;
        String scooterType = "cenas";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        int motor = 1;
        instance.addScooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        Vehicle expResult = null;
        Vehicle result = instance.getVehicle(2);
        assertEquals(expResult, result);
        expResult = new Scooter(scooterType, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        result = instance.getVehicle(1);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAllVehicles method, of class VehiclesRegistry.
     */
    @Test
    public void testGetAllVehicles() throws Exception {
        System.out.println("getAllVehicles");
        when(vdb.getAllVehicles()).thenReturn(new HashSet<>());
        HashSet<Vehicle> expResult = new HashSet<>();
        HashSet<Vehicle> result = (HashSet<Vehicle>) instance.getAllVehicles();
        assertEquals(expResult, result);
    }

    /**
     * Test of receiveScooterAutonomyReport method, of class VehiclesRegistry.
     */
    @Test
    public void testReceiveScooterAutonomyReport() throws Exception {
        System.out.println("receiveScooterAutonomyReport");

        when(sdb.getAllAvailableScooters()).thenReturn(null);

        Set<Scooter> expResult = null;
        Set<Scooter> result = instance.receiveScooterAutonomyReport();
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateAvailability() throws Exception {
        System.out.println("updateAvailability");
        VehiclesRegistry instanceReg = new VehiclesRegistry();
        VehicleDB vdb = mock(VehicleDB.class);
        instanceReg.setVehicleDB(vdb);
        instanceReg.updateAvailability(1);
    }

    /**
     * Test of getVehiclesByPark method, of class VehiclesRegistry.
     */
    @Test
    public void testGetVehiclesByPark() {
        System.out.println("getVehiclesByPark");
        int idPark = 1;
        VehiclesRegistry vr = new VehiclesRegistry();
        VehicleDB vdb = mock(VehicleDB.class);
        vr.setVehicleDB(vdb);
        Set<Scooter> expResult = new HashSet<>();
        expResult.add(new Scooter());
        when(vdb.getVehiclesByPark(1)).thenReturn(expResult);
        Set<Scooter> result = vr.getVehiclesByPark(idPark);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleInPark method, of class VehiclesRegistry.
     */
    @Test
    public void testGetVehicleInPark() throws Exception {
        System.out.println("getVehicleInPark");
        int idPark = 3;
        Set<Vehicle> expResult = new HashSet<>();
        Set<Vehicle> expResult1 = new HashSet<>();
        expResult.add(new Scooter("off", idPark, idPark, idPark, idPark, idPark, idPark, idPark, idPark));
        expResult1.add(new Scooter("off", idPark, idPark, idPark, idPark, idPark, idPark, idPark, idPark));
        expResult1.add(new Scooter("off", 2, 2, 2, 2, 2, 2, 2, 2));
        when(instance.getVehicleInPark(idPark)).thenReturn(expResult);
        Set<Vehicle> result = instance.getVehicleInPark(idPark);
        assertEquals(expResult, result);
        when(instance.getVehicleInPark(idPark)).thenReturn(expResult1);
        result = instance.getVehicleInPark(idPark);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerScooters method, of class VehiclesRegistry.
     */
    @org.junit.Test
    public void testRegisterScooters() throws Exception {
        System.out.println("registerScooters");
        VehiclesRegistry instance = new VehiclesRegistry();
        instance.registerScooters();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerBicycles method, of class VehiclesRegistry.
     */
    @org.junit.Test
    public void testRegisterBicycles() throws Exception {
        System.out.println("registerBicycles");
        VehiclesRegistry instance = new VehiclesRegistry();
        instance.registerBicycles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addScooter method, of class VehiclesRegistry.
     */
    @org.junit.Test
    public void testAddScooter() {
        System.out.println("addScooter");
        String type = "";
        int maxBatteryCapacity = 0;
        int actualBatteryCapacity = 0;
        int id = 0;
        int idPark = 0;
        int weight = 0;
        double aerodynamicCoefficient = 0.0;
        double frontalArea = 0.0;
        int motor = 0;
        VehiclesRegistry instance = new VehiclesRegistry();
        boolean expResult = false;
        boolean result = instance.addScooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBicycle method, of class VehiclesRegistry.
     */
    @org.junit.Test
    public void testAddBicycle() {
        System.out.println("addBicycle");
        String wheelSize = "";
        int id = 0;
        int idPark = 0;
        int weight = 0;
        double aerodynamicCoefficient = 0.0;
        double frontalArea = 0.0;
        VehiclesRegistry instance = new VehiclesRegistry();
        boolean expResult = false;
        boolean result = instance.addBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chargeVehicles method, of class VehiclesRegistry.
     */
    @org.junit.Test
    public void testChargeVehicles() throws Exception {
        System.out.println("chargeVehicles");
        VehiclesRegistry instance = new VehiclesRegistry();
        instance.chargeVehicles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
