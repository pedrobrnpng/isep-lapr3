/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import lapr.project.data.VehicleDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

/**
 *
 * @author joaol
 */
public class ChangeParkAvailabilityControllerTest {
    
    private ChangeParkAvailabilityController cpac;
    private VehicleDB vdb;

    
    public ChangeParkAvailabilityControllerTest() throws InvalidInfoClientException, SQLException {
        
    }

    @BeforeEach 
    public void setUp() throws Exception {
        cpac = new ChangeParkAvailabilityController();
        vdb = mock(VehicleDB.class);
        cpac = new ChangeParkAvailabilityController(vdb);
        Vehicle vehicle1 = new Bicycle("15'",1, 1, 15, 0.7, 8);
        HashSet<Vehicle> setVehicle = new HashSet<>();
        setVehicle.add(vehicle1);
        when(vdb.getAllVehicles()).thenReturn(setVehicle);
        
    }

    @Test
    public void testGetExistingVehicles() {
        System.out.println("getExistingVehicles");
        Vehicle vehicle2 = new Bicycle("15'",1, 1, 15, 0.7, 8);
        HashSet<Vehicle> setVehicle2 = new HashSet<>();
        setVehicle2.add(vehicle2);
        Set<Vehicle> expResult = setVehicle2;
        Set<Vehicle> result = cpac.getExistingVehicles();
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateVehicleTrue() {
        System.out.println("validateVehicle");
        int id_vehicle = 1;
        boolean expResult = true;
        boolean result = cpac.validateVehicle(id_vehicle);
        assertEquals(expResult, result);
    }
    
        @Test
    public void testValidateVehicleFalse() {
        System.out.println("validateVehicle");
        int id_vehicle = 2;
        boolean expResult = false;
        boolean result = cpac.validateVehicle(id_vehicle);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateAvailability() {
        try{
        System.out.println("updateAvailability");
        int id_vehicle = 1;
        assertTrue(cpac.updateAvailability(id_vehicle));
        }catch(Exception e){
            e.printStackTrace();
            fail("updateAvailability failed");
        }
        try {
            int id_vehicle = 3;
            assertFalse(cpac.updateAvailability(id_vehicle));
            }catch(Exception e){
            e.printStackTrace();
            fail("updateAvailability failed");
        }
    }
    
}
