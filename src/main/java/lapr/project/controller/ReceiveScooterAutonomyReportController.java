/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.data.ScooterDB;
import lapr.project.model.Scooter;
import lapr.project.model.VehiclesRegistry;

/**
 *
 * @author bruno
 */
public class ReceiveScooterAutonomyReportController {
    
    private final VehiclesRegistry vr;

    /**
     *
     */
    public ReceiveScooterAutonomyReportController() {
        this.vr =  new VehiclesRegistry();
    }

    /**
     *
     * @param sdb
     */
    public ReceiveScooterAutonomyReportController(ScooterDB sdb) {
        this.vr =  new VehiclesRegistry();
        vr.setScooterDB(sdb);
    }
    
    /**
     *
     * @param km
     * @return
     */
    public Set<Scooter> getScootersAutonomyByXKms(int km){
        try {
            HashSet<Scooter> list = (HashSet<Scooter>) vr.receiveScooterAutonomyReport();
            return calculateAutonomy(km, list);
        } catch (SQLException ex) {
            Logger.getLogger(ReceiveScooterAutonomyReportController.class.getName()).log(Level.SEVERE, null, ex);
            return new HashSet<>();
        }
    }
    
    private Set<Scooter> calculateAutonomy(int km, HashSet<Scooter> list){
        HashSet<Scooter> newList = new HashSet<>();
        if(km <= 0 || list == null || list.isEmpty()){
            return newList;
        }
        for (Scooter s : list) {
            double battery = s.getMaxBatteryCapacity() * s.getActualBatteryCapacity() * 0.01;
            double time = battery / s.getMotor();
            double distance = time * 20; //e calculado tendo em conta o maior consumo possivel e a máxima velocide deste tipo de veiculos 20km/h
            distance *= 0.7; //a bateria tem por norma uma eficiencia de 70 por cento (valores foram buscados no moodle)
            if (distance < km){
                newList.add(s);
            }
        }
        return newList;        
    }
    
    
    
    
}
