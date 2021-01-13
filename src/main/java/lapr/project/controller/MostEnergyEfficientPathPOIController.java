/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lapr.project.model.Bicycle;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.EnergyGraph;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
import lapr.project.model.Vehicle;

/**
 *
 * @author Simao
 */
public class MostEnergyEfficientPathPOIController {

    private PathRegistry pathRegistry;
    private POIRegistry poiRegistry;
    private ParkRegistry parkRegistry;
    private ClientRegistry clientRegistry;
    private List<String> report;

    /**
     *
     * @param pr
     * @param por
     * @param par
     */
    public void setRegistrys(ParkRegistry pr, POIRegistry por, PathRegistry par, ClientRegistry cr) {
        this.pathRegistry = par;
        this.poiRegistry = por;
        this.parkRegistry = pr;
        this.clientRegistry = cr;
    }
    
    public PathRegistry getPathRegistry(){
        return this.pathRegistry;
    }

    public POIRegistry getPOIRegistry(){
        return this.poiRegistry;
    }

    public ParkRegistry getParkRegistry(){
        return this.parkRegistry;
    }
    
    public ClientRegistry getClientRegistry(){
        return this.clientRegistry;
    }

    /**
     *
     */
    public MostEnergyEfficientPathPOIController() {
        this.pathRegistry = new PathRegistry();
        this.poiRegistry = new POIRegistry();
        this.parkRegistry = new ParkRegistry();
    }

    private EnergyGraph createGraph(Client user, Vehicle vehicle) {
        Set<Place> places = new HashSet<>();
        places.addAll(parkRegistry.getAllParks());
        places.addAll(poiRegistry.getExistingPOI());
        Set<Path> paths = this.pathRegistry.getAllPaths();
        return new EnergyGraph(places, paths, vehicle, user);
    }

    /**
     *
     * @param user client requesting
     * @param id_park_orig id of origin park
     * @param id_park_dest id of destination park
     * @param asc sorting order, true for ascending, false for descending
     * @param elements elements (POIs) that path must pass through
     * @param vehicle vehicle to be used in path
     * @return
     */
    public Set<List<Path>> createShortestWithPOI(Client user, int id_park_orig, int id_park_dest, boolean asc, Place[] elements, Vehicle vehicle) {
        EnergyGraph graph = createGraph(user, vehicle);
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        return graph.getShortestPathWithPOI(id_park_orig, id_park_dest, elements, asc);
    }

}
