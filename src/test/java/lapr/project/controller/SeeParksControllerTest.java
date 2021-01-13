/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Utilizador
 */
public class SeeParksControllerTest {
    
    public SeeParksControllerTest() {
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

    /**
     * Test of getExistingParks method, of class SeeParksController.
     */
    @Test
    public void testGetExistingParks() {
        System.out.println("getExistingParks");
        SeeParksController instance = new SeeParksController();
        ParkDB parkDB= mock(ParkDB.class);
        instance.setParkDB(parkDB);
        Set<Park> pl= new HashSet<>();
        pl.add(new Park(1,"Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        when(parkDB.getExistingParks()).thenReturn(pl);
        Set<Park> expResult = new HashSet<>();
        expResult.add(new Park(1,"Porto", 23, 43, 10, "Parque do Porto", 10, 10, 200, 201));
        Set<Park> result = instance.getExistingParks();
        assertEquals(expResult,result);
    }
    
}
