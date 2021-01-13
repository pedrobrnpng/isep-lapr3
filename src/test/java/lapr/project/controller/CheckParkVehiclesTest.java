/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Simao
 */

@RunWith(MockitoJUnitRunner.class)
public class CheckParkVehiclesTest {
    
    private BicycleDB bdb;
    private ScooterDB sdb;
    private ParkDB pdb;
    private CheckParkVehiclesController cpvc;

    @BeforeEach
    public void setUp() throws Exception {
        cpvc = new CheckParkVehiclesController();
        cpvc.getRegistries();
        bdb = mock(BicycleDB.class);
        sdb = mock(ScooterDB.class);
        pdb = mock(ParkDB.class);
        Set<Park> h = new HashSet<>();
        h.add(new Park(1,"Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        Set<Scooter> s = new HashSet<>();
        Scooter ss = new Scooter();
        ss.setID(1);
        s.add(ss);
        Set<Bicycle> b = new HashSet<>();
        Bicycle bb = new Bicycle();
        bb.setID(2);
        b.add(bb);
        cpvc.setBicycleDB(bdb);
        cpvc.setScooterDB(sdb);
        cpvc.setParkDB(pdb);
        when(pdb.getExistingParks()).thenReturn(h);
        when(sdb.getScootersByParkID(1)).thenReturn(s);
        when(bdb.getBicyclesByParkID(2)).thenReturn(b);
    }

        /**
     * Test of getRegistries method, of class CheckParkVehiclesController.
     */
    @Test
    public void testGetRegistries() {
        System.out.println("getRegistries");
        try {
            cpvc.getRegistries();
        } catch (Exception e) {
            e.printStackTrace();
            fail("getRegistries failed");
        }
    }


    /**
     * Test of getExistingParks method, of class CheckParkVehiclesController.
     */
    @Test
    public void testGetExistingParks() {
        System.out.println("getExistingParks");
        Set<Park> expResult = new HashSet<>();
        expResult.add(new Park(1,"Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        Set<Park> result = cpvc.getExistingParks();
        assertEquals(expResult, result);
    }

       /**
     * Test of getExistingScootersByPark method, of class CheckParkVehiclesController.
     */
    @Test
    public void testGetExistingScootersByPark() {
        System.out.println("getExistingScootersByPark");
        Set<Scooter> expResult = new HashSet<>();
        Scooter ss = new Scooter();
        ss.setID(1);
        expResult.add(ss);
        Set<Scooter> result = cpvc.getExistingScootersByParkID(1);
        assertEquals(expResult, result);
    }

       /**
     * Test of getExistingBicyclesByPark, of class CheckParkVehiclesController.
     */
    @Test
    public void testGetExistingBicyclesByPark() {
        System.out.println("getExistingBicyclesByPark");
        Set<Bicycle> expResult = new HashSet<>();
        Bicycle bb = new Bicycle();
        bb.setID(2);
        expResult.add(bb);
        Set<Bicycle> result = cpvc.getExistingBicyclesByParkID(2);
        assertEquals(expResult, result);
    }

}
