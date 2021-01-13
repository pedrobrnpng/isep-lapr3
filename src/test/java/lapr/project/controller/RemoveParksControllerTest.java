/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Utilizador
 */
public class RemoveParksControllerTest {

    private RemoveParksController removeParksController;

    public RemoveParksControllerTest() {
        this.removeParksController = new RemoveParksController();
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
     * Test of deactivatePark method, of class RemoveParksController.
     */
    @Test
    public void testDeactivatePark() {
        System.out.println("deactivatePark");
        String identification = "Porto";
        ParkDB parkDB = mock(ParkDB.class);
        removeParksController.setParkDB(parkDB);
        when(parkDB.getPark(identification)).thenReturn(new Park(1,"Porto", 23, 21, 12, "desc", 12, 23, 100, 12));
        removeParksController.getPark(identification);
        boolean expResult = false;
        boolean result = removeParksController.deactivatePark();
        assertEquals(expResult, result);
    }

    /**
     * Test of deactivatePark method, of class RemoveParksController.
     */
    @Test
    public void testDeactivatePark2() {
        System.out.println("deactivatePark");
        String identification = "Porto";
        ParkDB parkDB = mock(ParkDB.class);
        removeParksController.setParkDB(parkDB);
        when(parkDB.getPark(identification)).thenReturn(null);
        boolean expResult = false;
        removeParksController.getPark(identification);
        boolean result = removeParksController.deactivatePark();
        assertEquals(expResult, result);
    }

    /**
     * Test of deactivatePark method, of class RemoveParksController.
     */
    @Test
    public void testDeactivatePark3() {
        System.out.println("deactivatePark");
        String identification = "Porto";
        ParkDB parkDB = mock(ParkDB.class);
        removeParksController.setParkDB(parkDB);
        when(parkDB.getPark(identification)).thenReturn(new Park(1001,"Porto", 23, 21, 12, "desc", 12, 23, 100, 12));
        when(parkDB.deactivatePark(new Park(1001,"Porto", 23, 21, 12, "desc", 12, 23, 100, 12))).thenReturn(true);
        removeParksController.getPark(identification);
        boolean expResult = true;
        boolean result = removeParksController.deactivatePark();
        assertEquals(expResult, result);
    }

}
