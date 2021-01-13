/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lapr.project.data.PathDB;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author pedro
 */
public class PathRegistryTest {

    private PathRegistry instance;

    public PathRegistryTest() {
        instance = new PathRegistry();
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
     * Test of getPaths method, of class PathRegistry.
     */
    @Test
    public void testGetPaths() {
        System.out.println("getPaths");
        Set<Path> expResult = new HashSet<Path>();
        Set<Path> result = instance.getPaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of newPath method, of class PathRegistry.
     */
    @Test
    public void testNewPath() {
        System.out.println("newPath");
        int path_id = 3;
        double wind_dir = 20;
        double wind_speed = 10;
        Path result = instance.newPath(1, 2, 1.4, wind_dir, wind_speed);
        Path expResult = new Path(result.getPath_id(), 1, 2, 1.4, 20, 10);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class PathRegistry.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PathRegistry instance = new PathRegistry();
        int expResult = 91;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class PathRegistry.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        PathRegistry instance = new PathRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        PathRegistry instance = new PathRegistry();
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals3() {
        System.out.println("equals");
        PathRegistry instance = new PathRegistry();
        Object obj = new PathRegistry();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals4() {
        System.out.println("equals");
        PathRegistry instance = new PathRegistry();
        Object obj = new Path(1, 1, 2, 1.5, 20, 10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals5() {
        System.out.println("equals");
        PathRegistry instance = new PathRegistry();
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 32, 20, 20));
        Object obj = new PathRegistry(pl);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class PathRegistry.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 32, 20, 20));
        PathRegistry instance = new PathRegistry(pl);
        Path expResult = null;
        Path result = instance.getPath(0);
        assertEquals(expResult, result);
        expResult = new Path(1, 1, 2, 32, 20, 20);
        result = instance.getPath(1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllPaths method, of class PathRegistry.
     */
    @Test
    public void testGetAllPaths() {
        System.out.println("getAllPaths");
        PathRegistry instance = new PathRegistry();
        Set<Path> expResult = new HashSet<>();
        instance.setPathDB(mock(PathDB.class));
        Set<Path> result = instance.getAllPaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class PathRegistry.
     */
    @Test
    public void testGetPath_int_int() {
        System.out.println("getPath");
        int origin = 0;
        int destination = 0;
        PathRegistry instance = new PathRegistry();
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 12, 23));
        PathDB pdb = mock(PathDB.class);
        when(pdb.getExistingPaths()).thenReturn(pl);
        instance.setPathDB(pdb);
        instance.getAllPaths();
        Path expResult = null;
        Path result = instance.getPath(origin, destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPath_int_int2() {
        System.out.println("getPath");
        int origin = 1;
        int destination = 2;
        PathRegistry instance = new PathRegistry();
        PathDB pdb = mock(PathDB.class);
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 2, 12, 23));
        when(pdb.getExistingPaths()).thenReturn(pl);
        instance.setPathDB(pdb);
        instance.getAllPaths();
        Path expResult = new Path(1, 1, 2, 2, 12, 23);
        Path result = instance.getPath(origin, destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPath_int_int3() {
        System.out.println("getPath");
        int origin = 1;
        int destination = 0;
        PathRegistry instance = new PathRegistry();
        PathDB pdb = mock(PathDB.class);
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 2, 12, 23));
        when(pdb.getExistingPaths()).thenReturn(pl);
        instance.setPathDB(pdb);
        instance.getAllPaths();
        Path expResult = null;
        Path result = instance.getPath(origin, destination);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPath_int_int4() {
        System.out.println("getPath");
        int origin = 0;
        int destination = 2;
        PathRegistry instance = new PathRegistry();
        PathDB pdb = mock(PathDB.class);
        HashSet<Path> pl = new HashSet<>();
        pl.add(new Path(1, 1, 2, 2, 12, 23));
        when(pdb.getExistingPaths()).thenReturn(pl);
        instance.setPathDB(pdb);
        instance.getAllPaths();
        Path expResult = null;
        Path result = instance.getPath(origin, destination);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlace method, of class PathRegistry.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        double latitudeO = 10.0;
        double longitudeO = 20.0;
        PathRegistry instance = new PathRegistry();
        int expResult = 1;
        PathDB pdb = mock(PathDB.class);
        instance.setPathDB(pdb);
        when(pdb.getPlace(latitudeO, longitudeO)).thenReturn(1);
        int result = instance.getPlace(latitudeO, longitudeO);
        assertEquals(expResult, result);
    }

    /**
     * Test of addPath method, of class PathRegistry.
     */
    @Test
    public void testAddPath() {
        System.out.println("addPath");
        Path path = new Path(1, 1, 1, 1, 1);
        PathRegistry instance = new PathRegistry();
        boolean expResult = true;
        PathDB pdb = mock(PathDB.class);
        instance.setPathDB(pdb);
        when(pdb.addPath(path)).thenReturn(true);
        boolean result = instance.addPath(path);
        assertEquals(expResult, result);
    }

    /**
     * Test of addAllPaths method, of class PathRegistry.
     */
    @Test
    public void testAddAllPaths() {
        System.out.println("addAllPaths");
        PathRegistry pr = new PathRegistry();
        PathDB pdb = mock(PathDB.class);
        pr.setPathDB(pdb);
        Set<Path> pathList = new HashSet<>();
        when(pdb.addAllPaths(pathList)).thenReturn(2);
        int expResult = 2;
        int result = pr.addAllPaths();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPathDB method, of class PathRegistry.
     */
    @Test
    public void testSetPathDB() {
        System.out.println("setPathDB");
        PathDB pdb = null;
        PathRegistry instance = new PathRegistry();
        instance.setPathDB(pdb);
    }

    /**
     * Test of getPath method, of class PathRegistry.
     */
    @Test
    public void testGetPath_int() {
        System.out.println("getPath");
        int pathID = 0;
        PathRegistry instance = new PathRegistry();
        Path expResult = null;
        Path result = instance.getPath(pathID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEnergyRoutes method, of class PathRegistry.
     */
    @Test
    public void testGetEnergyRoutes() throws InvalidInfoClientException {
        System.out.println("getEnergyRoutes");
        Place p1 = new POI(41.140213, -8.640647, 0, "");
        Place p2 = new POI(40.205228, -8.416501, 0, "");
        Place p3 = new POI(38.744272, -9.101390, 0, "");
        Set<Place> placeList = new HashSet<>();
        placeList.add(p1);
        placeList.add(p2);
        placeList.add(p3);
        Path ph1 = new Path(p1.getId(), p2.getId(), 0.6, 0, 0);
        Path ph2 = new Path(p2.getId(), p3.getId(), 0.6, 0, 0);
        Set<Path> pathList = new HashSet<>();
        pathList.add(ph1);
        pathList.add(ph2);
        Set<Vehicle> vehicleList = new HashSet<>();
        Scooter s1 = new Scooter("off", 420, 100, 1, 1, 10, 10, 1.5, 200);
        Bicycle b1 = new Bicycle("19", 1, 1, 20, 0.5, 15);
        vehicleList.add(s1);
        vehicleList.add(b1);
        Client c = new Client("bruno", "bruno", 222, 16, 1.73, 65, 'M', 15, 1000);
        Place beginPark = p1;
        Place endPark = p3;
        LinkedList<Place> list = new LinkedList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        RoutesPerVehicle rpv1 = new RoutesPerVehicle(0, b1, list);
        RoutesPerVehicle rpv2 = new RoutesPerVehicle(0, s1, list);
        List<RoutesPerVehicle> expResult = new LinkedList<>();
        expResult.add(rpv2);
        expResult.add(rpv1);
        List<RoutesPerVehicle> result = instance.getEnergyRoutes(placeList, pathList, vehicleList, c, beginPark, endPark);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRouteDistance method, of class PathRegistry.
     */
    @Test
    public void testGetRouteDistance() {
        System.out.println("getRouteDistance");
        Place p1 = new POI(41.140213, -8.640647, 0, "");
        Place p2 = new POI(40.205228, -8.416501, 0, "");
        Place p3 = new POI(38.744272, -9.101390, 0, "");
        LinkedList<Place> route = new LinkedList<>();
        route.add(p1);
        route.add(p2);
        route.add(p3);
        double expResult = 280;
        double result = instance.getRouteDistance(route);
        assertEquals(expResult, result, 5);
    }

    /**
     * Test of getLeastWeightRoutes method, of class PathRegistry.
     */
    @Test
    public void testGetLeastWeightRoutes() {
        System.out.println("getLeastWeightRoutes");
        List<RoutesPerVehicle> routes = new ArrayList<>();
        PathRegistry instance1 = new PathRegistry();
        instance1.getLeastWeightRoutes(routes);
        LinkedList<Place> places1 = new LinkedList<>();
        places1.add(new POI(1, 1, 1, 1, "desc"));
        places1.add(new POI(2, 1, 1, 1, "desc"));
        RoutesPerVehicle r1 = new RoutesPerVehicle(1, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places1);
        routes.add(r1);
        LinkedList<Place> places2 = new LinkedList<>();
        places2.add(new POI(3, 1, 1, 20, "desc"));
        RoutesPerVehicle r2 = new RoutesPerVehicle(2, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places2);
        routes.add(r2);
        LinkedList<Place> places3 = new LinkedList<>();
        places3.add(new POI(4, 1, 1, 1, "desc"));
        RoutesPerVehicle r3 = new RoutesPerVehicle(3, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places3);
        routes.add(r3);
        LinkedList<Place> places4 = new LinkedList<>();
        places3.add(new POI(5, 1, 1, 20, "desc"));
        RoutesPerVehicle r4 = new RoutesPerVehicle(4, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places4);
        routes.add(r4);
        instance1.getLeastWeightRoutes(routes);
        assertEquals(routes.get(0), r1);
    }

    /**
     * Test of orderRoutes method, of class PathRegistry.
     */
    @Test
    public void testOrderRoutes() {
        System.out.println("orderRoutes");
        List<RoutesPerVehicle> routes = new ArrayList<>();
        LinkedList<Place> places1 = new LinkedList<>();
        places1.add(new POI(1, 1, 1, 1, "desc"));
        places1.add(new POI(1, 1, 1, 1, "desc"));
        RoutesPerVehicle r1 = new RoutesPerVehicle(1, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places1);
        routes.add(r1);
        LinkedList<Place> places2 = new LinkedList<>();
        places2.add(new POI(1, 1, 1, 20, "desc"));
        RoutesPerVehicle r2 = new RoutesPerVehicle(1, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places2);
        routes.add(r2);
        LinkedList<Place> places3 = new LinkedList<>();
        places3.add(new POI(1, 1, 1, 1, "desc"));
        RoutesPerVehicle r3 = new RoutesPerVehicle(1, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places3);
        routes.add(r3);
        LinkedList<Place> places4 = new LinkedList<>();
        places3.add(new POI(1, 1, 1, 20, "desc"));
        RoutesPerVehicle r4 = new RoutesPerVehicle(1, new Scooter("19", 1, 1, 1, 1, 1, 1, 1, 1), places4);
        routes.add(r4);
        PathRegistry instance1 = new PathRegistry();
        instance1.orderRoutes(routes);
        assertEquals(routes.get(0), r2);
        assertEquals(routes.get(1), r3);
        assertEquals(routes.get(2), r4);
        assertEquals(routes.get(3), r1);
    }

    /**
     * Test of calculateElevation method, of class PathRegistry.
     */
    @Test
    public void testCalculateElevation() {
        System.out.println("calculateElevation");
        LinkedList<Place> places = new LinkedList<>();
        places.add(new POI(1, 1, 1, 1, "desc"));
        PathRegistry instance = new PathRegistry();
        double expResult = 0.0;
        double result = instance.calculateElevation(places);
        assertEquals(expResult, result, 0.0);
    }

}
