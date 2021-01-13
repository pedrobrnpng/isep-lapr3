/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class VehicleRequestRegistryTest {
    
    public VehicleRequestRegistryTest() {
    }

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
     * Test of newVehicleRequest method, of class VehicleRequestRegistry.
     */
    @Test
    public void testNewVehicleRequest() {
        System.out.println("newVehicleRequest");
        int vehicleRequestID = 2;
        String userEmail = "pedro";
        int vehicleID = 3;
        int originID = 2;
        int destID = 5;
        int pathID = 3;
        String unlockTime = Instant.now().toString();
        String endTime = Instant.now().toString();
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
        VehicleRequest expResult = new VehicleRequest(vehicleRequestID, userEmail, vehicleID, originID, destID, unlockTime, endTime);
        VehicleRequest result = instance.newVehicleRequest(vehicleRequestID, userEmail, vehicleID, originID, destID, unlockTime, endTime);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class VehicleRequestRegistry.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        HashSet<VehicleRequest> vehicleReqList=new HashSet<>();
        vehicleReqList.add(new VehicleRequest(2,"pedro",3, 2, 3, Instant.now().toString(),Instant.now().toString()));
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(vehicleReqList);
        int expResult = hash;
        VehicleRequestRegistry instance = new VehicleRequestRegistry(vehicleReqList);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class VehicleRequestRegistry.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals2() {
        System.out.println("equals");
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
         Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals");
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
         Object obj = new VehicleRequestRegistry();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals4() {
        System.out.println("equals");
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
        Object obj = new VehicleRequest(2,"pedro",3,2,3, Instant.now().toString(),Instant.now().toString());
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals5() {
        System.out.println("equals");
        VehicleRequestRegistry instance = new VehicleRequestRegistry();
        HashSet<VehicleRequest> l= new HashSet<>();
        l.add(new VehicleRequest(2,"pedro",3,2,7, Instant.now().toString(),Instant.now().toString()));
        Object obj = new VehicleRequestRegistry(l);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of requestLockingUnlockingHistoryVehicles method, of class VehicleRequestRegistry.
     */
    @Test
    public void testRequestLockingUnlockingHistoryVehicles() throws Exception {
        System.out.println("requestLockingUnlockingHistoryVehicles");
        String userEmail = "";
        VehicleRequestsDB vrdb = mock(VehicleRequestsDB.class);
        when(vrdb.getVehiclesRequestHistory(userEmail)).thenReturn(new LinkedList<VehiclesHistoryLine>());
        VehicleRequestRegistry instance = new VehicleRequestRegistry(vrdb);
        VehiclesHistory expResult = new VehiclesHistory(userEmail, new LinkedList<>());
        VehiclesHistory result = instance.requestLockingUnlockingHistoryVehicles(userEmail);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleRequest method, of class VehicleRequestRegistry.
     */
    @Test
    public void testGetVehicleRequest() {
        System.out.println("getVehicleRequest");
        int vrId = 2;
        VehicleRequestsDB vrdb=mock(VehicleRequestsDB.class);
        when(vrdb.getVehicleRequest(vrId)).thenReturn(new VehicleRequest(2,"pedro",3,2,3, Instant.now().toString(),Instant.now().toString()));
        VehicleRequestRegistry instance = new VehicleRequestRegistry(vrdb);
        VehicleRequest expResult = new VehicleRequest(2,"pedro",3,2,3, Instant.now().toString(),Instant.now().toString());
        VehicleRequest result = instance.getVehicleRequest(vrId);
        assertEquals(expResult, result);
    }
    
       @Test
    public void testReceiveUnlockedVehicles() throws Exception {
        System.out.println("receiveUnlockedVehicles");
        VehicleRequestsDB vrdb=mock(VehicleRequestsDB.class);
        VehicleRequestRegistry vrr = new VehicleRequestRegistry(vrdb);
        Set<VehicleRequest> h = new HashSet<>();
        when(vrdb.getActiveRequestsGivenTime(anyString())).thenReturn(h);
        String date = "16/07/1998 16:42:05";
        Set<VehicleRequest> expResult = new HashSet<>();
        Set <VehicleRequest> result = vrr.receiveUnlockedVehicles(date);
        assertEquals(expResult, result);
    }
    
}
