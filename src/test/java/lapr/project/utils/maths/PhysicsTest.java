/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.maths;

import java.util.HashSet;
import java.util.Set;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.Place;
import lapr.project.model.Vehicle;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static lapr.project.utils.maths.Physics.*;

/**
 *
 * @author bruno
 */
public class PhysicsTest {

    /**
     * Test of calculateKCAL method, of class CalculateEnergy.
     *
     * @throws lapr.project.utils.InvalidInfoClientException
     */
    @Test
    public void testCalculateEnergy() throws InvalidInfoClientException {
        System.out.println("testCalculateEnergy");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        cli.setAvgCyclingSpeed(8.9);
        Park orig = new Park(2, "Porto", 41.273733, -8.378055, 100, "Parque do Porto longe", 10, 10, 200, 201);
        Park dest = new Park(4, "Porto", 41.279539, -8.360846, 400, "Parque do Porto perto", 10, 10, 200, 201);
        Set<Park> h = new HashSet<>();
        h.add(orig);
        h.add(dest);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));

        ParkRegistry p = mock(ParkRegistry.class);
        POIRegistry poi = mock(POIRegistry.class);
        when(p.getParks()).thenReturn(h);
        when(poi.getExistingPOI()).thenReturn(po);

//     * @param path_id - path id
//     * @param id_place_origin - origin place id
//     * @param id_place_destination - destination place id
//     * @param kinectic_coeficient - kinectic coeficient
//     * @param wind_dir - wind direction
//     * @param wind_speed - wind speed
//     */
        Path path = new Path(14, 2, 4, 0.002, 32, 50);

//        String wheelSize, 
//        int id, 
//        int idPark, 
//        int weight, 
//        double aerodynamicCoefficient, 
//        double frontalArea
        Bicycle bike = new Bicycle("12", 4, 2, 8, 0.5, 0.5);

        double expResult = 272177.5;
        double result = calculateEnergy(cli, path, orig, dest, bike);
        assertEquals(expResult, result, 0.2);

    }

    /**
     * Test of calculateKCAL method, of class CalculateEnergy.
     *
     * @throws lapr.project.utils.InvalidInfoClientException
     */
    @Test
    public void testCalculateEnergyDesc() throws InvalidInfoClientException {
        System.out.println("testCalculateEnergy");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        cli.setAvgCyclingSpeed(8.9);
        Park orig = new Park(2, "Porto", 41.273733, -8.378055, 500, "Parque do Porto longe", 10, 10, 200, 201);
        Park dest = new Park(4, "Porto", 41.279539, -8.360846, 400, "Parque do Porto perto", 10, 10, 200, 201);
        Set<Park> h = new HashSet<>();
        h.add(orig);
        h.add(dest);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));

        ParkRegistry p = mock(ParkRegistry.class);
        POIRegistry poi = mock(POIRegistry.class);
        when(p.getParks()).thenReturn(h);
        when(poi.getExistingPOI()).thenReturn(po);

//     * @param path_id - path id
//     * @param id_place_origin - origin place id
//     * @param id_place_destination - destination place id
//     * @param kinectic_coeficient - kinectic coeficient
//     * @param wind_dir - wind direction
//     * @param wind_speed - wind speed
//     */
        Path path = new Path(14, 2, 4, 0.002, 32, 50);

//        String wheelSize, 
//        int id, 
//        int idPark, 
//        int weight, 
//        double aerodynamicCoefficient, 
//        double frontalArea
        Bicycle bike = new Bicycle("12", 4, 2, 8, 0.5, 0.5);

        double expResult = 0;
        double result = calculateEnergy(cli, path, orig, dest, bike);
        assertEquals(expResult, result, 0.0);

    }
    
    /**
     * Test of calculateKCAL method, of class CalculateEnergy.
     *
     * @throws lapr.project.utils.InvalidInfoClientException
     */
    @Test
    public void testCalculateEnergyNoValues() throws InvalidInfoClientException {
        System.out.println("testCalculateEnergy");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 0, 0, 0, 'M');
        cli.setAvgCyclingSpeed(0);
        Park orig = new Park(2, "Porto", 41.273733, -8.378055, 300, "Parque do Porto longe", 10, 10, 200, 201);
        Park dest = new Park(4, "Porto", 41.279539, -8.360846, 400, "Parque do Porto perto", 10, 10, 200, 201);
        Set<Park> h = new HashSet<>();
        h.add(orig);
        h.add(dest);
        Set<POI> po = new HashSet<>();
        po.add(new POI(3, 50, 23, 12, "desc"));

        ParkRegistry p = mock(ParkRegistry.class);
        POIRegistry poi = mock(POIRegistry.class);
        when(p.getParks()).thenReturn(h);
        when(poi.getExistingPOI()).thenReturn(po);

