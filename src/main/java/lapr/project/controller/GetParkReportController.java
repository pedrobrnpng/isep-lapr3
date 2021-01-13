/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Scooter;
import lapr.project.model.VehiclesRegistry;
import lapr.project.model.WriteReport;

/**
 *
 * @author Utilizador
 */
public class GetParkReportController {

    private VehiclesRegistry vehicleRegistry;
    private ParkRegistry parkRegistry;

    /**
     * Constructor
     */
    public GetParkReportController() {
        this.vehicleRegistry = new VehiclesRegistry();
        this.parkRegistry = new ParkRegistry();
    }

    /**
     * Returns the park registry associated
     *
     * @return park registry
     */
    public ParkRegistry getParkRegistry() {
        return this.parkRegistry;
    }

    /**
     * Sets the registrys classes
     *
     * @param vr - vehicle registry
     * @param pr - park registry
     */
    public void setRegistrys(VehiclesRegistry vr, ParkRegistry pr) {
        this.vehicleRegistry = vr;
        this.parkRegistry = pr;
    }

    /**
     * Gets the reported related to a park
     *
     * @param parkIdentification
     * @param outputFileName
     * @return report of the park
     */
    public long getParkReport(String parkIdentification, String outputFileName) {
        Park park = this.parkRegistry.getParkDB(parkIdentification);
        Set<Scooter> vl = this.vehicleRegistry.getVehiclesByPark(park.getId());
        List<String> list = new ArrayList<>();
        long num = this.parkRegistry.getParkReport(park, vl, list);
        WriteReport.writeParkReport(list,outputFileName);
        return num;
    }
}
