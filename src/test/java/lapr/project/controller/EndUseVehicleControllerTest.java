/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleRequest;
import lapr.project.model.Vehicle;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class EndUseVehicleControllerTest {
    

    
    public EndUseVehicleControllerTest() {
    }

    /**
     * Test of endUse method, of class EndUseVehicleController.
     */
    @Test
    public void testEndUse() throws InvalidInfoClientException, SQLException {
        System.out.println("endUse");
        
        Instant inst = Instant.parse("2020-01-07T11:23:30.356Z");
        String time = inst.toString();
        String time2 = inst.plus(6, ChronoUnit.MINUTES).toString();
                
        VehicleRequest v = new VehicleRequest(2,"joaolealmgs3@gmail.com",1,2,4,time,"") ;  
        
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Bicycle> bl = new HashSet<>();
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(350);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(4);
        ss2.setMaxBatteryCapacity(350);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        Bicycle bb2 = new Bicycle();
        bb1.setID(101);
        
        sl.add(ss1);
        sl.add(ss2);
        bl.add(bb1);
        bl.add(bb2);
        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Porto", 23.009, 44.009, 50, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        //ManagePoints
        VehicleRequestsDB vrdbm = mock(VehicleRequestsDB.class);
        VehicleDB vdbm = mock(VehicleDB.class);
        ClientDB cdbm = mock(ClientDB.class);
        ParkDB pdbm = mock(ParkDB.class);
        POIDB poidbm = mock(POIDB.class);
        
        HashSet<Vehicle> slist = new HashSet<>();
        slist.add(ss1);
        slist.add(ss2);
        slist.add(bb1);
        slist.add(bb2);
        
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidbm.getExistingPOI()).thenReturn(po);
        when(pdbm.getExistingParks()).thenReturn(h);
        when(vrdbm.getVehicleRequest(2)).thenReturn(v);
        when(cdbm.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdbm.getAllVehicles()).thenReturn(slist);
        
        ManagePointsController mpc = new ManagePointsController();
        mpc.setDB(vrdbm, cdbm, vdbm, pdbm, poidbm);
        
        ParkDB pdb = mock(ParkDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        BicycleDB bikeDB = mock(BicycleDB.class);
        ScooterDB scooDB = mock(ScooterDB.class);
        ClientDB cliDB = mock(ClientDB.class);

        
        when(bikeDB.getBicyclesByParkID(1)).thenReturn(bl);
        when(scooDB.getScootersByParkID(1)).thenReturn(sl);
        when(cliDB.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(pdb.getPark("Porto")).thenReturn(p1);
        when(vrdb.getVReqByEmailAndVeh(1, "joaolealmgs3@gmail.com")).thenReturn(v);
        
        when(vrdb.updateFinish(2,1,time2)).thenReturn(Boolean.TRUE);
        
        EndUseVehicleController usvc = new EndUseVehicleController() ;
        usvc.setDB(vrdb, vdb, pdb, scooDB, bikeDB, mpc, inst);

        usvc.newEndUse(142, "Porto", "joaolealmgs3@gmail.com");
        long result = usvc.endUse();
        long expResult = 0;
         assertEquals(expResult,result);
    }
    
    /**
     * Test of endUse method, of class EndUseVehicleController.
     */
    @Test
    public void testEndUseLocation() throws InvalidInfoClientException, SQLException {
        System.out.println("endUse");
        
        Instant inst = Instant.parse("2020-01-07T11:23:30.356Z");
        String time = inst.toString();
        String time2 = inst.plus(6, ChronoUnit.MINUTES).toString();
                
        VehicleRequest v = new VehicleRequest(2,"joaolealmgs3@gmail.com",1,2,4,time,"") ;  
        
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Bicycle> bl = new HashSet<>();
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(350);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(4);
        ss2.setMaxBatteryCapacity(350);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        Bicycle bb2 = new Bicycle();
        bb1.setID(101);
        
        sl.add(ss1);
        sl.add(ss2);
        bl.add(bb1);
        bl.add(bb2);
        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Porto", 23.009, 44.009, 50, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        //ManagePoints
        VehicleRequestsDB vrdbm = mock(VehicleRequestsDB.class);
        VehicleDB vdbm = mock(VehicleDB.class);
        ClientDB cdbm = mock(ClientDB.class);
        ParkDB pdbm = mock(ParkDB.class);
        POIDB poidbm = mock(POIDB.class);
        
        HashSet<Vehicle> slist = new HashSet<>();
        slist.add(ss1);
        slist.add(ss2);
        slist.add(bb1);
        slist.add(bb2);
        
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidbm.getExistingPOI()).thenReturn(po);
        when(pdbm.getExistingParks()).thenReturn(h);
        when(vrdbm.getVehicleRequest(2)).thenReturn(v);
        when(cdbm.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdbm.getAllVehicles()).thenReturn(slist);
        
        ManagePointsController mpc = new ManagePointsController();
        mpc.setDB(vrdbm, cdbm, vdbm, pdbm, poidbm);
        
        ParkDB pdb = mock(ParkDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        BicycleDB bikeDB = mock(BicycleDB.class);
        ScooterDB scooDB = mock(ScooterDB.class);
        ClientDB cliDB = mock(ClientDB.class);

        
        when(bikeDB.getBicyclesByParkID(1)).thenReturn(bl);
        when(scooDB.getScootersByParkID(1)).thenReturn(sl);
        when(cliDB.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(pdb.getParkByCoordinates(23.003, 43.003)).thenReturn(p1);
        when(vrdb.getVReqByEmailAndVeh(1, "joaolealmgs3@gmail.com")).thenReturn(v);
        
        when(vrdb.updateFinish(2,1,time2)).thenReturn(Boolean.TRUE);
        
        EndUseVehicleController usvc = new EndUseVehicleController() ;
        usvc.setDB(vrdb, vdb, pdb, scooDB, bikeDB, mpc, inst);

        usvc.newEndUseLocation(322, 23.003, 43.003, "joaolealmgs3@gmail.com");
        long result = usvc.endUse();
        long expResult = 0;
        assertEquals(expResult,result);
    }
    
    /**
     * Test of endUse method, of class EndUseVehicleController.
     */
    @Test
    public void testEndUse2() throws InvalidInfoClientException, SQLException {
        System.out.println("endUse");
        
        Instant inst = Instant.parse("2020-01-07T11:23:30.356Z");
        String time = inst.toString();
        String time2 = inst.plus(6, ChronoUnit.MINUTES).toString();
                
        VehicleRequest v = new VehicleRequest(2,"joaolealmgs3@gmail.com",1,2,4,time,"") ;  
        
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Bicycle> bl = new HashSet<>();
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(350);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(4);
        ss2.setMaxBatteryCapacity(350);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Bicycle bb1 = new Bicycle();
        bb1.setID(2);
        Bicycle bb2 = new Bicycle();
        bb1.setID(101);
        
        sl.add(ss1);
        sl.add(ss2);
        bl.add(bb1);
        bl.add(bb2);
        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Porto", 23.009, 44.009, 50, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        //ManagePoints
        VehicleRequestsDB vrdbm = mock(VehicleRequestsDB.class);
        VehicleDB vdbm = mock(VehicleDB.class);
        ClientDB cdbm = mock(ClientDB.class);
        ParkDB pdbm = mock(ParkDB.class);
        POIDB poidbm = mock(POIDB.class);
        
        HashSet<Vehicle> slist = new HashSet<>();
        slist.add(ss1);
        slist.add(ss2);
        slist.add(bb1);
        slist.add(bb2);
        
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidbm.getExistingPOI()).thenReturn(po);
        when(pdbm.getExistingParks()).thenReturn(h);
        when(vrdbm.getVehicleRequest(2)).thenReturn(v);
        when(cdbm.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdbm.getAllVehicles()).thenReturn(slist);
        
        ManagePointsController mpc = new ManagePointsController();
        mpc.setDB(vrdbm, cdbm, vdbm, pdbm, poidbm);
        
        ParkDB pdb = mock(ParkDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        BicycleDB bikeDB = mock(BicycleDB.class);
        ScooterDB scooDB = mock(ScooterDB.class);
        ClientDB cliDB = mock(ClientDB.class);

        when(pdb.getPark("Porto")).thenReturn(p1);
        when(bikeDB.getBicycles(p1)).thenReturn(slist);
        when(cliDB.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        
        when(vrdb.getVReqByEmailAndVeh(1, "joaolealmgs3@gmail.com")).thenReturn(v);
        
        when(vrdb.updateFinish(2,1,time2)).thenReturn(Boolean.TRUE);
        
        EndUseVehicleController usvc = new EndUseVehicleController() ;
        usvc.setDB(vrdb, vdb, pdb, scooDB, bikeDB, mpc, inst);

        usvc.newEndUse(ss1.getID(), "Porto", "joaolealmgs3@gmail.com");
        long result = usvc.endUse();
        long expResult = inst.toEpochMilli();
        assertEquals(expResult,result);
    }
    
    /**
     * Test of endUse method, of class EndUseVehicleController.
     */
    @Test
    public void testEndUseLocation2() throws InvalidInfoClientException, SQLException {
        System.out.println("endUse");
        
        Instant inst = Instant.parse("2020-01-07T11:23:30.356Z");
        String time = inst.toString();
        String time2 = inst.plus(6, ChronoUnit.MINUTES).toString();
                
        VehicleRequest v = new VehicleRequest(2,"joaolealmgs3@gmail.com",103,2,4,time,"") ;  
        
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');

        Set<Bicycle> bl = new HashSet<>();
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(103);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(350);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(4);
        ss2.setMaxBatteryCapacity(350);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Bicycle bb1 = new Bicycle();
        bb1.setID(102);
        Bicycle bb2 = new Bicycle();
        bb1.setID(101);
        
        sl.add(ss1);
        sl.add(ss2);
        bl.add(bb1);
        bl.add(bb2);
        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Porto", 23.009, 44.009, 50, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        //ManagePoints
        VehicleRequestsDB vrdbm = mock(VehicleRequestsDB.class);
        VehicleDB vdbm = mock(VehicleDB.class);
        ClientDB cdbm = mock(ClientDB.class);
        ParkDB pdbm = mock(ParkDB.class);
        POIDB poidbm = mock(POIDB.class);
        
        HashSet<Vehicle> slist = new HashSet<>();
        slist.add(ss1);
        slist.add(ss2);
        slist.add(bb1);
        slist.add(bb2);
        
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        
        when(poidbm.getExistingPOI()).thenReturn(po);
        when(pdbm.getExistingParks()).thenReturn(h);
        when(vrdbm.getVehicleRequest(2)).thenReturn(v);
        when(cdbm.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdbm.getAllVehicles()).thenReturn(slist);
        
        ManagePointsController mpc = new ManagePointsController();
        mpc.setDB(vrdbm, cdbm, vdbm, pdbm, poidbm);
        
        ParkDB pdb = mock(ParkDB.class);
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        VehicleDB vdb = mock(VehicleDB.class);
        BicycleDB bikeDB = mock(BicycleDB.class);
        ScooterDB scooDB = mock(ScooterDB.class);
        ClientDB cliDB = mock(ClientDB.class);

        
        when(bikeDB.getBicyclesByParkID(1)).thenReturn(bl);
        when(scooDB.getScootersByParkID(1)).thenReturn(sl);
        when(cliDB.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(pdb.getParkByCoordinates(23.003, 43.003)).thenReturn(p1);
        when(vrdb.getVReqByEmailAndVeh(103, "joaolealmgs3@gmail.com")).thenReturn(v);
        
        when(vrdb.updateFinish(2,1,time2)).thenReturn(Boolean.TRUE);
        
        EndUseVehicleController usvc = new EndUseVehicleController() ;
        usvc.setDB(vrdb, vdb, pdb, scooDB, bikeDB, mpc, inst);

        usvc.newEndUseLocation(103, 23.003, 43.003, "joaolealmgs3@gmail.com");
        long result = usvc.endUse();
        long expResult = inst.toEpochMilli();
        assertEquals(expResult,result);
    }
    
}
