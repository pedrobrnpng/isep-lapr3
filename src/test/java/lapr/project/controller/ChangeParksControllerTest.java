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
public class ChangeParksControllerTest {

    ChangeParksController changeParksController;

    public ChangeParksControllerTest() {
        changeParksController = new ChangeParksController();
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
     * Test of updatePark method, of class ChangeParksController.
     */
    @Test
    public void testUpdatePark() {
        System.out.println("updatePark");
        int identification = 1;
        ParkDB parkDB = mock(ParkDB.class);
        changeParksController.setParkDB(parkDB);
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 23;
        int maxNumberScooter = 10;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        when(parkDB.getPark(1000)).thenReturn(new Park(identification,"Porto", latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent));
        changeParksController.getPark(identification);
        changeParksController.updateInformation("Porto",latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        boolean expResult = false;
        boolean result = changeParksController.updatePark();
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePark method, of class ChangeParksController.
     */
    @Test
    public void testUpdatePark2() {
        System.out.println("updatePark");
        String identification = "Porto";
        ParkDB parkDB = mock(ParkDB.class);
        changeParksController.setParkDB(parkDB);
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 23;
        int maxNumberScooter = 10;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        when(parkDB.getPark(1000)).thenReturn(new Park(1,identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent));
        changeParksController.getPark(1);
        changeParksController.updateInformation(identification,latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        boolean expResult = false;
        boolean result = changeParksController.updatePark();
        assertEquals(expResult, result);
    }
    
      /**
     * Test of updatePark method, of class ChangeParksController.
     */
    @Test
    public void testUpdatePark3() {
        System.out.println("updatePark");
        String identification = "Porto";
        ParkDB parkDB = mock(ParkDB.class);
        changeParksController.setParkDB(parkDB);
        double latitude = 40.0;
        double longitude = 43.0;
        double elevation = 5.0;
        String description = "Parque do Porto";
        int maxNumberBicycles = 23;
        int maxNumberScooter = 10;
        double inputVoltage = 1.0;
        double inputCurrent = 2.0;
        when(parkDB.getPark(1000)).thenReturn(new Park(1000,identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent));
        when(parkDB.updatePark(new Park(1000,identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent))).thenReturn(true);
        changeParksController.getPark(1000);
        changeParksController.updateInformation(identification,latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        boolean expResult = true;
        boolean result = changeParksController.updatePark();
        assertEquals(expResult, result);
    }

}
