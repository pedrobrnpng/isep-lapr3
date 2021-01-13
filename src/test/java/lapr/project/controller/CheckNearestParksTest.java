/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
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
public class CheckNearestParksTest {

    private ParkDB pdb;
    private CheckNearestParksController cnpc;

    @BeforeEach
    public void setUp() throws Exception {
        Set<Park> h = new HashSet<>();
        h.add(new Park(1, "Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201));
        h.add(new Park(1, "Porto", 23.009, 44.009, 10, "Parque do Porto longe", 10, 10, 200, 201));
        h.add(new Park(1, "Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        pdb = mock(ParkDB.class);
        cnpc = new CheckNearestParksController(pdb);
        cnpc.getRegistries();
        when(pdb.getExistingParks()).thenReturn(h);
        CheckNearestParksController dummy = new CheckNearestParksController();
        dummy.pdb=new ParkDB();
    }

    /**
     * Test of getRegistries method, of class CheckNearestParksController.
     */
    @Test
    public void testGetRegistries() {
        System.out.println("getRegistries");
        try {
            cnpc.getRegistries();
        } catch (Exception e) {
            e.printStackTrace();
            fail("getRegistries failed");
        }
    }

    /**
     * Test of getExistingParks method, of class CheckNearestParksController.
     */
    @Test
    public void testGetAllParks() {
        System.out.println("getAllParks");
        Set<Park> expResult = new HashSet<>();
        expResult.add(new Park(1, "Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        expResult.add(new Park(1, "Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201));
        expResult.add(new Park(1, "Porto", 23.009, 44.009, 10, "Parque do Porto longe", 10, 10, 200, 201));
        Set<Park> result = cnpc.getAllParks();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNearestParks method, of class CheckNearestParksController.
     */
    @Test
    public void testGetNearestParksFound() {
        System.out.println("getNearestParks");
        List<Park> expResult = new ArrayList<>();
        expResult.add(new Park(1, "Porto", 23.001, 43.001, 10, "Parque do Porto perto", 10, 10, 200, 201));
        expResult.add(new Park(1, "Porto", 23.003, 43.003, 10, "Parque do Porto medio", 10, 10, 200, 201));
        List<Park> result = cnpc.getNearestParks(23, 43, cnpc.getAllParks());
        assertEquals(expResult, result);
    }

    /**
     * Test of getNearestParks method, of class CheckNearestParksController.
     */
    @Test
    public void testGetNearestParksNull() {
        System.out.println("getNearestParks");
        List<Park> expResult = new ArrayList<>();
        List<Park> result = cnpc.getNearestParks(100, 100, cnpc.getAllParks());
        assertEquals(expResult, result);
    }
}
