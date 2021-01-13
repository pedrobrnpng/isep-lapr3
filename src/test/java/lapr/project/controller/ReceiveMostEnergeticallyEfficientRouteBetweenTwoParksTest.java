/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import lapr.project.data.BicycleDB;
import lapr.project.data.ClientDB;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.data.PathDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Place;
import lapr.project.model.RoutesPerVehicle;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 *
 * @author bruno
 */
public class ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksTest {

    ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController instance;
    POIDB poidb;
    ParkDB pkdb;
    PathDB pdb;
    ScooterDB sdb;
    BicycleDB bdb;
    ClientDB cdb;
    VehicleDB vdb;
    
    
    @BeforeEach
    public void setUp() throws Exception {
        bdb = mock(BicycleDB.class);
        sdb = mock(ScooterDB.class);
        pkdb = mock(ParkDB.class);
        pdb = mock(PathDB.class);
        poidb = mock(POIDB.class);
        cdb = mock(ClientDB.class);
        vdb = mock(VehicleDB.class);
        instance = new ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController(poidb, pdb, pkdb, bdb, sdb, cdb, vdb);
    }

    /**
     * Test of receiveMostEnergeticallyRouteBetweenTwoParks method, of class ReceiveMostEnergeticallyEfficientRouteBetweenTwoParks.
     */
    @Test
    public void testReceiveMostEnergeticallyRouteBetweenTwoParks() throws InvalidInfoClientException {
        System.out.println("receiveMostEnergeticallyRouteBetweenTwoParks");
        ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController i = new ReceiveMostEnergeticallyEfficientRouteBetweenTwoParksController();
        String username = "bruno";
        int beginPark = 1;
        int endPark = 3;
        List<RoutesPerVehicle> expResult = new LinkedList<>();
        List<RoutesPerVehicle> result = instance.receiveMostEnergeticallyRouteBetweenTwoParks(username, beginPark, endPark);
        assertEquals(expResult, result);
        
        doThrow(InvalidInfoClientException.class).when(cdb).getClient(username);
        
        result = instance.receiveMostEnergeticallyRouteBetweenTwoParks(username, beginPark, endPark);
        assertEquals(expResult, result);
        
        doThrow(SQLException.class).when(cdb).getClient(username);
        
        result = instance.receiveMostEnergeticallyRouteBetweenTwoParks(username, beginPark, endPark);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRouteDistance method, of class ReceiveMostEnergeticallyEfficientRouteBetweenTwoParks.
     */
    @Test
    public void testGetRouteDistance() {
        System.out.println("getRouteDistance");
        LinkedList<Place> route = new LinkedList<>();
        double expResult = 0.0;
        double result = instance.getRouteDistance(route);
        assertEquals(expResult, result, 0.0);
    }
    
}
