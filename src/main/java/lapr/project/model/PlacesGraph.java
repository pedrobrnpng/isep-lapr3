/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.GraphAlgorithms;
import static lapr.project.utils.maths.Physics.calculateEnergy;

/**
 *
 * @author Utilizador
 */
public class PlacesGraph {

    /**
     * graph of places
     */
    final Graph<Place, Path> placesGraph;

    /**
     * Constructor
     *
     * @param lp - list of parks
     * @param lpo - list of POI
     */
    public PlacesGraph(Set<Park> lp, Set<POI> lpo) {
        this.placesGraph = new Graph<>(true);
        for (Park p : lp) {
            this.placesGraph.insertVertex((Place) p);
        }
        for (POI p : lpo) {
            this.placesGraph.insertVertex((Place) p);
        }
    }

    /**
     * Adds a place to the graph
     *
     * @param p place
     */
    public void addPlace(Place p) {
        this.placesGraph.insertVertex(p);
    }

    /**
     * Adds a edge to the graph
     *
     * @param p1 - place 1
     * @param p2 - place 2
     * @param path - path
     * @param distance - distance
     */
    public void addConnectionBetweenPlaces(Place p1, Place p2, Path path, double distance) {
        this.placesGraph.insertEdge(p1, p2, path, distance);
    }

    /**
     * Inserts several paths to the graph
     *
     * @param lpa - list of paths
     */
    public void insertPaths(Set<Path> lpa) {
        Place o = null;
        Place d = null;
        for (Path p : lpa) {
            for (Place pl : this.placesGraph.vertices()) {
                if (pl.getId() == p.getId_place_origin()) {
                    o = pl;
                }
            }
            for (Place p2 : this.placesGraph.vertices()) {
                if (p2.getId() == p.getId_place_destination()) {
                    d = p2;
                }
            }
            if (o != null && d != null) {
                this.placesGraph.insertEdge(o, d, p, calculateDistance(o, d));
            }
        }
    }

