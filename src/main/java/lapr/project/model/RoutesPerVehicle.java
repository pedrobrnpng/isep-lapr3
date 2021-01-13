/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author bruno
 */
public class RoutesPerVehicle {
    
    private double weigth;
    private Vehicle v;
    private LinkedList<Place> routes;
    

    public RoutesPerVehicle(double weigth, Vehicle v, LinkedList<Place> routes) {
        this.weigth = weigth;
        this.v = v;
        this.routes = routes;
    }

    public LinkedList<Place> getRoutes() {
        return routes;
    }

    public Vehicle getV() {
        return v;
    }

    public double getWeigth() {
        return weigth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.v);
        return hash;
    }

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
        final RoutesPerVehicle other = (RoutesPerVehicle) obj;
        if (!Objects.equals(this.v, other.v)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
