/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.VehicleRequestsDB;

/**
 *
 * @author pedro
 */
public class VehicleRequestRegistry {
    
    /**
     * list of requested vehicles
     */
    private HashSet<VehicleRequest> vehicleReqList; 
    private VehicleRequestsDB vrdb;
    
    /**
     * empty constructor
     */
    public VehicleRequestRegistry() {
        this.vehicleReqList = new HashSet<>();
    }
    
    /**
     *
     * @param vrdb
     */
    public VehicleRequestRegistry(VehicleRequestsDB vrdb) {
        this.vehicleReqList = new HashSet<>() ;
        this.vrdb = vrdb;
    }
    
    /**
     * full constructor
     * @param list 
     */
    public VehicleRequestRegistry(HashSet<VehicleRequest> list) {
        this.vehicleReqList = list ;
    }
    
    /**
     * creates a new vehicleRequest
     * @param vehicleRequestID 
     * @param userEmail 
     * @param vehicleID
     * @param placeOrigID
     * @param placeDestID
     * @param pathID
     * @param unlockTime
     * @param endTime
     * @return new vehicle request
     */
    public VehicleRequest newVehicleRequest(int vehicleRequestID, String userEmail, int vehicleID, int placeOrigID, int placeDestID, String unlockTime, String endTime) {
        VehicleRequest vReq = new VehicleRequest( vehicleRequestID, userEmail, vehicleID, placeOrigID, placeDestID,  unlockTime, endTime);
        this.vehicleReqList.add(vReq);
        return vReq;
    }
    
    /**
     * requests the lock/ unlock history of the vehicles of a certain user
     * @param userEmail
     * @return
     * @throws SQLException 
     */
    public VehiclesHistory requestLockingUnlockingHistoryVehicles(String userEmail) throws SQLException{
        return new VehiclesHistory(userEmail, (LinkedList<VehiclesHistoryLine>) vrdb.getVehiclesRequestHistory(userEmail));
    }
    
    /**
     * returns a vehicle request
     * @param vrId
     * @return 
     */
    public VehicleRequest getVehicleRequest(int vrId) {
        return vrdb.getVehicleRequest(vrId);
    }

   /**
     * Receives a date String in a specific format and returns active requests during that date to report upon
     * @param date format: "dd/MM/yyyy HH:mm:ss"
     * @return 
     */
    public Set<VehicleRequest> receiveUnlockedVehicles(String date){
        return vrdb.getActiveRequestsGivenTime(date);
    }

    /**
     * receives a set of vehicle requests in which to report unlocked vehicles upon
     * @param vehicReqs
     * @return a string containing the report of unlocked vehicles and the users unlocking them
     */
    public String getUnlockingReport(Set<VehicleRequest> vehicReqs) {
        StringBuilder sb = new StringBuilder();
        
        for (VehicleRequest vR : vehicReqs) {   
                sb.append("Vehicle ID: ").append(vR.getVehicleID()).append("\nTime of unlocking: ").append(vR.getUnlockTime()).append("\nUnlocker: ").append(vR.getUserEmail()).append("\n");
            }
        return sb.toString();
    }
  
    /**
     * hashes the instance
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.vehicleReqList);
        return hash;
    }

    /**
     * compares this instance to anther
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
        final VehicleRequestRegistry other = (VehicleRequestRegistry) obj;
        return Objects.equals(this.vehicleReqList, other.vehicleReqList);
    }

    
}
