/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.model.POI;
import lapr.project.model.POIRegistry;
import lapr.project.model.Park;
import lapr.project.model.ParkRegistry;
import lapr.project.model.Path;
import lapr.project.model.PathRegistry;
import lapr.project.model.Place;
import lapr.project.model.PlacesGraph;

/**
 *
 * @author Utilizador
 */
public class ShortestPathController {

    private ParkRegistry parkRegistry;
    private POIRegistry poiRegistry;
    private PathRegistry pathRegistry;

    /**
     * Sets the registrys associated
     *
     * @param pr - park registry
     * @param por - POIRegistry
     * @param par - PathRegistry
     */
    public void setRegistrys(ParkRegistry pr, POIRegistry por, PathRegistry par) {
        this.parkRegistry = pr;
        this.poiRegistry = por;
        this.pathRegistry = par;
    }

    /**
     * Constructor
     */
    public ShortestPathController() {
        this.parkRegistry = new ParkRegistry();
        poiRegistry = new POIRegistry();
        this.pathRegistry = new PathRegistry();
    }
    
    /**
     *
     * @param d
     * @param d1
     * @param d2
     * @param d3
     * @param lp
     * @return
     */
    public int pathDistanceTO(double d, double d1, double d2, double d3, LinkedList<Place> lp) {
         PlacesGraph pgPath = createGraph();
         return (int) pgPath.pathTo(this.parkRegistry.getParkByCoordinates(d,d1), this.parkRegistry.getParkByCoordinates(d2,d3),lp);
    }

    /**
     * Creates the shortest path beetween two parks receiving de coordinates of
     * those parks
     *
     * @param latO - latitude of the origin
     * @param lonO - longitude of the origin
     * @param latD - latitude of the destination
     * @param lonD - longitude of the destination
     * @param lpaths - list of paths
     * @param report
     * @return distance of the path
     */
    public long createShortestPath(double latO, double lonO, double latD, double lonD, Set<List<Path>> lpaths, List<String> report) {
        PlacesGraph pg = createGraph();
        LinkedList<Place> lPlaces = new LinkedList<>();
        int id_park_origin = this.parkRegistry.getParkByCoordinates(latO, lonO).getId();
        int id_park_destination = this.parkRegistry.getParkByCoordinates(latD, lonD).getId();
        return createShortestPath(pg, id_park_origin, id_park_destination, lpaths, report);
    }

    /**
     * Creates the shortest path between two parks receiving the id of those
     * parks
     *
     * @param identificationParkOrigin - id of origin park
     * @param identificationParkDestination - id of destination park
     * @param lpaths - list of paths
     * @param report
     * @return distance of the path
     */
    public long createShortestPath(String identificationParkOrigin, String identificationParkDestination, Set<List<Path>> lpaths, List<String> report) {
        PlacesGraph pg = createGraph();
        int id_park_origin = this.parkRegistry.getParkDB(identificationParkOrigin).getId();
        int id_park_destination = this.parkRegistry.getParkDB(identificationParkDestination).getId();
        return createShortestPath(pg, id_park_origin, id_park_destination, lpaths, report);
    }

    /**
     *
     * @param pg
     * @param id_park_origin
     * @param id_park_destination
     * @param lpaths
     * @param report
     * @return
     */
    public long createShortestPath(PlacesGraph pg, int id_park_origin, int id_park_destination, Set<List<Path>> lpaths, List<String> report) {
        long dist = pg.getShortestPath(id_park_origin, id_park_destination, lpaths, report);
        return dist;
    }

    /**
     * Creates a graph with places on the vertices and paths on the edges
     *
     * @return the graph created
     */
    private PlacesGraph createGraph() {
        Set<Park> lp = parkRegistry.getAllParks();
        Set<POI> lpo = poiRegistry.getExistingPOI();
        PlacesGraph pg = new PlacesGraph(lp, lpo);
        Set<Path> lpa = this.pathRegistry.getAllPaths();
        pg.insertPaths(lpa);
        return pg;
    }

    /**
     * Creates the shortest path between two parks that goes by a certain number
     * of interest points
     *
     * @param identification_park_origin - origin's park id
     * @param identification_park_destination - destination's park id
     * @param elements - POI to be visited
     * @param report
     * @return
     */
    public long createShortestWithPOI(String identification_park_origin, String identification_park_destination, Place[] elements, List<String> report) {
        PlacesGraph pg = createGraph();
        int idParkOrigin = this.parkRegistry.getParkDB(identification_park_origin).getId();
        int idParkDestination = this.parkRegistry.getParkDB(identification_park_destination).getId();
        return createShortestWithPOI(pg, idParkOrigin, idParkDestination, elements, report);
    }

    /**
     * Creates the shortest path between two parks that goes by a certain number
     * of interest points
     *
     * @param latitudeO
     * @param longitudeO
     * @param latitudeD
     * @param longitudeD
     * @param elements - POI to be visited
     * @param report
     * @return
     */
    public long createShortestWithPOI(double latitudeO, double longitudeO, double latitudeD, double longitudeD, Place[] elements, List<String> report) {
        PlacesGraph pg = createGraph();
        int idParkOrigin = this.parkRegistry.getParkByCoordinates(latitudeO, longitudeO).getId();
        int idParkDestination = this.parkRegistry.getParkByCoordinates(latitudeD, longitudeD).getId();
        return createShortestWithPOI(pg, idParkOrigin, idParkDestination, elements, report);
    }

    private long createShortestWithPOI(PlacesGraph pg, int idParkOrigin, int idParkDestination, Place[] elements, List<String> report) {
        this.poiRegistry.getPOIS(elements);
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        return pg.getShortestPathWithPOI(idParkOrigin, idParkDestination, elements, pathSet, report);
    }

}
