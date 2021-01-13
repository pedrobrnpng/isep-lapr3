/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.utils.maths.Mathematics;

/**
 *
 * @author Simao
 */
public class CheckNearestParksController {

    Set<Park> parkList;
    Park park;
    ParkDB pdb;
    private ParkRegistry pr;

    /**
     *
     */
    public CheckNearestParksController() {
        pdb = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     *
     * @param pdb
     */
    public CheckNearestParksController(ParkDB pdb) {
        this.pdb = pdb;
    }

    /**
     * gets registries from company to use.
     */
    public void getRegistries() {
        pr = new ParkRegistry();
        pr.setParkDB(pdb);
    }

    /**
     * Method to get all parks
     *
     * @return all parks
     */
    public Set<Park> getAllParks() {
        parkList = pr.getAllParks();
        return parkList;
    }

    /**
     * default method for the nearest parks method that does not receive a
     * radius to search and uses 1km as default
     *
     * @param lat latitude
     * @param lon longitude
     * @param parkList all parks
     * @return nearest parks in 1km radius
     */
    public List<Park> getNearestParks(double lat, double lon, Set<Park> parkList) {
        return getNearestParks(lat, lon, parkList, 1);
    }

    /**
     * receives lat and long to find parks, a parklist of all parks and the
     * radius in which to search
     *
     * @param lat latitude
     * @param lon longitude
     * @param parkList all parks
     * @param radius radius to search
     * @return nearby parks in a list
     */
    public List<Park> getNearestParks(double lat, double lon, Set<Park> parkList, double radius) {
        SortedMap<Double, Park> nearestParksTree = new TreeMap<>();
        getRegistries();
        pr.setParkDB(pdb);
        parkList = getAllParks();
        List<Park> nearestParks = new ArrayList<>();
        if (!parkList.isEmpty()) {
            for (Park park : parkList) {
                double distance = Mathematics.calculateGeoDistance(lat, park.getLatitude(), lon, park.getLongitude());
                if (distance < radius) {
                    nearestParksTree.put(distance, park);
                }
            }
        }
        if (!nearestParksTree.isEmpty()) {
            nearestParks.addAll(nearestParksTree.values());
        }
        return nearestParks;
    }

}
