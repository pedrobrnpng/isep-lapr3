/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.utils.graphbase.Edge;
import lapr.project.utils.graphbase.Graph;
import lapr.project.utils.graphbase.GraphAlgorithms;
import static lapr.project.utils.maths.Physics.*;

/**
 *
 * @author bruno
 */
public class EnergyGraph {

    private final Graph<Place, Path> graph;

    /**
     *
     * @param placeList
     * @param pathList
     * @param v
     * @param c
     */
    public EnergyGraph(Set<Place> placeList, Set<Path> pathList, Vehicle v, Client c) {
        graph = new Graph<>(true);
        insertPalces(placeList);
        insertPaths(placeList, pathList, v, c);
    }

    private void insertPalces(Set<Place> placeList) {
        for (Place p : placeList) {
            graph.insertVertex(p);
        }
    }

    private void insertPaths(Set<Place> placeList, Set<Path> pathList, Vehicle v, Client c) {
        for (Path path : pathList) {
            Place origin = getPlaceByID(placeList, path.getId_place_origin());
            Place end = getPlaceByID(placeList, path.getId_place_destination());

            double weight = calculateEnergy(c, path, origin, end, v);

            graph.insertEdge(origin, end, path, weight);
        }
    }

    private Place getPlaceByID(Set<Place> placeList, int id) {
        for (Place p : placeList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param beginPark
     * @param endPark
     * @return
     */
    public double shortestPath(Place beginPark, Place endPark, LinkedList<Place> route) {
        return GraphAlgorithms.shortestPath(graph, beginPark, endPark, route, Place.class);
    }

    /**
     * Gets the shortest paths between two parks that goes through a certain
     * number of POI
     *
     * @param idParkOrigin - origin park id
     * @param idParkDestination - destination park id
     * @param elements - list of poi
     * @param asc - organization of the routes
     * @return
     */
    public Set<List<Path>> getShortestPathWithPOI(int idParkOrigin, int idParkDestination, Place[] elements, boolean asc) {

        Place destination = null;
        Place origin = null;

        for (Place p : graph.vertices()) {
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
            return null;
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
//
//        List<List<Path>> remove = new ArrayList<>();
//        for (List<Path> pl : pathLists) {
//            if (pl.isEmpty()) {
//                remove.add(pl);
//            }
//        }
//
//        pathLists.removeAll(remove);
        orderAllPaths(pathLists, dist, asc);
        Set<List<Path>> pathSet = new LinkedHashSet<>();
        if (pathLists.size() > 0) {
            pathSet.add(pathLists.get(0));
        }
        return pathSet;

    }

    /**
     * Orders a list of routes
     *
     * @param pathLists - list of paths
     * @param distance - distance
     * @param asc - organization of the routes
     */
    private void orderAllPaths(List<List<Path>> pathLists, double[] distance, boolean asc) {

        for (int i = 0; i < distance.length - 1; i++) {
            for (int j = i + 1; j < distance.length; j++) {
                if ((distance[i] > distance[j] && asc) || (distance[i] < distance[j] && !asc)) {
                    double aux = distance[i];
                    distance[i] = distance[j];
                    distance[j] = aux;
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

        double distance = 0;
        LinkedList<Place> shortestPlaces = new LinkedList<>();
        for (int i = 0; i < placeList.size() - 1; i++) {

            shortestPlaces.clear();
            double distTemp = GraphAlgorithms.shortestPath(graph, placeList.get(i), placeList.get(i + 1), shortestPlaces, Place.class);
            if (distTemp == 0) {
                pathList.clear();
                return -1;
            }

            distance += distTemp;
            for (int j = 0; j < shortestPlaces.size() - 1; j++) {
                Edge<Place, Path> path = this.graph.getEdge(shortestPlaces.get(j), shortestPlaces.get(j + 1));
                pathList.add(path.getElement());
            }
        }
        return distance;
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
     * @param list - array
     * @param a - POI 1
     * @param b - POI 2
     */
    private static void swap(Place[] list, int a, int b) {
        Place tmp = list[a];
        list[a] = list[b];
        list[b] = tmp;
    }

}
