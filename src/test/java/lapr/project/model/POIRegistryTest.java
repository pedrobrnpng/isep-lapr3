/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.POIDB;
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
public class POIRegistryTest {

    public POIRegistryTest() {
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
     * Test of newPOI method, of class POIRegistry.
     */
    @Test
    public void testNewPOI() {
        System.out.println("newPOI");
        double latitude = 41.0;
        double longitude = 32.0;
        double elevation = 10.0;
        String description = "Fábrica Nortada";
        POIRegistry instance = new POIRegistry();
        POI result = instance.newPOI(latitude, longitude, elevation, description);
        POI expResult = new POI(result.getId(), latitude, longitude, elevation, description);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPOIList method, of class POIRegistry.
     */
    @Test
    public void testSetPOIList() {
        System.out.println("setPOIList");
        Set<POI> poiList = new HashSet<>();
        POIRegistry instance = new POIRegistry();
        instance.setPOIList(poiList);
    }

    /**
     * Test of addPoi method, of class POIRegistry.
     */
    @Test
    public void testAddPoi() {
        System.out.println("addPoi");
        POI poi = new POI(1, 23, 43, "Fábrica Nortana");
        POIRegistry instance = new POIRegistry();
        POIDB pdb = mock(POIDB.class);
        when(pdb.addPOIDB(poi)).thenReturn(true);
        instance.setPOIDB(pdb);
        boolean expResult = true;
        boolean result = instance.addPoi(poi);
        assertEquals(expResult, result);
    }

    /**
     * Test of getExistingPOI method, of class POIRegistry.
     */
    @Test
    public void testGetExistingPOI() {
        System.out.println("getExistingPOI");
        POIRegistry instance = new POIRegistry();
        Set<POI> expResult = new HashSet<>();
        instance.setPOIDB(mock(POIDB.class));
        Set<POI> result = instance.getExistingPOI();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPOIS method, of class POIRegistry.
     */
    @Test
    public void testGetPOIS() {
        System.out.println("getPOIS");
        Place[] elements = new Place[2];
        POI p1 = new POI(12, 32, 1, "desc");
        POI p2 = new POI(30, 32, 1, "desc");
        elements[0] = p1;
        elements[1] = p2;
        POIRegistry instance = new POIRegistry();
        POIDB pdb = mock(POIDB.class);
        Set<POI> pl = new HashSet<>();
        pl.add(new POI(1, 12, 32, 1, "desc"));
        pl.add(new POI(2, 31, 32, 3, "desc"));
        when(pdb.getExistingPOI()).thenReturn(pl);
        instance.setPOIDB(pdb);
        instance.getExistingPOI();
        instance.getPOIS(elements);
        assertEquals(elements[0], new POI(1, 12, 32, 1, "desc"));
        assertEquals(elements[1],p2);
    }

}
