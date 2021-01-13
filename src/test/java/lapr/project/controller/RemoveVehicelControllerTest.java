/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 *
 * @author bruno
 */
public class RemoveVehicelControllerTest {
    
    private static RemoveVehicleController instance;
    private static VehicleDB vdb;
    
    @BeforeAll
    public static void setUp() {
        instance = new RemoveVehicleController();
        vdb = mock(VehicleDB.class);
        instance = new RemoveVehicleController(vdb);
    }

    /**
     * Test of removeVehicle method, of class RemoveVehicelController.
     */
    @Test
    public void testRemoveVehicle() throws SQLException {
        System.out.println("removeVehicle");
        int id_vehicle = 1;
        boolean expResult = true;
        boolean result = instance.removeVehicle(id_vehicle);
        assertEquals(expResult, result);
        
        doThrow(SQLException.class).when(vdb).removeVehicle(any(Integer.class));
        
        
        expResult = false;
        result = instance.removeVehicle(id_vehicle);
        assertEquals(expResult, result);
    }
    
}
