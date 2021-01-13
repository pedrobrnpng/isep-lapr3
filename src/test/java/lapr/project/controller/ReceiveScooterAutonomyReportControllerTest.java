/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author bruno
 */
public class ReceiveScooterAutonomyReportControllerTest {

    private ScooterDB sdb;
    private ReceiveScooterAutonomyReportController instance;

    @BeforeEach
    public void setUp() throws Exception {
        instance = new ReceiveScooterAutonomyReportController();
        sdb = mock(ScooterDB.class);
        instance = new ReceiveScooterAutonomyReportController(sdb);
        HashSet<Scooter> h = new HashSet<>();
        h.add(new Scooter("off-road", 350, 100, 2, 2, 2, 2, 2, 250));
        when(sdb.getAllAvailableScooters()).thenReturn(h);
    }

    /**
     * Test of getScootersAutonomyByXKms method, of class
     * ReceiveScooterAutonomyReportController.
     */
    @Test
    public void testGetScootersAutonomyByXKms() throws SQLException {
        System.out.println("getScootersAutonomyByXKms");
        int km = 20;
        Set<Scooter> expResult = new HashSet<>();
        expResult.add(new Scooter("off-road", 350, 100, 2, 2, 2, 2, 2, 250));
        Set<Scooter> result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        expResult.clear();

        km = 19;
        result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        km = 0;
        result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        km = -1;
        result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        HashSet<Scooter> h = new HashSet<>();
        when(sdb.getAllAvailableScooters()).thenReturn(h);

        km = 20;
        result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        h = null;
        when(sdb.getAllAvailableScooters()).thenReturn(h);

        km = 20;
        result = instance.getScootersAutonomyByXKms(km);
        assertEquals(expResult, result);

        doThrow(SQLException.class).when(sdb).getAllAvailableScooters();

        assertEquals(instance.getScootersAutonomyByXKms(km), new HashSet<Scooter>());

    }

}
