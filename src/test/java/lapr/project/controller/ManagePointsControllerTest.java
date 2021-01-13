/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleRequest;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class ManagePointsControllerTest {

    public ManagePointsControllerTest() {
    }

    /**
     * Test of checkParking method, of class ManagePointsController.
     */
    @Test
    public void testCheckParking1() throws Exception {
        System.out.println("checkParking");
        
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        ClientDB cdb = mock(ClientDB.class);
        ParkDB pdb = mock(ParkDB.class);
        POIDB poidb = mock(POIDB.class);
        
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(1);
        ss1.setMaxBatteryCapacity(12);
        ss1.setActualBatteryCapacity(12);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(2);
        ss2.setMaxBatteryCapacity(123);
        ss2.setActualBatteryCapacity(100);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        bb1.setIDPark(1);
        Bicycle bb2 = new Bicycle();
        bb2.setID(101);
        bb2.setIDPark(2);
        sl.add(ss1);
        sl.add(ss2);
        sl.add(bb1);
        sl.add(bb2);
        
        
        int vReqID = 4;
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(16, ChronoUnit.MINUTES).toString();
        
        VehicleRequest vReq = new VehicleRequest(4,"joaolealmgs3@gmail.com",2,1,2,time,time2) ;
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Park> pl = new HashSet<>();
        Park pOrig = (new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        Park pDest = (new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        pl.add(pDest);
        pl.add(pOrig);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidb.getExistingPOI()).thenReturn(po);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(vrdb.getVehicleRequest(vReqID)).thenReturn(vReq);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.getAllVehicles()).thenReturn(sl);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(poidb.getExistingPOI()).thenReturn(po);
        
        ManagePointsController instance = new ManagePointsController();
        instance.setDB(vrdb, cdb, vdb, pdb, poidb);

        instance.checkParking(vReqID);
        
        int expResult = 15;
        int result = cli.getPoints();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkParking method, of class ManagePointsController.
     */
    @Test
    public void testCheckParking2() throws Exception {
        System.out.println("checkParking");
        
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        ClientDB cdb = mock(ClientDB.class);
        ParkDB pdb = mock(ParkDB.class);
        POIDB poidb = mock(POIDB.class);
        
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(1);
        ss1.setMaxBatteryCapacity(12);
        ss1.setActualBatteryCapacity(12);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(2);
        ss2.setMaxBatteryCapacity(123);
        ss2.setActualBatteryCapacity(100);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        bb1.setIDPark(1);
        Bicycle bb2 = new Bicycle();
        bb2.setID(101);
        bb2.setIDPark(2);
        sl.add(ss1);
        sl.add(ss2);
        sl.add(bb1);
        sl.add(bb2);
        
        
        int vReqID = 4;
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(16, ChronoUnit.MINUTES).toString();
        
        VehicleRequest vReq = new VehicleRequest(4,"joaolealmgs3@gmail.com",2,1,2,time,time2) ;
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Park> pl = new HashSet<>();
        Park pOrig = (new Park(1, "Porto", 23, 23, 25, "Parque do Porto", 23, 23, 260, 120));
        Park pDest = (new Park(2, "Trindade", 80.15227, -10.60929, 50, "Parque da Trindade", 10, 5, 220, 16));
        pl.add(pDest);
        pl.add(pOrig);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidb.getExistingPOI()).thenReturn(po);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(vrdb.getVehicleRequest(vReqID)).thenReturn(vReq);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.getAllVehicles()).thenReturn(sl);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(poidb.getExistingPOI()).thenReturn(po);
        
        ManagePointsController instance = new ManagePointsController( );
        instance.setDB(vrdb, cdb, vdb, pdb, poidb);

        instance.checkParking(vReqID);
        
        int expResult = 5;
        int result = cli.getPoints();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkParking method, of class ManagePointsController.
     */
    @Test
    public void testCheckParking3() throws Exception {
        System.out.println("checkParking");
        
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        ClientDB cdb = mock(ClientDB.class);
        ParkDB pdb = mock(ParkDB.class);
        POIDB poidb = mock(POIDB.class);
        
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(1);
        ss1.setMaxBatteryCapacity(12);
        ss1.setActualBatteryCapacity(12);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(2);
        ss2.setMaxBatteryCapacity(123);
        ss2.setActualBatteryCapacity(100);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        bb1.setIDPark(1);
        Bicycle bb2 = new Bicycle();
        bb2.setID(101);
        bb2.setIDPark(2);
        sl.add(ss1);
        sl.add(ss2);
        sl.add(bb1);
        sl.add(bb2);
        
        
        int vReqID = 4;
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(10, ChronoUnit.MINUTES).toString();
        
        VehicleRequest vReq = new VehicleRequest(4,"joaolealmgs3@gmail.com",2,1,2,time,time2) ;
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Park> pl = new HashSet<>();
        Park pOrig = (new Park(1, "Porto", 23, 23, 25, "Parque do Porto", 23, 23, 260, 120));
        Park pDest = (new Park(2, "Trindade", 80.15227, -10.60929, 30, "Parque da Trindade", 10, 5, 220, 16));
        pl.add(pDest);
        pl.add(pOrig);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidb.getExistingPOI()).thenReturn(po);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(vrdb.getVehicleRequest(vReqID)).thenReturn(vReq);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.getAllVehicles()).thenReturn(sl);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(poidb.getExistingPOI()).thenReturn(po);
        
        ManagePointsController instance = new ManagePointsController();
        instance.setDB(vrdb, cdb, vdb, pdb, poidb);

        instance.checkParking(vReqID);
        
        int expResult = 5;
        int result = cli.getPoints();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkParking method, of class ManagePointsController.
     */
    @Test
    public void testCheckParking4() throws Exception {
        System.out.println("checkParking");
        
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        ClientDB cdb = mock(ClientDB.class);
        ParkDB pdb = mock(ParkDB.class);
        POIDB poidb = mock(POIDB.class);
        
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(1);
        ss1.setMaxBatteryCapacity(12);
        ss1.setActualBatteryCapacity(12);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(2);
        ss2.setMaxBatteryCapacity(123);
        ss2.setActualBatteryCapacity(100);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        Bicycle bb2 = new Bicycle();
        bb2.setID(101);
        bb2.setIDPark(2);
        sl.add(ss1);
        sl.add(ss2);
        sl.add(bb1);
        sl.add(bb2);
        
        
        int vReqID = 4;
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(16, ChronoUnit.MINUTES).toString();
        
        VehicleRequest vReq = new VehicleRequest(4,"joaolealmgs3@gmail.com",2,1,2,time,time2) ;
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Park> pl = new HashSet<>();
        Park pOrig = (new Park(1, "Porto", 23, 23, 25, "Parque do Porto", 23, 23, 260, 120));
        Park pDest = (new Park(2, "Trindade", 80.15227, -10.60929, 30, "Parque da Trindade", 10, 5, 220, 16));
        pl.add(pDest);
        pl.add(pOrig);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidb.getExistingPOI()).thenReturn(po);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(vrdb.getVehicleRequest(vReqID)).thenReturn(vReq);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.getAllVehicles()).thenReturn(sl);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(poidb.getExistingPOI()).thenReturn(po);
        
        ManagePointsController instance = new ManagePointsController( );
        instance.setDB(vrdb, cdb, vdb, pdb, poidb);

        instance.checkParking(vReqID);
        
        int expResult = -15;
        int result = cli.getPoints();

        assertEquals(expResult, result);
    }
    
    /**
     * Test of checkParking method, of class ManagePointsController.
     */
    @Test
    public void testCheckParking5() throws Exception {
        System.out.println("checkParking");
        
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        ClientDB cdb = mock(ClientDB.class);
        ParkDB pdb = mock(ParkDB.class);
        POIDB poidb = mock(POIDB.class);
        
        HashSet<Vehicle> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(1);
        ss1.setMaxBatteryCapacity(12);
        ss1.setActualBatteryCapacity(12);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(2);
        ss2.setMaxBatteryCapacity(123);
        ss2.setActualBatteryCapacity(100);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        bb1.setIDPark(1);
        Bicycle bb2 = new Bicycle();
        bb2.setID(101);
        bb2.setIDPark(2);
        sl.add(ss1);
        sl.add(ss2);
        sl.add(bb1);
        sl.add(bb2);
        
        
        int vReqID = 4;
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(10, ChronoUnit.MINUTES).toString();
        
        VehicleRequest vReq = new VehicleRequest(4,"joaolealmgs3@gmail.com",2,1,2,time,time2) ;
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Park> pl = new HashSet<>();
        Park pOrig = (new Park(1, "Porto", 23, 23, 25, "Parque do Porto", 23, 23, 260, 120));
        Park pDest = (new Park(2, "Trindade", 80.15227, -10.60929, 400, "Parque da Trindade", 10, 5, 220, 16));
        pl.add(pDest);
        pl.add(pOrig);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidb.getExistingPOI()).thenReturn(po);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(vrdb.getVehicleRequest(vReqID)).thenReturn(vReq);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.getAllVehicles()).thenReturn(sl);
        when(pdb.getExistingParks()).thenReturn(pl);
        when(poidb.getExistingPOI()).thenReturn(po);
        
        ManagePointsController instance = new ManagePointsController();
        instance.setDB(vrdb, cdb, vdb, pdb, poidb);

        instance.checkParking(vReqID);
        
        int expResult = 20;
        int result = cli.getPoints();
        
        assertEquals(expResult, result);
    }
    
}
