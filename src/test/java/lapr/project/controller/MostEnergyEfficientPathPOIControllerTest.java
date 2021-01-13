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
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
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
 * @author Utilizador
 */
public class MostEnergyEfficientPathPOIControllerTest {

    public MostEnergyEfficientPathPOIControllerTest() {
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
     * Test of createShortestWithPOI method, of class ShortestPathController.
     */
    @Test
    public void testCreateShortestWithPOI() throws InvalidInfoClientException {
        System.out.println("createShortestWithPOI");
        MostEnergyEfficientPathPOIController instance = new MostEnergyEfficientPathPOIController();
        ParkRegistry pr = mock(ParkRegistry.class);
        POIRegistry por = mock(POIRegistry.class);
        PathRegistry par = mock(PathRegistry.class);
        ClientRegistry cr = mock(ClientRegistry.class);
        instance.setRegistrys(pr, por, par, cr);
        Set<Park> pl = new HashSet<>();
        Bicycle bicycle = new Bicycle("19", 1, 1, 20, 0.5, 0.5);
        Client cli=new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        pl.add(new Park(1, "Porto", 41.15227, -8.60929, 104, "Trindade", 23, 23, 260, 120));
        pl.add(new Park(2, "Trindade", 41.14063, -8.61118, 25, "Cais da Ribeira", 10, 5, 220, 16));
        Set<POI> po = new HashSet<>();
        POI p1 = new POI(3, 41.14582, -8.61398, 87, "Clerigos");
        POI p2 = new POI(4, 41.14723, -8.60657, 91, "Majestic");
        POI p3 = new POI(5, 41.14871, -8.60746, 87, "Bolhao");
        po.add(p1);
        po.add(p2);
        po.add(p3);
        Set<Path> pa = new HashSet<>();
        Path pa1 = new Path(1, 3, 0.6, 0, 1);
        Path pa2 = new Path(3, 4, 0.6, 0, 1);
        Path pa3 = new Path(3, 5, 0.6, 0, 1);
        Path pa4 = new Path(5, 2, 0.6, 0, 1);
        Path pa5 = new Path(2, 5, 0.6, 0, 1);
        Path pa6 = new Path(4, 2, 0.6, 0, 1);
        Path pa7 = new Path(2, 3, 0.6, 0, 1);
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
        int id_park_origin = 1;
        int id_park_destination = 2;
        LinkedList<Place> lpath = new LinkedList<>();
        Set<List<Path>> expResult = new LinkedHashSet<>();
        List<Path> l1 = new ArrayList<>();
        l1.add(pa1);
        l1.add(pa3);
        l1.add(pa4);
        l1.add(pa7);
        l1.add(pa2);
        l1.add(pa6);
        l1.add(pa7);
        l1.add(pa3);
        l1.add(pa4);
        expResult.add(l1);
        Place[] elements = new Place[3];
        elements[0] = p1;
        elements[1] = p2;
        elements[2] = p3;
        boolean asc = false;
        int n = 1;
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        List<String> report = new ArrayList<>();
        Set<List<Path>> result = instance.createShortestWithPOI(cli, id_park_origin, id_park_destination, asc, elements, bicycle);
        System.out.println("report: "+report);
        assertEquals(expResult, result);
    }

}
