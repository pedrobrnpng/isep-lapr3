/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author pedro
 */
public class VehicleRequest {
    
    /**
     * Vehicle Request ID.
     */
    private int vehicleRequestID;
    /**
     * Email of the user associated with the vehicle request.
     */
    private String userEmail;
    /**
     * ID of the vehicle associated with the vehicle request.
     */
    private int vehicleID;
    /**
     * ID of the place of origin of the vehicle request.
     */
    private int originID;
    /**
     * ID of the place of destination of the vehicle request.
     */
    private int destinationID;
    /**
     * Time that the vehicle has been unlocked. Also represents the time of start of use.
     */
    private String unlockTime;
    /**
     * Time that the vehicle has been locked. Also represents the time of end of use.
     */
    private String endTime;
    
    /**
     * empty constructor
     */
    public VehicleRequest() {  
    }
    
    /**
     * Constructor
     * @param vehicleRequestID Vehicle Request ID.
     * @param userEmail Email of the user associated with the vehicle request.
     * @param vehicleID ID of the vehicle associated with the vehicle request.
     * @param originID ID of the place of origin of the vehicle request.
     * @param destinationID ID of the place of destination of the vehicle request.
     * @param unlockTime Time that the vehicle has been unlocked. Also represents the time of start of use.
     * @param endTime  Time that the vehicle has been locked. Also represents the time of end of use.
     */
    public VehicleRequest(int vehicleRequestID, String userEmail, int vehicleID, int originID, int destinationID, String unlockTime, String endTime) {
        this.vehicleRequestID = vehicleRequestID;
        this.userEmail = userEmail;
        this.vehicleID = vehicleID;
        this.originID = originID;
        this.destinationID = destinationID;
        this.unlockTime = unlockTime;
        this.endTime = endTime;
    }
    
    /**
     * returns the vehicle request id
     * @return vehicle request id
     */
    public int getVehicleRequestID() {
        return vehicleRequestID;
    }
    /**
     * returns the user email of the request
     * @return user email
     */
    public String getUserEmail() {
        return userEmail;
    }
    
    /**
     * returns the vehicle id of the request
     * @return vehicle id
     */
    public int getVehicleID() {
        return vehicleID;
    }

    /**
     * returns the place of origin of the request
     * @return place of origin
     */
    public int getOriginID() {
        return originID;
    }

    /**
     * returns the place of destination of the request
     * @return  place of destination
     */
    public int getDestinationID() {
        return destinationID;
    }
    
    /**
     * returns the unlock time of the reuqest
     * @return unlock time
     */
    public String getUnlockTime() {
        return unlockTime;
    }
    
    /**
     * returns the lock time of the request
     * @return lock time
     */
    public String getEndTime() {
        return endTime;
    }
    
    /**
     * sets the lock time of the request
     * @param vehicleRequestID
     */
    public void setVehicleRequestID(int vehicleRequestID) {
        this.vehicleRequestID = vehicleRequestID;
    }
    
    /**
     * sets the lock time of the request
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    /**
     * sets the lock time of the request
     * @param vehicleID
     */
    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
    
    /**
     * sets the lock time of the request
     * @param originID
     */
    public void setOriginID(int originID) {
        this.originID = originID;
    }
    
    /**
     * sets the lock time of the request
     * @param destinationID
     */
    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }
    
    /**
     * sets the lock time of the request
     * @param unlockTime
     */
    public void setUnlockTime(String unlockTime) {
        this.unlockTime = unlockTime;
    }
    
    /**
     * sets the lock time of the request
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    /**
     * calculates the cost of this request. if the end time is empty we can´t calculate
     * @return 
     */
    public double calculateCost() {
        if (endTime.isEmpty() || endTime==null) return -1;
        String diff=diffTime();
        String hourss=diff.substring(0, 2);
        int hours=Integer.valueOf(hourss);
        double cost=0;
        cost+=hours*1.5;
        return cost;
    }
    
    /**
     * checks if the trip has been done in less than 15 min
     * @return 
     */
    public boolean tripTimeReward() {
        if (endTime.isEmpty() || endTime == null) return false;
        String diff = diffTime();
        String hourss = diff.substring(0,2);
        int hours = Integer.valueOf(hourss);

        String minutess = diff.substring(4,6);
        int minutes = Integer.valueOf(minutess);

        if(hours == 0) {
            if(minutes < 15) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * calls method diffTime
     * @return 
     */
    public String diffTime() {
        return diffTime(unlockTime, endTime);
    }
    
    /**
     * calculates the difference in the unlock and lock times
     * @param time1
     * @param time2
     * @return 
     */
    public String diffTime(String time1, String time2) {
        Instant inst1 = Instant.parse(time1);
        Instant inst2 = Instant.parse(time2);
        Date date1 = Date.from(inst1);
        Date date2 = Date.from(inst2);
        long result = date2.getTime() - date1.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;

        long elapsedHours = result / hoursInMilli;
        result = result % hoursInMilli;
        long elapsedMinutes = result / minutesInMilli;
        result = result % minutesInMilli;
        long elapsedSeconds = result / secondsInMilli;

        String hoursAdd = "";
        String minAdd = "";
        String secAdd = "";

        if (elapsedHours < 10) {
            hoursAdd = "0";
        }
        if (elapsedMinutes < 10) {
            minAdd = "0";
        }
        if (elapsedSeconds < 10) {
            secAdd = "0";
        }

        String str = hoursAdd + elapsedHours + "h " + minAdd + elapsedMinutes + "m " + secAdd + elapsedSeconds + "s";
        return str;
    }

    /**
     * creates a hash of the instance
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.vehicleRequestID);
        return hash;
    }

    /**
     * compares this instance to another 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleRequest other = (VehicleRequest) obj;
        if (this.vehicleRequestID != other.vehicleRequestID) {
            return false;
        }
        if (this.originID != other.originID) {
            return false;
        }
        if (this.destinationID != other.destinationID) {
            return false;
        }
        return true;
    }
 
}
