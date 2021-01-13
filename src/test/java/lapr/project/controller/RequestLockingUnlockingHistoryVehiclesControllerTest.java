/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.LinkedList;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.VehiclesHistory;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 *
 * @author bruno
 */
public class RequestLockingUnlockingHistoryVehiclesControllerTest {
    
    RequestLockingUnlockingHistoryVehiclesController instance;
    VehicleRequestsDB vrdb;
    
    @BeforeEach
    public void setUp() {
        vrdb = mock(VehicleRequestsDB.class);
        instance = new RequestLockingUnlockingHistoryVehiclesController(vrdb);
    }

    /**
     * Test of requestLockingUnlockingHistoryVehicles method, of class RequestLockingUnlockingHistoryVehiclesController.
     */
    @Test
    public void testRequestLockingUnlockingHistoryVehicles() throws SQLException {
        System.out.println("requestLockingUnlockingHistoryVehicles");
        String userEmail = "";
        
        VehiclesHistory expResult = new VehiclesHistory(userEmail, new LinkedList<>());
        VehiclesHistory result = instance.requestLockingUnlockingHistoryVehicles(userEmail);
        assertEquals(expResult.toString(), result.toString());
        
        doThrow(SQLException.class).when(vrdb).getVehiclesRequestHistory(userEmail);
        
        assertEquals(null, instance.requestLockingUnlockingHistoryVehicles(userEmail));
    }
    
}
