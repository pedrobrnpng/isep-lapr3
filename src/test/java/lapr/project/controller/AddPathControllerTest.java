/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
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
public class AddPathControllerTest {

    public AddPathControllerTest() {
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
     * Test of addPath method, of class AddPathController.
     */
    @Test
    public void testAddPath() {
        System.out.println("addPath");
        double latitudeO = 20.0;
        double longitudeO = 12.0;
        double latitudeD = 23.0;
        double longitudeD = 40.0;
        double kineticCoefficient = 1.0;
        double windDirection = 1.0;
        double windSpeed = 1.0;
        AddPathController instance = new AddPathController();
        PathRegistry pr = mock(PathRegistry.class);
        instance.setPathRegistry(pr);
        when(pr.getPlace(latitudeO, longitudeO)).thenReturn(1);
        when(pr.getPlace(latitudeD, longitudeD)).thenReturn(2);
        when(pr.newPath(1, 2, 1.0, 1, 1)).thenReturn(new Path(1, 2, 1, 1, 1, 1));
        instance.newPath(latitudeO, longitudeO, latitudeD, longitudeD, kineticCoefficient, windDirection, windSpeed);
        boolean expResult = true;
        when(pr.addPath(new Path(1, 2, 1, 1, 1, 1))).thenReturn(true);
        boolean result = instance.addPath();
        assertEquals(expResult, result);
        when(pr.getPlace(latitudeO, longitudeO)).thenReturn(-1);
        when(pr.getPlace(latitudeD, longitudeD)).thenReturn(2);
        instance.newPath(latitudeO, longitudeO, latitudeD, longitudeD, kineticCoefficient, windDirection, windSpeed);
        expResult = false;
        result = instance.addPath();
        assertEquals(expResult, result);
        when(pr.getPlace(latitudeO, longitudeO)).thenReturn(1);
        when(pr.getPlace(latitudeD, longitudeD)).thenReturn(-1);
        instance.newPath(latitudeO, longitudeO, latitudeD, longitudeD, kineticCoefficient, windDirection, windSpeed);
        expResult = false;
        result = instance.addPath();
        assertEquals(expResult, result);
        when(pr.getPlace(latitudeO, longitudeO)).thenReturn(-1);
        when(pr.getPlace(latitudeD, longitudeD)).thenReturn(-1);
        instance.newPath(latitudeO, longitudeO, latitudeD, longitudeD, kineticCoefficient, windDirection, windSpeed);
        expResult = false;
        result = instance.addPath();
        assertEquals(expResult, result);
        when(pr.getPlace(latitudeO, longitudeO)).thenReturn(1);
        when(pr.getPlace(latitudeD, longitudeD)).thenReturn(2);
        when(pr.addPath(new Path(1, 2, 1, 1, 1, 1))).thenReturn(false);
        result= instance.addPath();
        assertEquals(result,expResult);
        Set<Path> pathl = new HashSet<>();
        when(pr.addAllPaths()).thenReturn(2);
        int expResult1 = 2;
        int result1 = instance.addAllPaths();
        assertEquals(expResult1, result1);
    }

}
