/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
import lapr.project.model.PlacesGraph;
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
public class ShortestPathControllerTest {

    public ShortestPathControllerTest() {
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
     * Test of createShortestPath method, of class ShortestPathController.
     */
    @Test
    public void testCreateShortestPath() {
        System.out.println("createShortestPath");
        int id_park_origin = 1;
        int id_park_destination = 2;
        Set<List<Path>> lpaths = new HashSet<>();
        ShortestPathController instance = new ShortestPathController();
        long expResult = 6580958;
        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
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
        when(pr.getParkDB("Trindade")).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        List<String> report = new ArrayList<>();
        long result = instance.createShortestPath("Porto", "Trindade", lpaths, report);
        assertEquals(expResult, result, 0.01);
        Set<List<Path>> expPaths = new HashSet<>();
        List<Path> listPaths= new LinkedList<>();
        listPaths.add(new Path(1, 1, 3, 1, 20, 30));
        listPaths.add(new Path(2, 3, 2, 1, 20, 30));
        expPaths.add(listPaths);
        assertEquals(lpaths, expPaths);
        when(pr.getParkByCoordinates(40, 20)).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkByCoordinates(10, 20)).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        lpaths.clear();
        report.clear();
        result = instance.createShortestPath(40, 20, 10, 20, lpaths, report);
        expResult = 6580958;
        assertEquals(result, expResult);
        result=instance.pathDistanceTO(40, 20, 10, 20, new LinkedList<>());
        assertEquals(result,expResult);
    }

    /**
     * Test of createShortestWithPOI method, of class ShortestPathController.
     */
    @Test
    public void testCreateShortestWithPOI() {
        System.out.println("createShortestWithPOI");
        ShortestPathController instance = new ShortestPathController();
        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        instance.setRegistrys(pr, por, par);
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 200, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 123, "ISEP");
        POI p3 = new POI(5, 12, 32, 65, "ESEP");
        po.add(p1);
        po.add(p2);
        po.add(p3);
        Set<Path> pa = new HashSet<>();
        Path pa1 = new Path(1, 3, 3, 20, 30);
        Path pa2 = new Path(3, 4, 2, 20, 30);
        Path pa3 = new Path(5, 2, 3, 40, 50);
        Path pa4 = new Path(1, 4, 3, 20, 30);
        Path pa5 = new Path(4, 5, 2, 20, 30);
        Path pa6 = new Path(5, 3, 2, 20, 30);
        Path pa7 = new Path(3, 2, 2, 20, 30);
        pa.add(pa1);
        pa.add(pa2);
        pa.add(pa3);
        pa.add(pa4);
        pa.add(pa5);
        pa.add(pa6);
        pa.add(pa7);
        when(pr.getAllParks()).thenReturn(pl);
        when(por.getExistingPOI()).thenReturn(po);
        when(par.getAllPaths()).thenReturn(pa);
        when(pr.getParkDB("Porto")).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkDB("Trindade")).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        when(pr.getParkByCoordinates(23, 23)).thenReturn(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        when(pr.getParkByCoordinates(80.15227, -10.60929)).thenReturn(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        int id_park_origin = 1;
        int id_park_destination = 2;
        LinkedList<Place> lpath = new LinkedList<>();
        Set<List<Path>> expResult = new LinkedHashSet<>();
        List<Path> l1 = new ArrayList<>();
        l1.add(pa1);
        l1.add(pa2);
        l1.add(pa5);
        l1.add(pa6);
        l1.add(pa2);
        l1.add(pa5);
        l1.add(pa3);
        List<Path> l2 = new ArrayList<>();
        l2.add(pa4);
        l2.add(pa5);
        l2.add(pa6);
        l2.add(pa2);
        l2.add(pa5);
        l2.add(pa6);
        l2.add(pa7);
        List<Path> l3 = new ArrayList<>();
        l3.add(pa4);
        l3.add(pa5);
        l3.add(pa6);
        l3.add(pa2);
        l3.add(pa5);
        l3.add(pa3);
        List<Path> l4 = new ArrayList<>();
        l4.add(pa4);
        l4.add(pa5);
        l4.add(pa6);
        l4.add(pa2);
        l4.add(pa5);
        l4.add(pa3);
        List<Path> l5 = new ArrayList<>();
        l5.add(pa1);
        l5.add(pa2);
        l5.add(pa5);
        l5.add(pa3);
        List<Path> l6 = new ArrayList<>();
        l6.add(pa4);
        l6.add(pa5);
        l6.add(pa6);
        l6.add(pa7);
        expResult.add(l1);
        expResult.add(l2);
        expResult.add(l3);
        expResult.add(l5);
        expResult.add(l6);
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = false;
        int n = 6;
        List<String> report = new ArrayList<>();
        long result = instance.createShortestWithPOI("Porto", "Trindade", elements, report);
        long expResult1 = 14192867;
        assertEquals(expResult1, result);
        report.clear();
        result = instance.createShortestWithPOI(23, 23, 80.15227, -10.60929, elements, report);
        assertEquals(expResult1, result);
    }

}
