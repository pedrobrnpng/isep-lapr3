/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class VehicleRequestTest {
    
    private VehicleRequest vRequest ;
    
    public VehicleRequestTest() {
        Instant endTime=Instant.parse("2020-10-10T10:35:00.00Z");
        vRequest = new VehicleRequest(2,"pedro",3,2,3,endTime.minus(30, ChronoUnit.MINUTES).toString(), endTime.toString()) ;
    }

    /**
     * Test of getVehicleRequestID method, of class VehicleRequest.
     */
    @Test
    public void testGetVehicleRequestID() {
        System.out.println("getVehicleRequestID");
        Integer expResult = 2;
        Integer result = vRequest.getVehicleRequestID();
        assertEquals(expResult, result);

    }

    /**
     * Test of getUserEmail method, of class VehicleRequest.
     */
    @Test
    public void testGetUserEmail() {
        System.out.println("getUserEmail");
        String expResult = "pedro";
        String result = vRequest.getUserEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVehicleID method, of class VehicleRequest.
     */
    @Test
    public void testGetVehicleID() {
        System.out.println("getVehicleID");
        Integer expResult = 3;
        Integer result = vRequest.getVehicleID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUnlockTime method, of class VehicleRequest.
     */
    @Test
    public void testGetUnlockTime() {
        System.out.println("getUnlockTime");
        String expResult = Instant.now().toString();
        vRequest.setUnlockTime(expResult);
        String result = vRequest.getUnlockTime();
        assertEquals(result, expResult);
    }

    /**
     * Test of getEndTime method, of class VehicleRequest.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        String expResult = Instant.now().toString();
        vRequest.setEndTime(expResult);
        String result = vRequest.getEndTime();
        assertEquals(result, expResult);
    }
    
    /**
     * Test of getOriginID method, of class VehicleRequest.
     */
    @Test
    public void testGetOriginID() {
        System.out.println("getOriginID");
        vRequest.setOriginID(4);
        int expResult = 4;
        int result = vRequest.getOriginID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDestinationID method, of class VehicleRequest.
     */
    @Test
    public void testGetDestinationID() {
        System.out.println("getDestinationID");
        vRequest.setDestinationID(4);
        int expResult = 4;
        int result = vRequest.getDestinationID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOriginID method, of class VehicleRequest.
     */
    @Test
    public void testSetOriginID() {
        System.out.println("setOriginID");
        int originID = 0;
        vRequest.setOriginID(originID);
    }

    /**
     * Test of setDestinationID method, of class VehicleRequest.
     */
    @Test
    public void testSetDestinationID() {
        System.out.println("setDestinationID");
        int destinationID = 0;
        vRequest.setDestinationID(destinationID);
    }

    /**
     * Test of setVehicleRequestID method, of class VehicleRequest.
     */
    @Test
    public void testSetVehicleRequestID() {
        System.out.println("setVehicleRequestID");
        Integer vehicleRequestID = 4;
        vRequest.setVehicleRequestID(vehicleRequestID);
    }

    /**
     * Test of setUserEmail method, of class VehicleRequest.
     */
    @Test
    public void testSetUserEmail() {
        System.out.println("setUserEmail");
        String userEmail = "joao";
        vRequest.setUserEmail(userEmail);
    }

    /**
     * Test of setVehicleID method, of class VehicleRequest.
     */
    @Test
    public void testSetVehicleID() {
        System.out.println("setVehicleID");
        Integer vehicleID = 3;
        vRequest.setVehicleID(vehicleID);
    }
    
    /**
     * Test of setUnlockTime method, of class VehicleRequest.
     */
    @Test
    public void testSetUnlockTime() {
        System.out.println("setUnlockTime");
        String unlockTime = Instant.now().toString();
        vRequest.setUnlockTime(unlockTime);
    }

    /**
     * Test of setEndTime method, of class VehicleRequest.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        String endTime = Instant.now().toString();
        vRequest.setEndTime(endTime);
    }

    /**
     * Test of hashCode method, of class VehicleRequest.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int vehicleRequestID=312;
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(vehicleRequestID);
        int expResult = hash;
        VehicleRequest instance = new VehicleRequest();
        instance.setVehicleRequestID(vehicleRequestID);
        int result = instance.hashCode();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Trip.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = vRequest.equals(obj);
        assertEquals(expResult, result);
    }
    
   @Test
    public void testEquals2() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = vRequest;
        boolean result = vRequest.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals");
        boolean expResult = true;
        Object obj = new VehicleRequest(2,"pedro",3,2,3,vRequest.getUnlockTime(),vRequest.getEndTime()) ;
        boolean result = vRequest.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals4() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new VehicleRequest(5,"pedro",3,2,3,Instant.now().toString(), Instant.now().toString()) ;
        boolean result = vRequest.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals5() {
        System.out.println("equals");
        boolean expResult = false;
        Object obj = new VehicleRequestRegistry() ;
        boolean result = vRequest.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateCost method, of class VehicleRequest.
     */
    @Test
    public void testCalculateCost() {
        System.out.println("calculateCost");
        Instant inst=Instant.parse("2020-10-10T10:35:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(2, ChronoUnit.HOURS).toString();
        VehicleRequest vr= new VehicleRequest(1, "joaolealmgs3@gmail.com", 1, 2, 3, time, time2);
        double expResult = 3;
        double result = vr.calculateCost();
        assertEquals(expResult, result, 0.0);
        vr= new VehicleRequest(1, "joaolealmgs3@gmail.com", 1, 2, 3, time, "");
        expResult = -1;
        result = vr.calculateCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of diffTime method, of class VehicleRequest.
     */
    @Test
    public void testDiffTime() {
        System.out.println("diffTime");
        Instant inst=Instant.parse("2020-10-10T10:35:00.00Z");
        String time= inst.toString();
        String time2 = inst.minus(32, ChronoUnit.MINUTES).toString();
        String expResult = "00h 32m 00s";
        String result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        time2= inst.minus(60, ChronoUnit.SECONDS).toString();
        expResult = "00h 01m 00s";
        result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        time2= inst.minus(10, ChronoUnit.HOURS).toString();
        expResult = "10h 00m 00s";
        result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        time2= inst.minus(59, ChronoUnit.SECONDS).toString();
        expResult = "00h 00m 59s";
        result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        time2= inst.minus(10, ChronoUnit.SECONDS).toString();
        expResult = "00h 00m 10s";
        result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        time2= inst.minus(10, ChronoUnit.MINUTES).toString();
        expResult = "00h 10m 00s";
        result = vRequest.diffTime(time2, time);
        assertEquals(expResult, result);
        expResult= "00h 30m 00s";
        result = vRequest.diffTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of tripTimeReward method, of class VehicleRequest.
     */
    @Test
    public void testTripTimeReward() {
        System.out.println("tripTimeReward");

        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(16, ChronoUnit.MINUTES).toString();
        
        vRequest.setUnlockTime(time);
        vRequest.setEndTime(time2);
        
        boolean expResult = false;
        boolean result = vRequest.tripTimeReward();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of tripTimeReward method, of class VehicleRequest.
     */
    @Test
    public void testTripTimeReward2() {
        System.out.println("tripTimeReward");
        VehicleRequest instance = new VehicleRequest();
        
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(13, ChronoUnit.MINUTES).toString();
        
        vRequest.setUnlockTime(time);
        vRequest.setEndTime(time2);
        
        boolean expResult = true;
        boolean result = vRequest.tripTimeReward();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of tripTimeReward method, of class VehicleRequest.
     */
    @Test
    public void testTripTimeReward3() {
        System.out.println("tripTimeReward");
        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(15, ChronoUnit.MINUTES).toString();
        
        vRequest.setUnlockTime(time);
        vRequest.setEndTime(time2);
        
        boolean expResult = false;
        boolean result = vRequest.tripTimeReward();
        assertEquals(expResult, result);

    }
    
    /**
     * Test of tripTimeReward method, of class VehicleRequest.
     */
    @Test
    public void testTripTimeReward4() {
        System.out.println("tripTimeReward");

        Instant inst=Instant.parse("2020-10-10T10:00:00.00Z");
        String time= inst.toString();

        vRequest.setUnlockTime(time);
        vRequest.setEndTime("");
        
        boolean expResult = false;
        boolean result = vRequest.tripTimeReward();
        assertEquals(expResult, result);

    }

}
