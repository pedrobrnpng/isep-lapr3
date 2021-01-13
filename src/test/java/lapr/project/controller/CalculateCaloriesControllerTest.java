/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.Park;
import lapr.project.model.Path;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class CalculateCaloriesControllerTest {
    
    public CalculateCaloriesControllerTest() {
    }

    /**
     * Test of calculateCalories method, of class CalculateCaloriesController.
     */
    @Test
    public void testCalculateCalories() throws Exception {
        System.out.println("calculateCalories");

        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');       
        cli.setAvgCyclingSpeed(8.9);
        Park orig = new Park(2,"Porto", 41.273733, -8.378055, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park dest = new Park(4,"Porto", 41.279539, -8.360846, 600, "Parque do Porto perto", 10, 10, 200, 201);
        Park p1 = new Park(5,"Porto", 41.273733, -8.378055, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park p2 = new Park(6,"Porto", 41.279123, -8.360236, 600, "Parque do Porto perto", 10, 10, 200, 201);
        Park p3 = new Park(7,"Porto", 41.273233, -8.378354, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park p4 = new Park(8,"Porto", 41.2795341, -8.360246, 600, "Parque do Porto perto", 10, 10, 200, 201);
        
        Set<Park> h = new HashSet<>();
        h.add(orig );
        h.add(dest);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        ParkDB pdb = mock(ParkDB.class);
        POIDB podb = mock(POIDB.class);
        when(pdb.getExistingParks()).thenReturn(h);
        when(podb.getExistingPOI()).thenReturn(po);
        CalculateCaloriesController instance = new CalculateCaloriesController();
        instance.setDB(pdb, podb);
        double expResult = 26.1;
        Path path = new Path(14,2,4,0.002,32,50);
        Bicycle bike = new Bicycle("12",4,2,8,0.5,0.5);
        
        double result = instance.calculateCalories(path,cli,bike);
        assertEquals(result,expResult,0.1);
    }
    
    /**
     * Test of calculateCalories method, of class CalculateCaloriesController.
     */
    @Test
    public void testCalculateCalories2() throws Exception {
        System.out.println("calculateCalories");

        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.75, 75, 'M');       
        cli.setAvgCyclingSpeed(8.9);
        Park orig = new Park(2,"Porto", 41.273733, -8.378055, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park dest = new Park(4,"Porto", 41.279539, -8.360846, 600, "Parque do Porto perto", 10, 10, 200, 201);
        Park p1 = new Park(5,"Porto", 41.273733, -8.378055, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park p2 = new Park(6,"Porto", 41.279123, -8.360236, 600, "Parque do Porto perto", 10, 10, 200, 201);
        Park p3 = new Park(7,"Porto", 41.273233, -8.378354, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park p4 = new Park(8,"Porto", 41.2795341, -8.360246, 600, "Parque do Porto perto", 10, 10, 200, 201);
        
        Set<Park> h = new HashSet<>();
        h.add(orig );
        h.add(dest);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));
        ParkDB pdb = mock(ParkDB.class);
        POIDB podb = mock(POIDB.class);
        when(pdb.getExistingParks()).thenReturn(h);
        when(podb.getExistingPOI()).thenReturn(po);
        CalculateCaloriesController instance = new CalculateCaloriesController();
        instance.setDB(pdb, podb);
        double expResult = 0.0;
        Path path = new Path(14,9,14,0.002,32,50);
        Bicycle bike = new Bicycle("12",4,2,8,0.5,0.5);
        
        double result = instance.calculateCalories(path,cli,bike);
        assertEquals(result,expResult,0.1);
   }
    
}
