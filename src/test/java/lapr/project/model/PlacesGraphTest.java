/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class PlacesGraphTest {

    public PlacesGraphTest() {
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
     * Test of addPlace method, of class PlacesGraph.
     */
    @Test
    public void testAddPlace() {
        System.out.println("addPlace");
        Place p = new POI(1, 23, 43, 12, "Fábrica Nortada");
        PlacesGraph instance = new PlacesGraph(new HashSet<>(), new HashSet<>());
        instance.addPlace(p);
    }

    /**
     * Test of addConnectionBetweenPlaces method, of class PlacesGraph.
     */
    @Test
    public void testAddConnectionBetweenPlaces() {
        System.out.println("addConnectionBetweenPlaces");
        Place p1 = new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120);
        Place p2 = new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16);
        double distance = 400.0;
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.addConnectionBetweenPlaces(p1, p2, new Path(1, 1, 2, 123, 32), distance);
    }

    /**
     * Test of insertPaths method, of class PlacesGraph.
     */
    @Test
    public void testInsertPaths() {
        System.out.println("insertPaths");
        Set<Path> pa = new HashSet<>();
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        pa.add(new Path(1, 3, 1, 20, 30));
        pa.add(new Path(3, 2, 1, 20, 30));
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
    }

    @Test
    public void testInsertPaths2() {
        System.out.println("insertPaths");
        Set<Path> pa = new HashSet<>();
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 23, 23, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        pa.add(new Path(1, 3, 1, 20, 30));
        pa.add(new Path(3, 2, 1, 20, 30));
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
    }

    /**
     * Test of getShortestPath method, of class PlacesGraph.
     */
    @Test
    public void testGetShortestPath() {
        System.out.println("getShortestPath");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
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
        int id_park_origin = 1;
        int id_park_destination = 2;
        Set<List<Path>> lpath = new HashSet<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        long expResult = 6580958;
        List<String> report = new ArrayList<>();
        long result = instance.getShortestPath(id_park_origin, id_park_destination, lpath, report);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetShortestPath2() {
        System.out.println("getShortestPath");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 3, 3, 20, 30));
        pa.add(new Path(3, 2, 2, 20, 30));
        int id_park_origin = 100;
        int id_park_destination = 100;
        Set<List<Path>> lpath = new HashSet<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        double expResult = -1;
        List<String> report = new ArrayList<>();
        double result = instance.getShortestPath(id_park_origin, id_park_destination, lpath, report);
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testGetShortestPath3() {
        System.out.println("getShortestPath");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 3, 3, 20, 30));
        pa.add(new Path(3, 2, 2, 20, 30));
        int id_park_origin = 1;
        int id_park_destination = 100;
        Set<List<Path>> lpath = new HashSet<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        double expResult = -1;
        List<String> report = new ArrayList<>();
        double result = instance.getShortestPath(id_park_origin, id_park_destination, lpath, report);
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testGetShortestPath4() {
        System.out.println("getShortestPath");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        Set<Path> pa = new HashSet<>();
        pa.add(new Path(1, 3, 3, 20, 30));
        pa.add(new Path(3, 2, 2, 20, 30));
        int id_park_origin = 100;
        int id_park_destination = 2;
        Set<List<Path>> lpath = new HashSet<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        double expResult = -1;
        List<String> report = new ArrayList<>();
        double result = instance.getShortestPath(id_park_origin, id_park_destination, lpath, report);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of permutations method, of class PlacesGraph.
     */
    @Test
    public void testPlacesCombinations() {
        System.out.println("placesCombinations");
        LinkedList<Place> list = new LinkedList<>();
        Place[] elements = new Place[3];
        elements[0] = new POI(0, 0, 0, "desc");
        elements[1] = new POI(0, 0, 0, "desc2");
        elements[2] = new POI(0, 0, 0, "desc3");
        List<List<Place>> l = new LinkedList<>();
        PlacesGraph.placesCombinations(3, elements, l);
        List<List<Place>> expResult = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            expResult.add(new ArrayList<Place>());
        }
        expResult.get(0).add(elements[0]);
        expResult.get(0).add(elements[1]);
        expResult.get(0).add(elements[2]);
        expResult.get(1).add(elements[0]);
        expResult.get(1).add(elements[2]);
        expResult.get(1).add(elements[1]);
        expResult.get(2).add(elements[1]);
        expResult.get(2).add(elements[0]);
        expResult.get(2).add(elements[2]);
        expResult.get(3).add(elements[1]);
        expResult.get(3).add(elements[2]);
        expResult.get(3).add(elements[0]);
        expResult.get(4).add(elements[2]);
        expResult.get(4).add(elements[0]);
        expResult.get(4).add(elements[1]);
        expResult.get(5).add(elements[2]);
        expResult.get(5).add(elements[1]);
        expResult.get(5).add(elements[0]);
        List<List<Place>> result = l;
        assertTrue(result.contains(expResult.get(0)));
        assertTrue(result.contains(expResult.get(1)));
        assertTrue(result.contains(expResult.get(2)));
        assertTrue(result.contains(expResult.get(3)));
        assertTrue(result.contains(expResult.get(4)));
        assertTrue(result.contains(expResult.get(5)));
        assertTrue(result.size() == expResult.size());
    }

    /**
     * Test of getShortestPathWithPOI method, of class PlacesGraph.
     */
    @Test
    public void testGetShortestPathWithPOI() {
        System.out.println("getShortestPathWithPOI");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
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
        int id_park_origin = 1;
        int id_park_destination = 2;
        LinkedList<Place> lpath = new LinkedList<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        Set<List<Path>> expResult = new LinkedHashSet<>();
        List<Path> l6 = new ArrayList<>();
        l6.add(pa4);
        l6.add(pa5);
        l6.add(pa6);
        l6.add(pa7);
        expResult.add(l6);
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = false;
        int n = 6;
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        List<String> report = new ArrayList<>();
        long result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        WriteReport.writeParkReport(report, "route.txt");
        Iterator<List<Path>> it = pathSet.iterator();
        Iterator<List<Path>> it2 = expResult.iterator();
        assertEquals(it.next(), it2.next());
        long expResult1 = 14192867;
        assertEquals(expResult1, result);
        pl.clear();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 40, 23, 104, "Parque da Trindade", 10, 5, 220, 16));
        po.clear();
        p1 = new POI(3, 23, 46, 12, "Fábrica Nortada");
        p2 = new POI(4, 23, 0, 1, "ISEP");
        po.add(p1);
        po.add(p2);
        elements = new Place[2];
        elements[0] = p1;
        elements[1] = p2;
        pa.clear();
        pa1 = new Path(1, 3, 3, 20, 30);
        pa4 = new Path(1, 4, 3, 20, 30);
        pa2 = new Path(3, 4, 2, 20, 30);
        pa3 = new Path(4, 3, 3, 40, 50);
        pa5 = new Path(4, 2, 2, 20, 30);
        pa6 = new Path(3, 2, 2, 20, 30);
        pa.add(pa1);
        pa.add(pa2);
        pa.add(pa3);
        pa.add(pa4);
        pa.add(pa5);
        pa.add(pa6);
        pathSet.clear();
        report.clear();
        instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        expResult1= 9909065;
        expResult.clear();
        List<Path> l1= new ArrayList<>();
        l1.add(pa1);
        l1.add(pa2);
        l1.add(pa5);
        List<Path> l2= new ArrayList<>();
        l1.add(pa4);
        l1.add(pa3);
        l1.add(pa6);
        expResult.add(l1);
        expResult.add(l2);
        assertEquals(expResult1, result);
        
    }

    /**
     * Test of getShortestPathWithPOI method, of class PlacesGraph.
     */
    @Test
    public void testGetShortestPathWithPOI2() {
        System.out.println("getShortestPathWithPOI");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
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
        int id_park_origin = 1;
        int id_park_destination = 2;
        LinkedList<Place> lpath = new LinkedList<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
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
        expResult.add(l6);
        expResult.add(l5);
        expResult.add(l3);
        expResult.add(l2);
        expResult.add(l1);
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = true;
        int n = 6;
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        List<String> report = new ArrayList<>();
        long result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        long expResult1 = 14192867;
        assertEquals(expResult1, result);
    }

    @Test
    public void testGetShortestPathWithPOI3() {
        System.out.println("getShortestPathWithPOI");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
        po.add(p1);
        po.add(p2);
        po.add(p3);
        Set<Path> pa = new HashSet<>();
        Path pa1 = new Path(1, 3, 3, 20, 30);
        Path pa2 = new Path(3, 4, 2, 20, 30);
        Path pa3 = new Path(5, 2, 3, 40, 50);
        Path pa4 = new Path(4, 5, 2, 20, 30);
        pa.add(pa1);
        pa.add(pa2);
        pa.add(pa3);
        pa.add(pa4);
        int id_park_origin = 1;
        int id_park_destination = 2;
        LinkedList<Place> lpath = new LinkedList<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        Set<List<Path>> expResult = new LinkedHashSet<>();
        List<Path> l1 = new ArrayList<>();
        l1.add(pa1);
        l1.add(pa2);
        l1.add(pa4);
        l1.add(pa3);
        expResult.add(l1);
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = false;
        int n = 1;
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        List<String> report = new ArrayList<>();
        long result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        long expResult1 = 15872436;
        assertEquals(expResult1, result);
    }

    @Test
    public void testGetShortestPathWithPOI4() {
        System.out.println("getShortestPathWithPOI");
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
        po.add(p1);
        po.add(p2);
        po.add(p3);
        Set<Path> pa = new HashSet<>();
        Path pa1 = new Path(1, 3, 3, 20, 30);
        Path pa2 = new Path(3, 4, 2, 20, 30);
        Path pa3 = new Path(5, 2, 3, 40, 50);
        Path pa4 = new Path(4, 5, 2, 20, 30);
        pa.add(pa1);
        pa.add(pa2);
        pa.add(pa3);
        pa.add(pa4);
        int id_park_origin = 0;
        int id_park_destination = 1;
        LinkedList<Place> lpath = new LinkedList<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        Set<List<Path>> expResult = null;
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = false;
        int n = 1;
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        List<String> report = new ArrayList<>();
        long result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        long expResult1 = -1;
        assertEquals(expResult1, result);
        id_park_origin = 1;
        id_park_destination = 10000;
        pathSet.clear();
        result = instance.getShortestPathWithPOI(id_park_origin, id_park_destination, elements, pathSet, report);
        expResult1 = -1;
        assertEquals(expResult1, result);
    }

    /**
     * Test of getPlaceOrigin method, of class PlacesGraph.
     */
    @Test
    public void testGetPlaceOrigin() {
        Set<Park> pl = new HashSet<>();
        pl.add(new Park(1, "Porto", 23, 23, 42, "Parque do Porto", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 80.15227, -10.60929, 104, "Parque da Trindade", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 50, 23, 12, "Fábrica Nortada");
        POI p2 = new POI(4, 43, 13, 1, "ISEP");
        POI p3 = new POI(5, 12, 32, 2, "ESEP");
        po.add(p1);
        po.add(p2);
        po.add(p3);
        Set<Path> pa = new HashSet<>();
        Path pa1 = new Path(1, 3, 3, 20, 30);
        Path pa2 = new Path(3, 4, 2, 20, 30);
        Path pa3 = new Path(5, 2, 3, 40, 50);
        Path pa4 = new Path(4, 5, 2, 20, 30);
        pa.add(pa1);
        pa.add(pa2);
        pa.add(pa3);
        pa.add(pa4);
        int id_park_origin = 0;
        int id_park_destination = 1;
        LinkedList<Place> lpath = new LinkedList<>();
        PlacesGraph instance = new PlacesGraph(pl, po);
        instance.insertPaths(pa);
        System.out.println("getPlaceOrigin");
        Path path = pa1;
        Place expResult = pl.iterator().next();
        Place result = instance.getPlaceOrigin(path);
        assertEquals(expResult, result);
        assertEquals(instance.getPlaceDestination(path), p1);
        result = instance.getPlaceOrigin(new Path(100, 100, 100, 1, 1, 1));
        assertEquals(result, null);
        result = instance.getPlaceDestination(new Path(100, 100, 100, 1, 1, 1));
        assertEquals(result, null);

    }
}
