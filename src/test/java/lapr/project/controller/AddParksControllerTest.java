/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
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
public class AddParksControllerTest {
    AddParksController addParksController;
    public AddParksControllerTest() {
        addParksController=new AddParksController();
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
     * Test of newPark method, of class AddParksController.
     */
    @Test
    public void testNewPark() {
        System.out.println("newPark");
        String identification = "porto";
        double latitude = 2.0;
        double longitude = 3.0;
        double elevation = 4.0;
        String description = "parque";
        int maxNumberBicycles = 4;
        int maxNumberScooter = 5;
        double inputVoltage = 6.0;
        double inputCurrent = 7.0;
        addParksController.newPark(identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        ParkDB pdb= mock(ParkDB.class);
        addParksController.setParkDB(pdb);
        when(pdb.addPark(new Park(addParksController.getPark().getId(),identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent))).thenReturn(true);
        boolean result= addParksController.addParks();
        assertEquals(true,result);
        addParksController.getParkRegistry().setParkDB(pdb);
        Set<Park> pk= new HashSet<>();
        pk.add(new Park(addParksController.getPark().getId(),identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent));
        when(pdb.AddAllParks(pk)).thenReturn(1);
        assertEquals(1,addParksController.addAllParks());
    }
    
}