//     * @param path_id - path id
//     * @param id_place_origin - origin place id
//     * @param id_place_destination - destination place id
//     * @param kinectic_coeficient - kinectic coeficient
//     * @param wind_dir - wind direction
//     * @param wind_speed - wind speed
//     */
        Path path = new Path(14, 2, 4, 0.002, 32, 50);

//        String wheelSize, 
//        int id, 
//        int idPark, 
//        int weight, 
//        double aerodynamicCoefficient, 
//        double frontalArea
        Bicycle bike = new Bicycle("12", 2, 0, 5, 0.5, 0.5);

        double expResult = 104035.2;
        double result = calculateEnergy(cli, path, orig, dest, bike);
        System.out.println(result);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateGravityResistance method, of class CalculateEnergy.
     */
    @Test
    public void testCalculateGravityResistance() throws InvalidInfoClientException {
        System.out.println("calculateGravityResistance");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        cli.setAvgCyclingSpeed(8);
        Vehicle b = new Bicycle("12", 4, 2, 8, 0.5, 0.5);
        Place orig = new Park(2, "Porto", 41.273733, -8.378055, 100, "Parque do Porto longe", 10, 10, 200, 201);
        Place dest = new Park(4, "Porto", 41.279539, -8.360846, 400, "Parque do Porto perto", 10, 10, 200, 201);

        double expResult = 155;
        double result = calculateGravityResistance(cli, b, orig, dest);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of calculateWindRes method, of class CalculateEnergy.
     */
    @Test
    public void testCalculateWindRes() throws InvalidInfoClientException {
        System.out.println("calculateWindRes");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        cli.setAvgCyclingSpeed(8);
        Vehicle b = new Bicycle("12", 4, 2, 8, 0.5, 0.5);
        Place orig = new Park(2, "Porto", 41.273733, -8.378055, 100, "Parque do Porto longe", 10, 10, 200, 201);
        Place dest = new Park(4, "Porto", 41.279539, -8.360846, 400, "Parque do Porto perto", 10, 10, 200, 201);

        Path path = new Path(14, 2, 4, 0.002, 32, 7);

        double expResult = 0.3;
        double result = calculateWindRes(cli, b, path, orig, dest);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of calculateRollRes method, of class CalculateEnergy.
     */
    @Test
    public void testCalculateRollRes() throws InvalidInfoClientException {
        System.out.println("calculateRollRes");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        cli.setAvgCyclingSpeed(8);
        Vehicle b = new Bicycle("12", 4, 2, 8, 0.5, 0.5);
        Path path = new Path(14, 2, 4, 0.002, 32, 50);

        double expResult = 1.6;
        double result = calculateRollRes(cli, b, path);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of calculateSlope method, of class CalculateEnergy.
     */
    @Test
    public void testCalculateSlope() {
        System.out.println("calculateSlope");
        Place orig = new Park(2, "Porto", 41.273733, -8.378055, 3000, "Parque do Porto longe", 10, 10, 200, 201);
        Place dest = new Park(4, "Porto", 41.279539, -8.360846, 3300, "Parque do Porto perto", 10, 10, 200, 201);

        double expResult = 0.19;
        double result = calculateSlope(orig, dest);

        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of windSpeed method, of class CalculateEnergy.
     */
    @Test
    public void testWindSpeed() {
        System.out.println("windSpeed");
        double windSpeed = 7;
        double windDir = 32;
        double bikeDir = 30;

        double expResult = -2.9;
        double result = windSpeed(windSpeed, windDir, bikeDir);
        System.out.println(result);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateBearing method, of class CalculateEnergy.
     */
    @Test
    public void testCalculateBearing() {
        System.out.println("calculateBearing");
        double lat1 = 41.273733;
        double lon1 = -8.378055;
        double lat2 = 41.279539;
        double lon2 = -8.360846;

        double expResult = 65.8;
        double result = calculateBearing(lat1, lon1, lat2, lon2);
        assertEquals(expResult, result, 0.1);
    }

}
