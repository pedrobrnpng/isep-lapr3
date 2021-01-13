/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.maths;

import static java.lang.Math.*;
import java.math.BigDecimal;
import lapr.project.model.Client;
import lapr.project.model.Path;
import lapr.project.model.Place;
import lapr.project.model.Vehicle;
import static lapr.project.utils.maths.Mathematics.*;

/**
 *
 * @author bruno
 */
public class Physics {

    /**
     *
     */
    public static final double  RADIUS_EARTH = 6371;
    
    /**
     * value of gravity
     */
    public static final double GRAVITY = 9.80655 ;
    
    /**
     * value of air density
     */
    public static final double AIR_DENSITY = 1.226;

    /**
     * calculates the joules expent in a trip that goes through the determined path
     * time must be received in seconds!!
     * @param user user 
     * @param path path 
     * @param orig 
     * @param dest 
     * @param bike vehicle
     * @return
     */
    public static double calculateEnergy(Client user, Path path, Place orig, Place dest, Vehicle bike) { 
        
        double avgSpeed = user.getAvgCyclingSpeed() * 5/18;
        
        if((dest.getElevation() - orig.getElevation()) < 0)
            return 0 ;

        if(avgSpeed == 0)
            avgSpeed = 4.17;
        
        double gravResistance = calculateGravityResistance(user, bike, orig, dest);
        double rollResistance = calculateRollRes(user, bike, path);
        double windResistance = calculateWindRes(user, bike, path, orig, dest);
        
        double distance = calculateGeoDistance(orig.getLatitude(), dest.getLatitude(), orig.getLongitude(), dest.getLongitude()) * 1000;
        
        
        double time = distance / avgSpeed;
        double power = (gravResistance + rollResistance + windResistance) * avgSpeed ;
        double energy = (power * time) ;
        
        return energy ;
    }
    
    /**
     * calculates the resistance of gravity in a path
     * @param c
     * @param b
     * @param orig
     * @param dest
     * @return 
     */
    public static double calculateGravityResistance(Client c, Vehicle b, Place orig, Place dest) {
        double clientMass = c.getWeight();
        double vehicleMass = b.getWeight();
        
        if(clientMass == 0)
            clientMass = 75;
        if(vehicleMass == 0)
            vehicleMass = 8;
        
        double totalMass =  vehicleMass + clientMass; 

        double slope = calculateSlope(orig, dest);
        double gravityRes = GRAVITY * totalMass * slope;
        
        return gravityRes ;
    }
    
    /**
     * calculates the wind resistance in a path
     * @param c client
     * @param b vehicle 
     * @param p path
     * @param orig place of origin  
     * @param dest place of destination
     * @return 
     */
    public static double calculateWindRes(Client c, Vehicle b, Path p, Place orig, Place dest){
        double bikeDir = calculateBearing(orig.getLatitude(),orig.getLongitude(),dest.getLatitude(), dest.getLongitude());
        double windSpeed = windSpeed(p.getWind_speed(), p.getWind_dir(), bikeDir);

        double windRes = 0.5 * AIR_DENSITY * b.getAerodynamicCoefficient() * b.getFrontalArea() * pow((windSpeed*5/18), 2) ;
        
        return windRes;
    }
    
    /**
     * calculates the roll resistance in a path
     * @param c client
     * @param b vehicle 
     * @param p path
     * @return 
     */
    public static double calculateRollRes(Client c, Vehicle b, Path p) {
        double totalMass = c.getWeight() + b.getWeight();
        if(totalMass == 0) {
            totalMass = 83;
        }
        double rollRes = totalMass * GRAVITY * p.getKinectic_coeficient();
        
        return rollRes;
    }  
    
    /**
     * calculates the slope of the path
     * @param orig place of origin  
     * @param dest place of destination
     * @return 
     */
    public static double calculateSlope(Place orig, Place dest) {
        double distance = calculateGeoDistance(orig.getLatitude(), dest.getLatitude(), orig.getLongitude(), dest.getLongitude()) * 1000;
        double diffAlt = dest.getElevation() - orig.getElevation();
        double slope = diffAlt / distance ;
        
        return slope ;
    }
    
    /**
     * returns the wind speed affecting the bike
     * @param windSpeed wind speed
     * @param windDir direction of wind in degrees
     * @param bikeDir direction of the bike in degrees
     * @return 
     */
    public static double windSpeed(double windSpeed, double windDir, double bikeDir) {
        double angle = bikeDir - windDir % 360;
        windSpeed = windSpeed * cos(angle);
        return windSpeed;
    }
    
    /**
     * calculates the bearing of where the client is headed
     * @param lat1 latitude of origin
     * @param lon1 longitude of origin
     * @param lat2 latitude of destination
     * @param lon2 longitude of destination
     * @return 
     */
    public static double calculateBearing(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Mathematics.toRadians(lat1);
        lon1 = Mathematics.toRadians(lon1);
        lat2 = Mathematics.toRadians(lat2);
        lon2 = Mathematics.toRadians(lon2);
        
        double y = sin(lon2 - lon1) * cos(lat2);
        double x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1);
        double bearing = atan2(y,x);
        bearing = Mathematics.toDegrees(bearing);
        if(BigDecimal.valueOf(bearing).compareTo(BigDecimal.valueOf(0.0)) != 0) {
            return (bearing + 360) % 360;
        }
        return 360.0;
    }
    
    
}
