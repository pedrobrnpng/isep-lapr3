/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.data.VehicleDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
import lapr.project.model.WriteReport;


/**
 *
 * @author Simao
 */
public class ReceiveUnlockedVehiclesReportController {
    Set<Park> parkList;
    Set<Scooter> scooterList;
    Set<Bicycle> bicycleList;
    Park park;
    ParkDB pdb;
    
    VehicleRequestRegistry vrr;
    VehicleRequestsDB vrdb;
    VehicleDB vdb;
    
    /**
     *
     * @param vrdb
     */
    public ReceiveUnlockedVehiclesReportController(VehicleRequestsDB vrdb){
        pdb= new ParkDB();
        this.vdb=new VehicleDB();
        this.vrdb=vrdb;
        this.vrr=new VehicleRequestRegistry();
    }
    
    public ReceiveUnlockedVehiclesReportController(){
        pdb= new ParkDB();
        this.vdb=new VehicleDB();
        this.vrdb=new VehicleRequestsDB();
        this.vrr=new VehicleRequestRegistry();
    }
         
    public VehicleRequestsDB getVRDB(){
        return this.vrdb;
    }
    
    public void setVRDB(VehicleRequestsDB vrdb){
        this.vrdb=vrdb;
    }
    
    /**
     * Receives a date String in a specific format and returns active requests during that date to report upon
     * @param date format: "dd/MM/yyyy HH:mm:ss"
     * @return
     * @throws ParseException 
     */
   public Set<VehicleRequest> getActiveRequests(String date) throws ParseException{
      return vrdb.getActiveRequestsGivenTime(date);
   }
   
   /**
    * Receives a date String in a specific format and returns a string for the report
    * @param date format: "dd/MM/yyyy HH:mm:ss"
    * @return 
     * @throws java.text.ParseException 
    */
   public String getUnlockingReport(String date) throws ParseException{
        Set<VehicleRequest> vehicleReqs = this.getActiveRequests(date);
        String report = vrr.getUnlockingReport(vehicleReqs);
        WriteReport wr = new WriteReport();
        wr.writeUnlockingReport(report, "UnlockingReport.txt");
        return report;
        } 
}