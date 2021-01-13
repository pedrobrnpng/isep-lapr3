/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class PathTest {
    
    private Path path ; 
    
    public PathTest() {
        path = new Path(1,1,2,1.5,20,10);
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
     * Test of getPath_id method, of class Path.
     */
    @Test
    public void testGetPath_id() {
        System.out.println("getPath_id");
        int expResult = 1;
        int result = path.getPath_id();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getKinectic_coeficient method, of class Path.
     */
    @Test
    public void testGetKinectic_coeficient() {
        System.out.println("getKinectic_coeficient");
        double expResult = 1.5;
        double result = path.getKinectic_coeficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWind_dir method, of class Path.
     */
    @Test
    public void testGetWind_dir() {
        System.out.println("getWind_dir");
        double expResult = 20;
        double result = path.getWind_dir();
        assertEquals(expResult, result,0.0);
    }

    /**
     * Test of getWind_speed method, of class Path.
     */
    @Test
    public void testGetWind_speed() {
        System.out.println("getWind_speed");
        double expResult = 10;
        double result = path.getWind_speed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPath_id method, of class Path.
     */
    @Test
    public void testSetPath_id() {
        System.out.println("setPath_id");
        int path_id = 1;
        path.setPath_id(path_id);
    }

    /**
     * Test of setKinectic_coeficient method, of class Path.
     */
    @Test
    public void testSetKinectic_coeficient() {
        System.out.println("setKinectic_coeficient");
        double kinectic_coeficient = 1.5;
        path.setKinectic_coeficient(kinectic_coeficient);
    }

    /**
     * Test of setWind_dir method, of class Path.
     */
    @Test
    public void testSetWind_dir() {
        System.out.println("setWind_dir");
        double wind_dir = 10;
        path.setWind_dir(wind_dir);
    }

    /**
     * Test of setWind_speed method, of class Path.
     */
    @Test
    public void testSetWind_speed() {
        System.out.println("setWind_speed");
        double wind_speed = 23;
        path.setWind_speed(wind_speed);
    }

    /**
     * Test of hashCode method, of class Path.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 292;
        int result = path.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Path.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = path.equals(obj);
        assertEquals(expResult, result);
    }
    
   @Test
    public void testEquals2() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = path;
        boolean result = path.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = new Path(1,1,2,1.5,20,10);
        boolean result = path.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals4() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new Path(2,1,2,6, 20, 8);
        boolean result = path.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals5() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new PathRegistry();
        boolean result = path.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of setId_place_origin method, of class Path.
     */
    @Test
    public void testSetId_place_origin() {
        System.out.println("setId_place_origin");
        int id_place_origin = 1;
        Path instance = new Path(1, 23, 42, 1, 2);
        instance.setId_place_origin(id_place_origin);
        assertEquals(id_place_origin, instance.getId_place_origin());
    }

    /**
     * Test of setId_place_destination method, of class Path.
     */
    @Test
    public void testSetId_place_destination() {
        System.out.println("setId_place_destination");
        int id_place_destination = 2;
        Path instance = new Path(1, 23, 42, 1, 2);
        instance.setId_place_destination(id_place_destination);
        assertEquals(id_place_destination, instance.getId_place_destination());
    }
    
}
