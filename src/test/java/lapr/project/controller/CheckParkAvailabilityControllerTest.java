/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author pedro
 */
public class CheckParkAvailabilityControllerTest {

    private CheckParkAvailabilityController controller;

    public CheckParkAvailabilityControllerTest() {
        controller = new CheckParkAvailabilityController();
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

@Test
    public void testCheckAvailability3() {
        System.out.println("checkAvailability");
        int parkID = 4;
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 5;
        int maxNumberScooter = 1;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        Park location = new Park(parkID,"Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        Bicycle vehicle = new Bicycle();
        vehicle.setID(2);
        vehicle.setIDPark(2);
        vehicle.setWheelSize("mtb");
        ScooterDB sdb = mock(ScooterDB.class);
        BicycleDB bdb = mock(BicycleDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        ParkDB pdb = mock(ParkDB.class);
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter s = new Scooter();
        s.setID(1);
        s.setIDPark(4);
        s.setMaxBatteryCapacity(12);
        s.setActualBatteryCapacity(12);
        s.setType("scooter");
        sl.add(s);
        HashSet<Vehicle> b1 = new HashSet<>();
        Bicycle b = new Bicycle();
        b.setID(2);
        b.setIDPark(4);
        b.setWheelSize("bicycle");
        b1.add(b);
        Instant now = Instant.parse("2020-01-08T22:08:44.754Z");
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,now.toString(),"") ;
        Set<VehicleRequest> lv = new HashSet<>();
        lv.add(vReq);
        when(sdb.getScooters(location)).thenReturn(sl);
        when(bdb.getBicycles(location)).thenReturn(b1);
        when(pdb.getPark("Porto")).thenReturn(location);
        when(vrdb.getActiveRequestsGivenTime(now.toString())).thenReturn(lv);

        controller.setDB(sdb, bdb, pdb, vrdb, vdb, now);
        
        Set<Park> pl = new HashSet<>();
        pl.add(location);
        int expResult = 4;
        int result = controller.getFreeSlots("Porto", "joaolealmgs3@gmail.com");
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckAvailability2() {
        System.out.println("checkAvailability");
        int parkID = 4;
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 1;
        int maxNumberScooter = 7;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        Park location = new Park(parkID,"Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        Bicycle vehicle = new Bicycle();
        vehicle.setID(2);
        vehicle.setIDPark(2);
        vehicle.setWheelSize("mtb");
        ScooterDB sdb = mock(ScooterDB.class);
        BicycleDB bdb = mock(BicycleDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        ParkDB pdb = mock(ParkDB.class);
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter s = new Scooter();
        s.setID(1);
        s.setIDPark(4);
        s.setMaxBatteryCapacity(12);
        s.setActualBatteryCapacity(12);
        s.setType("scooter");
        sl.add(s);
        HashSet<Vehicle> b1 = new HashSet<>();
        Bicycle b = new Bicycle();
        b.setID(2);
        b.setIDPark(4);
        b.setWheelSize("bicycle");
        b1.add(b);
        Instant now = Instant.parse("2020-01-08T22:08:44.754Z");
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",4,4,1,now.toString(),"") ;
        Set<VehicleRequest> lv = new HashSet<>();
        lv.add(vReq);
        when(sdb.getScooters(location)).thenReturn(sl);
        when(bdb.getBicycles(location)).thenReturn(b1);
        when(pdb.getPark("Porto")).thenReturn(location);
        when(vrdb.getActiveRequestsGivenTime(now.toString())).thenReturn(lv);

        controller.setDB(sdb, bdb, pdb, vrdb, vdb, now);
        
        Set<Park> pl = new HashSet<>();
        pl.add(location);
        int expResult = 6;
        int result = controller.getFreeSlots("Porto", "joaolealmgs3@gmail.com");
        System.out.println(result);
        assertEquals(expResult, result);
    }
  
@Test
    public void testCheckAvailability6() {
        System.out.println("checkAvailability");
        int parkID = 4;
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 5;
        int maxNumberScooter = 1;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        Park location = new Park(parkID,"Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        Bicycle vehicle = new Bicycle();
        vehicle.setID(2);
        vehicle.setIDPark(2);
        vehicle.setWheelSize("mtb");
        ScooterDB sdb = mock(ScooterDB.class);
        BicycleDB bdb = mock(BicycleDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        ParkDB pdb = mock(ParkDB.class);
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter s = new Scooter();
        s.setID(1);
        s.setIDPark(4);
        s.setMaxBatteryCapacity(12);
        s.setActualBatteryCapacity(12);
        s.setType("scooter");
        sl.add(s);
        HashSet<Vehicle> b1 = new HashSet<>();
        Bicycle b = new Bicycle();
        b.setID(2);
        b.setIDPark(4);
        b.setWheelSize("bicycle");
        b1.add(b);
        Instant now = Instant.parse("2020-01-08T22:08:44.754Z");
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,now.toString(),"") ;
        Set<VehicleRequest> lv = new HashSet<>();
        lv.add(vReq);
        when(sdb.getScooters(location)).thenReturn(sl);
        when(bdb.getBicycles(location)).thenReturn(b1);
        when(pdb.getPark("Porto")).thenReturn(location);
        when(vrdb.getActiveRequestsGivenTime(now.toString())).thenReturn(lv);

        controller.setDB(sdb, bdb, pdb, vrdb, vdb, now);
        
        Set<Park> pl = new HashSet<>();
        pl.add(location);
        int expResult = 4;
        int result = controller.getFreeSlots("Porto", "joaolealmgs3@gmail.com");
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckAvailability4() {
        System.out.println("checkAvailability");
        int parkID = 4;
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 1;
        int maxNumberScooter = 1;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        Park location = new Park(parkID,"Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        Bicycle vehicle = new Bicycle();
        vehicle.setID(2);
        vehicle.setIDPark(2);
        vehicle.setWheelSize("mtb");
        ScooterDB sdb = mock(ScooterDB.class);
        BicycleDB bdb = mock(BicycleDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        ParkDB pdb = mock(ParkDB.class);
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter s = new Scooter();
        s.setID(1);
        s.setIDPark(4);
        s.setMaxBatteryCapacity(12);
        s.setActualBatteryCapacity(12);
        s.setType("scooter");
        sl.add(s);
        HashSet<Vehicle> b1 = new HashSet<>();
        Bicycle b = new Bicycle();
        b.setID(2);
        b.setIDPark(4);
        b.setWheelSize("bicycle");
        b1.add(b);
        Instant now = Instant.parse("2020-01-08T22:08:44.754Z");
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,now.toString(),"") ;
        Set<VehicleRequest> lv = new HashSet<>();
        lv.add(vReq);
        when(sdb.getScooters(location)).thenReturn(sl);
        when(bdb.getBicycles(location)).thenReturn(b1);
        when(pdb.getPark("Porto")).thenReturn(location);
        when(vrdb.getActiveRequestsGivenTime(now.toString())).thenReturn(lv);

        controller.setDB(sdb, bdb, pdb, vrdb, vdb, now);
        
        Set<Park> pl = new HashSet<>();
        pl.add(location);
        int expResult = 0;
        int result = controller.getFreeSlots("Porto", "joaolealmgs3@gmail.com");
        System.out.println(result);
        assertEquals(expResult, result);
    }
    


    /**
     * Test of getFreeSlots method, of class CheckParkAvailabilityController.
     */
    @Test
    public void testGetFreeSlots() {
        System.out.println("getFreeSlots");
        System.out.println(Instant.now().toString());
    }

}
