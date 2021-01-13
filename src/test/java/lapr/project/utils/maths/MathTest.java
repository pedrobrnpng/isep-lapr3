/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.maths;

import static lapr.project.utils.maths.Mathematics.*;
import org.junit.jupiter.api.Test;;
import static org.junit.Assert.*;

/**
 *
 * @author bruno
 */
public class MathTest {
    
    public MathTest() {
    }

     /**
     * Test of round method, of class 
     */
    @Test
    public void testRound() {
        System.out.println("round");
        double value = 12.12229;
        int places = 4;
        double expResult = 12.1223;
        double result = round(value, places);
        assertEquals(expResult, result, 0.0);
        
        result = round(value, 0);
        assertEquals(12, result, 0.0);
        
        
        try {
            round(value, -1);
            fail("places must be higher or equal than 0");
        } catch (Exception e) {
        }
    }

    /**
     * Test of convergeGeoCoordinatesToX method, of class 
     */
    @Test
    public void testConvergeGeoCoordinatesToX() {
        System.out.println("convergeGeoCoordinatesToX");
        double lat = 90;
        double lon = 90;
        double expResult = 0;
        double result = convergeGeoCoordinatesToX(lat, lon);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of convergeGeoCoordinatesToY method, of class 
     */
    @Test
    public void testConvergeGeoCoordinatesToY() {
        System.out.println("convergeGeoCoordinatesToY");
        double lat = 90;
        double lon = 90;
        double expResult = 0.0;
        double result = convergeGeoCoordinatesToY(lat, lon);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of convergeGeoCoordinatesToZ method, of class 
     */
    @Test
    public void testConvergeGeoCoordinatesToZ() {
        System.out.println("convergeGeoCoordinatesToZ");
        double lat = 90;
        double expResult = 6371;
        double result = convergeGeoCoordinatesToZ(lat);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of reduceDegree method, of class 
     */
    @Test
    public void testReduceDegree() {
        System.out.println("reduceDegree");
        double degree = 400;
        double expResult = 40;
        double result = reduceDegree(degree);
        assertEquals(expResult, result, 0.0);
        expResult=0;
        degree=360;
        result=reduceDegree(degree);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of angleBetweenTwoVectors method, of class 
     */
    @Test
    public void testAngleBetweenTwoVectors() {
        System.out.println("angleBetweenTwoVectors");
        double v1X = convergeGeoCoordinatesToX(41.108941, -7.363382);
        double v1Y = convergeGeoCoordinatesToY(41.108941, -7.363382);
        double v2X = convergeGeoCoordinatesToX(41.415866, -6.038863);
        double v2Y = convergeGeoCoordinatesToY(41.415866, -6.038863);
        double v1length = 4;
        double v2length = 10;
        double expResult = 1.32;
        double result = angleBetweenTwoVectors(v1X, v1Y, v2X, v2Y);
        assertEquals(expResult, result, 0.01);
        v1X=0;
        v1Y=0;
        expResult=0;
        result = angleBetweenTwoVectors(v1X, v1Y, v2X, v2Y);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of projectionOfAVectorInOtherVector method, of class 
     */
    @Test
    public void testProjectionOfAVectorInOtherVector() {
        System.out.println("projectionOfAVectorInOtherVector");
        double projVectorLength = 4;
        double degree = 40.54160187;
        double expResult = 3.039736831;
        double result = projectionOfAVectorInOtherVector(projVectorLength, degree);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of convertKHtoMS method, of class 
     */
    @Test
    public void testConvertKHtoMS() {
        System.out.println("convertKHtoMS");
        double v = 20;
        double expResult = 5.555555556;
        double result = convertKHtoMS(v);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of calculateGeoDistance method, of class 
     */
    @Test
    public void testCalculateGeoDistance() {
        System.out.println("calculateGeoDistance");
        double lat1 = 41.091395;
        double lat2 = 40.406474;
        double lon1 = -8.613142;
        double lon2 = -3.658634;
        double expResult = 421;
        double result = calculateGeoDistance(lat1, lat2, lon1, lon2);
        assertEquals(expResult, result, 10);
    }
    
}
