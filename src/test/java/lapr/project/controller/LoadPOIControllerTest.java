/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.POIDB;
import lapr.project.model.POI;
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
public class LoadPOIControllerTest {
    private final LoadPOIController lpc;
    
    public LoadPOIControllerTest() {
        lpc=new LoadPOIController();
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
     * Test of addPOI method, of class LoadPOIController.
     */
    @Test
    public void testAddPOI() {
        System.out.println("addPOI");
        double latitude = 10.0;
        double longitude = 12.0;
        double elevation = 1.0;
        String description = "Fábrica Nortada";
        POIDB pdb= mock(POIDB.class);
        lpc.getPOIRegistry().setPOIDB(pdb);
        lpc.newPOI(latitude, longitude, elevation, description);
        when(pdb.addPOIDB(new POI(this.lpc.getPOI().getId(),latitude, longitude, elevation, description))).thenReturn(true);
        Set<POI> poilist= new HashSet<>();
        poilist.add(new POI(this.lpc.getPOI().getId(),latitude, longitude, elevation, description));
        when(pdb.addPOIDB(poilist)).thenReturn(1);
        boolean expResult = true;
        boolean result = lpc.addPOI();
        assertEquals(expResult, result);
        assertEquals(lpc.addPOIlist(),1);
    }
    
}