    /**
     *
     * @param path
     * @return place
     */
    public Place getPlaceOrigin(Path path) {
        for (Place p : placesGraph.vertices()) {
            if (p.getId() == path.getId_place_origin()) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param path
     * @return place
     */
    public Place getPlaceDestination(Path path) {
        for (Place p : placesGraph.vertices()) {
            if (p.getId() == path.getId_place_destination()) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param p1 - origin place
     * @param p2 - destination place
     * @param pl - list of places the route goes by
     * @return shortest distance
     */
    public double pathTo(Place p1, Place p2, LinkedList<Place> pl) {
        return GraphAlgorithms.shortestPath(placesGraph, p1, p2, pl, Place.class);
    }

    /**
     * Calculates the distance between two places
     *
     * @param o - origin
     * @param d - destination
     * @return distance between origin and destination
     */
    private static double calculateDistance(Place o, Place d) {
        double lat1 = o.getLatitude();
        double lon1 = o.getLongitude();
        double lat2 = d.getLatitude();
        double lon2 = d.getLongitude();
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist * 1000);

    }

    /**
     * Gets the shortest path between two parks
     *
     * @param idParkOrigin - origin park
     * @param idParkDestination - destination park
     * @param setPath - list of routes
     * @param report 
     * @return shortest distance
     */
    public long getShortestPath(int idParkOrigin, int idParkDestination, Set<List<Path>> setPath, List<String> report) {
        Place origin = null;
        Place destination = null;
        for (Place p : placesGraph.vertices()) {
            if (p instanceof Park) {
                if (p.getId() == idParkOrigin) {
                    origin = p;
                }
                if (p.getId() == idParkDestination) {
                    destination = p;
                }
            }
        }
        if (origin == null || destination == null) {
            return -1;
        }
        List<LinkedList<Place>> lpath = new ArrayList<>();
        List<Long> dist = new ArrayList<>();
        lpath.add(new LinkedList<>());
        dist.add((long) GraphAlgorithms.shortestPath(placesGraph, origin, destination, lpath.get(0), Place.class));
        int j = 0;
        for (Place p : placesGraph.adjVertices(origin)) {
            for (Place p2 : placesGraph.adjVertices(p)) {
                j++;
                lpath.add(new LinkedList<>());
                lpath.get(j).add(origin);
                lpath.get(j).add(p);
                lpath.get(j).add(p2);
                dist.add((long) (calculateDistance(origin, p) + calculateDistance(p, p2)));
                for (Place p3 : placesGraph.adjVertices(p2)) {
                    if (p2 != destination) {
                        j++;
                        lpath.add(new LinkedList<>());
                        lpath.get(j).add(origin);
                        lpath.get(j).add(p);
                        lpath.get(j).add(p2);
                        lpath.get(j).add(p3);
                        dist.add((long) (calculateDistance(origin, p) + calculateDistance(p, p2) + calculateDistance(p2, p3)));
                    }
                }
            }
        }
        Iterator<LinkedList<Place>> it = lpath.iterator();
        int i = 0;
        while (it.hasNext()) {
            List<Place> lp = (List<Place>) it.next();
            if (lp.get(lp.size() - 1) != destination || dist.get(i) > dist.get(0)) {
                it.remove();
                dist.remove(i);
                i--;
            }
            i++;
        }
        List<List<Path>> pathList = new ArrayList<>();
        for (List<Place> lp : lpath) {
            LinkedList<Path> paths = new LinkedList<>();
            for (i = 0; i < lp.size() - 1; i++) {
                paths.add(this.placesGraph.getEdge(lp.get(i), lp.get(i + 1)).getElement());
            }
            pathList.add(paths);
        }
        double[] distances = new double[dist.size()];
        for (i = 0; i < distances.length; i++) {
            distances[i] = dist.get(i);
        }
        orderAllPaths(pathList, distances);
        setPath.addAll(pathList);
        getReport(setPath, (long) distances[0], report);
        return dist.get(0);
    }

    /**
     * Gets the shortest paths between two parks that goes through a certain
     * number of POI
     *
     * @param idParkOrigin - origin park id
     * @param idParkDestination - destination park id
     * @param elements - list of poi
     * @param pathSet
     * @param report
     * @return shortest distance
     */
    public long getShortestPathWithPOI(int idParkOrigin, int idParkDestination, Place[] elements, Set<List<Path>> pathSet, List<String> report) {
        Place origin = null;
        Place destination = null;
        for (Place p : placesGraph.vertices()) {
            if (p instanceof Park) {
                if (p.getId() == idParkOrigin) {
                    origin = p;
                }
                if (p.getId() == idParkDestination) {
                    destination = p;
                }
            }
        }
        if (origin == null || destination == null) {
            return -1;
        }
        List<List<Place>> list = new ArrayList<>();
        placesCombinations(elements.length, elements, list);
        List<List<Path>> pathLists = new ArrayList<>();
        for (List<Place> placeList : list) {
            placeList.add(0, origin);
            placeList.add(placeList.size(), destination);
        }
        for (int i = 0; i < list.size(); i++) {
            pathLists.add(new ArrayList<>());
        }
        int i = 0;
        double[] dist = new double[list.size()];
        for (List<Place> placeList : list) {
            dist[i] = getPathsGivenPlaces(placeList, pathLists.get(i));
            i++;
        }
        List<List<Path>> remove = new ArrayList<>();
        for (List<Path> pl : pathLists) {
            if (pl.isEmpty()) {
                remove.add(pl);
            }
        }
        pathLists.removeAll(remove);
        if (pathLists.isEmpty()) {
            report.add("");
            return -1;
        }
        orderAllPaths(pathLists, dist);
        i = 0;
        long min = Long.MAX_VALUE;
        for (List<Path> l : pathLists) {
            if ((long) dist[i] == min) {
                pathSet.add(l);
            }
            if (dist[i] < min) {
                pathSet.clear();
                min = (long) dist[i];
                pathSet.add(l);
            }
            i++;
        }
        getReport(pathSet, min, report);
        return min;
    }

    /**
     * Orders a list of routes
     *
     * @param pathLists - list of paths
     * @param dist - distance
     * @param asc - organization of the routes
     */
    private void orderAllPaths(List<List<Path>> pathLists, double[] dist) {
        for (int i = 0; i < pathLists.size() - 1; i++) {
            for (int j = i + 1; j < pathLists.size(); j++) {
                if (pathLists.get(i).size() == pathLists.get(j).size()) {
                    if (calculateElevation(pathLists.get(i)) >= calculateElevation(pathLists.get(j))) {
                        double aux = dist[i];
                        dist[i] = dist[j];
                        dist[j] = aux;
                        List<Path> laux = pathLists.get(i);
                        pathLists.set(i, pathLists.get(j));
                        pathLists.set(j, laux);
                    }
                }
                if (pathLists.get(i).size() > pathLists.get(j).size()) {
                    double aux = dist[i];
                    dist[i] = dist[j];
                    dist[j] = aux;
                    List<Path> laux = pathLists.get(i);
                    pathLists.set(i, pathLists.get(j));
                    pathLists.set(j, laux);
                }
            }
        }
    }

    /**
     * Gets the paths given a list of places in order
     *
     * @param placeList - list of places
     * @param pathList - list of paths
     * @return distance
     */
    private double getPathsGivenPlaces(List<Place> placeList, List<Path> pathList) {
        double dist = 0;
        LinkedList<Place> shortestPlaces = new LinkedList<>();
        for (int i = 0; i < placeList.size() - 1; i++) {
            shortestPlaces.clear();
            double distTemp = GraphAlgorithms.shortestPath(placesGraph, placeList.get(i), placeList.get(i + 1), shortestPlaces, Place.class);
            if (distTemp == 0) {
                pathList.clear();
                return -1;
            }
            dist += distTemp;
            for (int j = 0; j < shortestPlaces.size() - 1; j++) {
                Edge<Place, Path> path = this.placesGraph.getEdge(shortestPlaces.get(j), shortestPlaces.get(j + 1));
                pathList.add(path.getElement());
            }
        }
        return dist;
    }

    /**
     * Makes all possible combinations of a list of POI
     *
     * @param n - number of elements
     * @param elements - elements
     * @param list - list of routes
     */
    public static void placesCombinations(int n, Place[] elements, List<List<Place>> list) {
        if (n == 1) {
            list.add(new ArrayList<>(Arrays.asList(elements)));
        } else {
            for (int i = 0; i < n - 1; i++) {
                placesCombinations(n - 1, elements, list);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            placesCombinations(n - 1, elements, list);
        }
    }

    /**
     * Swaps two POI in an array
     *
     * @param input - array
     * @param a - POI 1
     * @param b - POI 2
     */
    private static void swap(Place[] input, int a, int b) {
        Place tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    /**
     * Calculates de total elevation of a path
     *
     * @param list - list of paths the route goes by
     * @return elevation difference
     */
    private double calculateElevation(List<Path> list) {
        double elevation = 0;
        for (Path p : list) {
            elevation += Math.abs(getPlaceDestination(p).getElevation() - getPlaceOrigin(p).getElevation());
        }
        return elevation;
    }

    /**
     *
     * @param pathSet Set of routes 
     * @param min shortest distance
     * @param report
     */
    public void getReport(Set<List<Path>> pathSet, long min, List<String> report) {
        int i = 1;
        double energy = 0;
        for (List<Path> l : pathSet) {
            for (Path path : l) {
                try {
                    energy += calculateEnergy(new Client("average", "average", 0, 0, 0, 0, 'M', 0, 0), path, getPlaceOrigin(path), getPlaceDestination(path), new Bicycle("12", 2, 0, 8, 0.5, 0.5));

                } catch (InvalidInfoClientException ex) {
                    Logger.getLogger(PlacesGraph.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            report.add(String.format("Path %03d%n", i));
            report.add("total_distance:" + min + "\n");
            report.add(String.format("total_energy:%.2f%n", energy));
            report.add("elevation:" + (long) (getPlaceDestination(l.get(l.size() - 1)).getElevation() - getPlaceOrigin(l.get(0)).getElevation()) + "\n");
            Place p = getPlaceOrigin(l.get(0));
            report.add(p.getLatitude() + ";" + p.getLongitude() + "\n");
            for (Path path : l) {
                p = getPlaceDestination(path);
                report.add(p.getLatitude() + ";" + p.getLongitude() + "\n");
            }
            i++;
        }
    }

}
