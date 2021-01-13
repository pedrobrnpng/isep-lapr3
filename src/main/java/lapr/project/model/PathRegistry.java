/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.PathDB;
import static lapr.project.utils.maths.Mathematics.calculateGeoDistance;

/**
 *
 * @author pedro
 */
public class PathRegistry {

    /**
     * List of paths
     */
    private Set<Path> PathList;
    /**
     * Path DB
     */
    private PathDB pathDB;

    /**
     * ´Constructor
     */
    public PathRegistry() {
        this.PathList = new HashSet<>();
        this.pathDB = new PathDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     * ´Sets the path DB
     *
     * @param pdb - path DB
     */
    public void setPathDB(PathDB pdb) {
        this.pathDB = pdb;
    }

    /**
     * Consctrutor
     *
     * @param PathList - list of paths
     */
    public PathRegistry(HashSet<Path> PathList) {
        this.PathList = PathList;
    }

    /**
     * Gets a list of existing paths
     *
     * @return list of paths
     */
    public Set<Path> getPaths() {
        return this.PathList;
    }

    /**
     * Gets the path by id
     *
     * @param pathID - id of the path
     * @return
     */
    public Path getPath(int pathID) {
        for (Path t : PathList) {
            if (pathID == t.getPath_id()) {
                return t;
            }
        }
        return null;
    }

    /**
     * Creates a new path
     *
     * @param id_place_o - origin place id
     * @param id_place_d - destination place id
     * @param kinectic_coeficient - kinectic coeficient
     * @param wind_dir - wind direction
     * @param wind_speed - wind speed
     * @return new path created
     */
    public Path newPath(int id_place_o, int id_place_d, double kinectic_coeficient, double wind_dir, double wind_speed) {
        Path path = new Path(id_place_o, id_place_d, kinectic_coeficient, wind_dir, wind_speed);
        this.PathList.add(path);
        return path;
    }

    /**
     *
     * @param placeList
     * @param pathList
     * @param vehicleList
     * @param c
     * @param beginPark
     * @param endPark
     * @return
     */
    public List<RoutesPerVehicle> getEnergyRoutes(Set<Place> placeList, Set<Path> pathList, Set<Vehicle> vehicleList, Client c, Place beginPark, Place endPark) {
        List<RoutesPerVehicle> routes = new LinkedList<>();
        for (Vehicle v : vehicleList) {
            EnergyGraph eg = new EnergyGraph(placeList, pathList, v, c);
            LinkedList<Place> route = new LinkedList<>();
            RoutesPerVehicle rpv = new RoutesPerVehicle(eg.shortestPath(beginPark, endPark, route), v, route);
            routes.add(rpv);
        }
        return routes;
    }

    /**
     *
     * @param route
     * @return
     */
    public double getRouteDistance(LinkedList<Place> route) {
        double distance = 0;
        ListIterator<Place> i = route.listIterator();
        while (i.hasNext() && i.nextIndex() < route.size() - 1) {
            Place origin = i.next();
            Place destination = route.get(i.nextIndex());
            distance += calculateGeoDistance(origin.getLatitude(), destination.getLatitude(), origin.getLongitude(), destination.getLongitude());
        }
        return distance;
    }

    /**
     * HashCode
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.PathList);
        return hash;
    }

    /**
     * Compares two objects of Path Registry e returns if they are equal´
     *
     * @param obj
     * @return whether two objects of path registry are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PathRegistry other = (PathRegistry) obj;
        if (!Objects.equals(this.PathList, other.PathList)) {
            return false;
        }
        return true;
    }

    /**
     * Gets all paths in the database
     *
     * @return
     */
    public Set<Path> getAllPaths() {
        this.PathList = pathDB.getExistingPaths();
        return this.PathList;
    }

    /**
     * Gets a path by origin and destination place
     *
     * @param origin - origin id
     * @param destination - destination id
     * @return
     */
    public Path getPath(int origin, int destination) {
        for (Path p : this.PathList) {
            if (p.getId_place_origin() == origin && p.getId_place_destination() == destination) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a place by its latitude and longitude
     *
     * @param latitudeO - place latitude
     * @param longitudeO - place longitude
     * @return
     */
    public int getPlace(double latitudeO, double longitudeO) {
        return pathDB.getPlace(latitudeO, longitudeO);
    }

    /**
     * Adds a path to the database
     *
     * @param path - path
     * @return boolean wether the path was added or not
     */
    public boolean addPath(Path path) {
        return pathDB.addPath(path);
    }

    /**
     * Adds several paths to the database
     *
     * @return number of paths added
     */
    public int addAllPaths() {
        return this.pathDB.addAllPaths(this.PathList);
    }

    public void routesForTypeVehicle(String type, String vehicleSpecs, List<RoutesPerVehicle> routes) {
        Iterator<RoutesPerVehicle> i = routes.iterator();
        while (i.hasNext()) {
            RoutesPerVehicle rpv = i.next();
            Vehicle v = rpv.getV();
            if (type.equals("scooter")) {
                if (!(v instanceof Scooter)) {
                    i.remove();
                } else if (!((Scooter) v).getType().equals(vehicleSpecs)) {
                    i.remove();
                }
            }
            if (type.equals("bicycle")) {
                if (!(v instanceof Bicycle)) {
                    i.remove();
                } else if (!((Bicycle) v).getWheelSize().equals(vehicleSpecs)) {
                    i.remove();
                }
            }
        }
    }

    public void getLeastWeightRoutes(List<RoutesPerVehicle> routes) {
        double lowerWeight = Double.MAX_VALUE;
        RoutesPerVehicle lowestRoute = null;
        for (RoutesPerVehicle rpv : routes) {
            if (rpv.getWeigth() < lowerWeight) {
                lowerWeight = rpv.getWeigth();
                lowestRoute = rpv;
            }
        }
        if (lowestRoute != null) {
            Iterator<RoutesPerVehicle> i = routes.iterator();
            while (i.hasNext()) {
                if (i.next().getWeigth() != lowestRoute.getWeigth()) {
                    i.remove();
                }
            }
        }
    }

    public void orderRoutes(List<RoutesPerVehicle> routes) {
        RoutesPerVehicle aux;
        for (int i = 0; i < routes.size() - 1; i++) {
            for (int j = i + 1; j < routes.size(); j++) {
                if (routes.get(i).getRoutes().size() == routes.get(j).getRoutes().size()) {
                    if (calculateElevation(routes.get(i).getRoutes()) >= calculateElevation(routes.get(j).getRoutes())) {
                        aux = routes.get(i);
                        routes.set(i, routes.get(j));
                        routes.set(j, aux);
                    }
                }
                if (routes.get(i).getRoutes().size() > routes.get(j).getRoutes().size()) {
                    aux = routes.get(i);
                    routes.set(i, routes.get(j));
                    routes.set(j, aux);
                }
            }
        }
    }

    public double calculateElevation(LinkedList<Place> places) {
        double elevation = 0;
        for (int i = 0; i < places.size() - 1; i++) {
            elevation += Math.abs(places.get(i).getElevation() - places.get(i + 1).getElevation());
        }
        return elevation;
    }
}
