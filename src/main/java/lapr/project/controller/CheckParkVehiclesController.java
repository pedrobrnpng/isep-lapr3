/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ParkDB;
import lapr.project.data.ScooterDB;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.ParkRegistry;

/**
 *
 * @author Simao
 */
public class CheckParkVehiclesController {

    Set<Park> parkList;
    Set<Scooter> scooterList;
    Set<Bicycle> bicycleList;
    Park park;
    ParkDB pdb;
    ScooterDB sdb;
    BicycleDB bdb;
    private ParkRegistry pr;

    /**
     *
     * @param sdb
     */
    public void setScooterDB(ScooterDB sdb) {
        this.sdb = sdb;
    }

    /**
     *
     * @param bdb
     */
    public void setBicycleDB(BicycleDB bdb) {
        this.bdb = bdb;
    }

    /**
     *
     * @param pdb
     */
    public void setParkDB(ParkDB pdb) {
        this.pdb = pdb;
        pr.setParkDB(pdb);
    }

    /**
     *
     */
    public CheckParkVehiclesController() {
        this.pdb = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        this.sdb = new ScooterDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        this.bdb = new BicycleDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     *
     */
    public void getRegistries() {
        pr = new ParkRegistry();
    }

    /**
     * grabs all existing parks as a set
     * @return set of parks
     */
    public Set<Park> getExistingParks() {
        parkList = pr.getAllParks();
        return parkList;
    }

    /**
     * grabs set of existing scooters in a park by its id
     * @param idPark id of park
     * @return scooters set
     */
    public Set<Scooter> getExistingScootersByParkID(int idPark) {
        scooterList = sdb.getScootersByParkID(idPark);
        return scooterList;
    }

    /**
     * grabs set of existing bicycles in a park by its id
     * @param idPark id of park
     * @return bicycles set
     */
    public Set<Bicycle> getExistingBicyclesByParkID(int idPark) {
        return bdb.getBicyclesByParkID(idPark);
    }

}
