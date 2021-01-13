/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import lapr.project.model.VehiclesRegistry;
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
public class GetParkReportControllerTest {

    public GetParkReportControllerTest() {
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
     * Test of getParkReport method, of class GetParkReportController.
     */
    @Test
    public void testGetParkReport() {
        System.out.println("getParkReport");
        int idPark = 1;
        GetParkReportController instance = new GetParkReportController();
        VehiclesRegistry vr = mock(VehiclesRegistry.class);
        instance.setRegistrys(vr, new ParkRegistry());
        ParkDB pdb = mock(ParkDB.class);
        instance.getParkRegistry().setParkDB(pdb);
        Set<Scooter> vList = new LinkedHashSet<>();
        vList.add(new Scooter("Off-Road", 2, 100, 4, 1, 23, 0.2, 30, 100));
        vList.add(new Scooter("Off-Road", 2, 100, 3, 1, 23, 0.2, 30, 100));
        vList.add(new Scooter("Off-Road", 2, 100, 5, 1, 23, 0.2, 30, 100));
        vList.add(new Scooter("Off-Road", 5, 20, 2, 1, 23, 0.2, 30, 100));
        when(vr.getVehiclesByPark(1)).thenReturn(vList);
        when(pdb.getPark("Trindade")).thenReturn(new Park(1, "Trindade", 20, 12, 20, "Park da trindade", 10, 12, 260, 16));
        long result = instance.getParkReport("Trindade", "parkReport.txt");
        long expResult = 1;
        assertEquals(result, expResult);
    }

    @Test
    public void testGetParkReport2() {
        System.out.println("getParkReport");
        int idPark = 1;
        GetParkReportController instance = new GetParkReportController();
        VehiclesRegistry vr = mock(VehiclesRegistry.class);
        instance.setRegistrys(vr, new ParkRegistry());
        ParkDB pdb = mock(ParkDB.class);
        instance.getParkRegistry().setParkDB(pdb);
        Set<Scooter> vList = new HashSet<>();
        vList.add(new Scooter("Off-Road", 5, 20, 2, 1, 23, 0.2, 30, 100));
        when(vr.getVehiclesByPark(1)).thenReturn(vList);
        when(pdb.getPark("Trindade")).thenReturn(new Park(1, "Trindade", 20, 12, 20, "Park da trindade", 10, 12, 1080000, 1000));
        long result = instance.getParkReport("Trindade", "parkReport.csv");
        long expResult = 1;
        assertEquals(expResult, result);
    }

    @Test
    public void testGetParkReport3() {
        System.out.println("getParkReport");
        int idPark = 1;
        GetParkReportController instance = new GetParkReportController();
        VehiclesRegistry vr = mock(VehiclesRegistry.class);
        instance.setRegistrys(vr, new ParkRegistry());
        ParkDB pdb = mock(ParkDB.class);
        instance.getParkRegistry().setParkDB(pdb);
        Set<Scooter> vList = new HashSet<>();
        vList.add(new Scooter("Off-Road", 5, 20, 2, 1, 23, 0.2, 30, 100));
        when(vr.getVehiclesByPark(1)).thenReturn(vList);
        when(pdb.getPark("Trindade")).thenReturn(new Park(1, "Trindade", 20, 12, 20, "Park da trindade", 10, 12, 1080000, 1));
        long result = instance.getParkReport("Trindade", "parkReport.txt");
        long expResult = 1;
        assertEquals(expResult, result);
    }
}
