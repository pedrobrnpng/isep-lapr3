/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.POIDB;
import lapr.project.data.ParkDB;
import lapr.project.model.Client;
import lapr.project.model.Path;
import lapr.project.model.Place;
import lapr.project.model.Vehicle;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import static lapr.project.utils.maths.Physics.calculateEnergy;

/**
 *
 * @author pedro
 */
public class CalculateCaloriesController {
    
    private final static String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private final static String USERDB = "LAPR3_2019_G022";
    private final static String PASSDB = "qwerty";
    
    private ParkDB parkDB;
    private POIDB poiDB;
    
    public void setDB(ParkDB p, POIDB po) {
        this.parkDB = p;
        this.poiDB = po;
    }
    
    public CalculateCaloriesController() {
        this.parkDB = new ParkDB(URLDB, USERDB, PASSDB);
        this.poiDB = new POIDB(URLDB, USERDB, PASSDB);
    }
    
    public double calculateCalories(Path path, Client cli, Vehicle v) throws InvalidInfoClientException, SQLException {
       
        double kcal = 0 ;
        
        Set<Place> placeList = new HashSet<>();
        placeList.addAll(parkDB.getExistingParks());
        placeList.addAll(poiDB.getExistingPOI());
        
        Place orig = null;
        Place dest = null;
        for(Place p : placeList) {
            if(p.getId() == path.getId_place_destination()) 
                dest = p;
            if(p.getId() == path.getId_place_origin())
                orig = p;
        }
        
        if(orig == null || dest == null)
            return 0;
        
        kcal = calculateEnergy(cli, path, orig, dest, v);
        kcal = kcal / 4186;
        return kcal;
        
    }
    
}
