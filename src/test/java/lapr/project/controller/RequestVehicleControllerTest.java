/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleRequest;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class RequestVehicleControllerTest {
    
    RequestVehicleController reqVehController;
    VehicleRequestsDB vrdb;
    VehicleDB vdb;
    ParkDB pdb;
    ScooterDB sdb;
    
    /**
     * Test of unlockVehicle method, of class RequestVehicleController.
     */
    @Test
    public void testUnlockVehicle() throws SQLException, InvalidInfoClientException {
        System.out.println("testGetAvailableScooters");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');
        reqVehController = new RequestVehicleController();
        vrdb = mock(VehicleRequestsDB.class); 
        vdb = mock(VehicleDB.class);
        pdb = mock(ParkDB.class);
        sdb = mock(ScooterDB.class);
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,"","") ;
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(300000);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(3);
        ss2.setMaxBatteryCapacity(300);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Scooter ss3 = new Scooter();
        ss3.setID(5);
        ss3.setIDPark(4);
        ss3.setMaxBatteryCapacity(400);
        ss3.setActualBatteryCapacity(100);
        ss3.setMotor(250);
        Scooter ss4 = new Scooter();
        ss4.setID(142);
        ss4.setIDPark(3);
        ss4.setMaxBatteryCapacity(30);
        ss4.setActualBatteryCapacity(100);
        ss4.setMotor(250);

        sl.add(ss1);

        sl.add(ss4);

        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Bolhao", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Clerigos", 23.009, 44.009, 10, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        when(sdb.getScootersByParkID(4)).thenReturn(sl);
        when(vdb.unlockVehicle(2)).thenReturn(true);
        
        Set<List<Path>> lpaths = new HashSet<>();
        ShortestPathController instance = new ShortestPathController();

        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Mac", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        when(pr.getAllParks()).thenReturn(pl);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        when(por.getExistingPOI()).thenReturn(po);
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 1, 3, 1, 20, 30));
        pa.add(new Path(2, 3, 2, 1, 20, 30));
        when(par.getPath(1, 3)).thenReturn(new Path(3, 1, 3, 1, 20, 30));
        when(par.getPath(3, 2)).thenReturn(new Path(4, 3, 2, 1, 20, 30));
        when(par.getAllPaths()).thenReturn(pa);
        instance.setRegistrys(pr, por, par);
        when(pr.getParkDB("Porto")).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkDB("Serralves")).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        List<String> report = new ArrayList<>();

        Set<List<Path>> expPaths = new HashSet<>();
        List<Path> listPaths= new LinkedList<>();
        listPaths.add(new Path(1, 1, 3, 1, 20, 30));
        listPaths.add(new Path(2, 3, 2, 1, 20, 30));
        expPaths.add(listPaths);

        when(pr.getParkByCoordinates(23.001, 43.001)).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkByCoordinates(23.003, 43.003)).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        lpaths.clear();
        report.clear();
        
        when(pr.validatePark("Porto")).thenReturn(true);
        when(pr.getParkDB("Porto")).thenReturn(p3);
        when(pr.validateCoordinates(23.003,43.003)).thenReturn(true);
        
        ClientDB cdb = mock(ClientDB.class);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        reqVehController.setDB(vdb, vrdb, sdb,instance,cdb, mock(ParkDB.class));
        reqVehController.setRegistry(pr);
        Set<Scooter> expResult = new HashSet<>();
        expResult.add(ss1);

        Set<Scooter> result = reqVehController.getAvailableScooter("Porto",23.003,43.003);
       
        assertEquals(expResult, result);
    }
   
    /**
     * Test of unlockVehicle method, of class RequestVehicleController.
     */
    @Test
    public void testgetVehicleMostEnergy() throws SQLException, InvalidInfoClientException {
        System.out.println("testGetAvailableScooters");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');
        reqVehController = new RequestVehicleController();
        vrdb = mock(VehicleRequestsDB.class); 
        vdb = mock(VehicleDB.class);
        pdb = mock(ParkDB.class);
        sdb = mock(ScooterDB.class);
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,"","") ;
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(300000);
        ss1.setActualBatteryCapacity(60);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(3);
        ss2.setMaxBatteryCapacity(300);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Scooter ss3 = new Scooter();
        ss3.setID(5);
        ss3.setIDPark(4);
        ss3.setMaxBatteryCapacity(400);
        ss3.setActualBatteryCapacity(100);
        ss3.setMotor(250);
        Scooter ss4 = new Scooter();
        ss4.setID(142);
        ss4.setIDPark(3);
        ss4.setMaxBatteryCapacity(30);
        ss4.setActualBatteryCapacity(100);
        ss4.setMotor(250);

        sl.add(ss1);

        sl.add(ss3);

        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Bolhao", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Clerigos", 23.009, 44.009, 10, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        when(sdb.getScootersByParkID(4)).thenReturn(sl);
        when(sdb.getAllAvailableScooters()).thenReturn(sl);
        when(vdb.unlockVehicle(2)).thenReturn(true);
        
        Set<List<Path>> lpaths = new HashSet<>();
        ShortestPathController instance = new ShortestPathController();

        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Mac", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        when(pr.getAllParks()).thenReturn(pl);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        when(por.getExistingPOI()).thenReturn(po);
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 1, 3, 1, 20, 30));
        pa.add(new Path(2, 3, 2, 1, 20, 30));
        when(par.getPath(1, 3)).thenReturn(new Path(3, 1, 3, 1, 20, 30));
        when(par.getPath(3, 2)).thenReturn(new Path(4, 3, 2, 1, 20, 30));
        when(par.getAllPaths()).thenReturn(pa);
        instance.setRegistrys(pr, por, par);
        when(pr.getParkDB("Porto")).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkDB("Serralves")).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        List<String> report = new ArrayList<>();

        Set<List<Path>> expPaths = new HashSet<>();
        List<Path> listPaths= new LinkedList<>();
        listPaths.add(new Path(1, 1, 3, 1, 20, 30));
        listPaths.add(new Path(2, 3, 2, 1, 20, 30));
        expPaths.add(listPaths);

        when(pr.getParkByCoordinates(23.001, 43.001)).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkByCoordinates(23.003, 43.003)).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        lpaths.clear();
        report.clear();
        
        when(pr.validatePark("Porto")).thenReturn(true);
        when(pr.getParkDB("Porto")).thenReturn(p3);
        when(pr.validateCoordinates(23.003,43.003)).thenReturn(true);
        
        ClientDB cdb = mock(ClientDB.class);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(vdb.unlockVehicle(5)).thenReturn(true);
        when(vrdb.addRequest(new VehicleRequest(0,"joaolealmgs3@gmail.com",5,4,0,Instant.now().toString(),""))).thenReturn(true);
        
        reqVehController.setDB(vdb, vrdb, sdb,instance,cdb, mock(ParkDB.class));
        reqVehController.setRegistry(pr);

        Scooter result = reqVehController.getAvailableVehicleMost("Porto", "joaolealmgs3@gmail.com");
        assertEquals(ss3, result);
        
    }
    
    
    /**
    * Test of unlockVehicle method, of class RequestVehicleController.
    */
    @Test
    public void testgetPark() throws SQLException, InvalidInfoClientException {
        System.out.println("testGetPark");
        vrdb = mock(VehicleRequestsDB.class); 
        vdb = mock(VehicleDB.class);
        pdb = mock(ParkDB.class);
        sdb = mock(ScooterDB.class);
        ClientDB cdb = mock(ClientDB.class);
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        
        when(pdb.getPark(4)).thenReturn(p3);
        RequestVehicleController instance = new RequestVehicleController();        
        instance.setDB(vdb, vrdb, sdb, new ShortestPathController(), cdb, pdb);
        Park result = instance.getPark(4);
        assertEquals(result, p3);
    }
    
    /**
     * Test of unlockVehicle method, of class RequestVehicleController.
     */
    @Test
    public void testUnlockVehicle3() throws SQLException, InvalidInfoClientException {
        System.out.println("testGetAvailableScooters");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');
        reqVehController = new RequestVehicleController();
        vrdb = mock(VehicleRequestsDB.class); 
        vdb = mock(VehicleDB.class);
        pdb = mock(ParkDB.class);
        sdb = mock(ScooterDB.class);
        VehicleRequest vReq = new VehicleRequest(0,"joaolealmgs3@gmail.com",2,4,1,"","") ;
        Set<Scooter> sl = new HashSet<>();
        Scooter ss1 = new Scooter();
        ss1.setID(1);
        ss1.setIDPark(4);
        ss1.setMaxBatteryCapacity(300000);
        ss1.setActualBatteryCapacity(100);
        ss1.setMotor(250);
        Scooter ss2 = new Scooter();
        ss2.setID(100);
        ss2.setIDPark(3);
        ss2.setMaxBatteryCapacity(300);
        ss2.setActualBatteryCapacity(100);
        ss2.setMotor(250);
        Scooter ss3 = new Scooter();
        ss3.setID(5);
        ss3.setIDPark(4);
        ss3.setMaxBatteryCapacity(400);
        ss3.setActualBatteryCapacity(100);
        ss3.setMotor(250);
        Scooter ss4 = new Scooter();
        ss4.setID(142);
        ss4.setIDPark(3);
        ss4.setMaxBatteryCapacity(10);
        ss4.setActualBatteryCapacity(100);
        ss4.setMotor(250);

        sl.add(ss1);

        sl.add(ss4);

        Set<Park> h = new HashSet<>();
        Park p1 = (new Park(1,"Bolhao", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201)); 
        Park p2 = (new Park(2,"Clerigos", 23.009, 44.009, 10, "Parque do Porto longe", 10, 10, 200, 201));
        Park p3 = (new Park(4,"Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        h.add(p1);
        h.add(p2);
        h.add(p3);
        
        when(sdb.getScootersByParkID(4)).thenReturn(sl);
        when(vdb.unlockVehicle(1)).thenReturn(true);
        when(vrdb.addRequest(vReq)).thenReturn(true);
        Set<List<Path>> lpaths = new HashSet<>();
        ShortestPathController instance = new ShortestPathController();

        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Mac", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        when(pr.getAllParks()).thenReturn(pl);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        when(por.getExistingPOI()).thenReturn(po);
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 1, 3, 1, 20, 30));
        pa.add(new Path(2, 3, 2, 1, 20, 30));
        when(par.getPath(1, 3)).thenReturn(new Path(3, 1, 3, 1, 20, 30));
        when(par.getPath(3, 2)).thenReturn(new Path(4, 3, 2, 1, 20, 30));
        when(par.getAllPaths()).thenReturn(pa);
        instance.setRegistrys(pr, por, par);
        when(pr.getParkDB("Porto")).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkDB("Serralves")).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        List<String> report = new ArrayList<>();

        Set<List<Path>> expPaths = new HashSet<>();
        List<Path> listPaths= new LinkedList<>();
        listPaths.add(new Path(1, 1, 3, 1, 20, 30));
        listPaths.add(new Path(2, 3, 2, 1, 20, 30));
        expPaths.add(listPaths);

        when(pr.getParkByCoordinates(23.001, 43.001)).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkByCoordinates(23.003, 43.003)).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        lpaths.clear();
        report.clear();
        
        when(pr.validatePark("Porto")).thenReturn(true);
        when(pr.getParkDB("Porto")).thenReturn(p3);
        when(pr.validateCoordinates(23.003,43.003)).thenReturn(true);
        when(sdb.getAllAvailableScooters()).thenReturn(sl);
        ClientDB cdb = mock(ClientDB.class);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        reqVehController.setDB(vdb, vrdb, sdb,instance,cdb, mock(ParkDB.class));
        reqVehController.setRegistry(pr);
 
        Scooter result = reqVehController.unlockAScooterAtParkForDestination("Porto", "joaolealmgs3@gmail.com",23.003,43.003);

       
        assertEquals(ss1, result);
    }
    
}
