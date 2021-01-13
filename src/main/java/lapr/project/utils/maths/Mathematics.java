/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.maths;

import static java.lang.Math.*;

/**
 *
 * @author bruno
 */
public class Mathematics {
    /**
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) pow(10, places);
        value = value * factor;
        long tmp = java.lang.Math.round(value);
        return (double) tmp / factor;
    }
    
    /**
     * converts degrees to radians
     * @param degrees
     * @return 
     */
    public static double toRadians(double degrees) {
        return degrees * PI / 180;
    }
    
    /**
     * converts radians to degrees
     * @param radians
     * @return 
     */
    public static double toDegrees(double radians) {
        return radians * 180 / PI;
    }
    
    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double convergeGeoCoordinatesToX(double lat, double lon) {
        lat = toRadians(lat);
        lon = toRadians(lon);
        return Physics.RADIUS_EARTH * cos(lat) * cos(lon);
    }

    /**
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double convergeGeoCoordinatesToY(double lat, double lon) {
        lat = toRadians(lat);
        lon = toRadians(lon);
        return Physics.RADIUS_EARTH * cos(lat) * sin(lon);
    }

    /**
     *
     * @param lat
     * @return
     */
    public static double convergeGeoCoordinatesToZ(double lat) {
        lat = toRadians(lat);
        return Physics.RADIUS_EARTH * sin(lat);
    }

    /**
     *
     * @param degree
     * @return
     */
    public static double reduceDegree(double degree) {
        if (degree >= 360) {
            int res = (int) degree / 360;
            degree -= res * 360;
        }
        return degree;
    }
    
    /**
     *
     * @param v1X
     * @param v1Y
     * @param v2X
     * @param v2Y
     * @return
     */
    public static double angleBetweenTwoVectors(double v1X, double v1Y, double v2X, double v2Y){
        double v1Length=java.lang.Math.sqrt((v1X*v1X)+(v1Y*v1Y));
        double v2Length=java.lang.Math.sqrt((v2X*v2X)+(v2Y*v2Y));
        if (v1Length == 0 || v2Length == 0){
            return 0;
        }
        double cossenoTeta = (v1X * v2X + v1Y * v2Y) / (v1Length * v2Length);
        return toDegrees(acos(cossenoTeta));
    }
    
    /**
     *
     * @param projVectorLength
     * @param degree
     * @return
     */
    public static double projectionOfAVectorInOtherVector(double projVectorLength, double degree){
        return projVectorLength * cos(toRadians(degree));
    }
    
    /**
     *
     * @param v
     * @return
     */
    public static double convertKHtoMS(double v){
        return v * 1000 / 3600;
    }
    
    /**
     *
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @return distance in meters
     */
    public static double calculateGeoDistance(double lat1, double lat2, double lon1, double lon2) {
        double φ1 = toRadians(lat1);     //φ1 
        double φ2 = toRadians(lat2);     //φ2
        double Δφ = toRadians(lat2-lat1);
        double Δλ = toRadians(lon2-lon1);

        double a = sin(Δφ/2) * sin(Δφ/2) + cos(φ1) * cos(φ2) * sin(Δλ/2) * sin(Δλ/2);
        double c = 2 * atan2(sqrt(a), sqrt(1-a));

        return Physics.RADIUS_EARTH * c;

    }
    
}
