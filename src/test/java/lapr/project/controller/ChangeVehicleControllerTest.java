/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.BicycleDB;
import lapr.project.data.ScooterDB;
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
public class ChangeVehicleControllerTest {
    
    private static ChangeVehicleController instance;
    private static BicycleDB bdb;
    private static ScooterDB sdb;

    @BeforeAll
    public static void setUp() throws Exception {
        bdb = mock(BicycleDB.class);
        sdb = mock(ScooterDB.class);
        instance = new ChangeVehicleController(sdb, bdb);
        ChangeVehicleController instance2 = new ChangeVehicleController();
        instance2.getClass();
    }

    /**
     * Test of changeVehicle method, of class ChangeVehicleController.
     */
    @Test
    public void testChangeVehicle_5args() throws SQLException {
        System.out.println("changeVehicle");
        int id = 1;
        int idPark = 1;
        int maxBatteryLevel = 1;
        int actualBatteryLevel = 1;
        String scooterType = "0";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        int motor = 1;
        boolean expResult = true;
        boolean result = instance.changeVehicle(scooterType, maxBatteryLevel, actualBatteryLevel, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        assertEquals(expResult, result);
        
        doThrow(SQLException.class).when(sdb).updateScooter(any(String.class), any(Integer.class), any(Integer.class), any(Integer.class), any(Integer.class), any(Integer.class), any(Double.class), any(Double.class), any(Integer.class));
        
        expResult = false;
        result = instance.changeVehicle(scooterType, maxBatteryLevel, actualBatteryLevel, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeVehicle method, of class ChangeVehicleController.
     */
    @Test
    public void testChangeVehicle_3args() throws SQLException {
        System.out.println("changeVehicle");
        int id = 1;
        int idPark = 1;
        String wheelSize = "2";
        int weight = 1;
        double aerodynamicCoefficient = 1;
        double frontalArea = 1;
        boolean expResult = true;
        boolean result = instance.changeVehicle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        assertEquals(expResult, result);
        
        doThrow(SQLException.class).when(bdb).updateBicycle(any(String.class), any(Integer.class), any(Integer.class), any(Integer.class), any(Double.class), any(Double.class));
        
        expResult = false;
        result = instance.changeVehicle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        assertEquals(expResult, result);
    }
    
}
