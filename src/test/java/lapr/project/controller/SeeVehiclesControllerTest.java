/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.VehicleDB;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author bruno
 */
public class SeeVehiclesControllerTest {
    
    private static SeeVehiclesController instance;
    private static VehicleDB vdb;
    
    @BeforeAll
    public static void setUp() throws SQLException {
        instance = new SeeVehiclesController();
        vdb = mock(VehicleDB.class);
        instance = new SeeVehiclesController(vdb);
        HashSet<Vehicle> l = new HashSet<>();
        l.add(new Scooter());
        when(vdb.getAllVehicles()).thenReturn(l);
    }

    /**
     * Test of getAllVehicles method, of class SeeVehiclesController.
     */
    @Test
    public void testGetAllVehicles() throws SQLException {
        System.out.println("getAllVehicles");
        HashSet<Vehicle> expResult = new HashSet<>();
        expResult.add(new Scooter());
        HashSet<Vehicle> result = (HashSet<Vehicle>) instance.getAllVehicles();
        assertEquals(expResult, result);
        
        doThrow(SQLException.class).when(vdb).getAllVehicles();
        
        result = (HashSet<Vehicle>) instance.getAllVehicles();
        assertEquals(new HashSet<>(), result);
    }
    
}
