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
public class VehiclesHistory {
    
    private final String userEmail;
    private final LinkedList<VehiclesHistoryLine> list;

    /**
     *
     * @param userEmail
     * @param list
     */
    public VehiclesHistory(String userEmail, LinkedList<VehiclesHistoryLine> list) {
        this.userEmail = userEmail;
        this.list = list;
    }

    @Override
    public String toString() {
        String s = new String();
        for (VehiclesHistoryLine vhl : list){
            s += String.format("%s%n", vhl);
        }
        return "VEHICLE HISTORY of " + userEmail + ", by ride-sharing\n\n" + 
                (s.isEmpty() ? "You don't have any vehicles requests." : s);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.userEmail);
        hash = 11 * hash + Objects.hashCode(this.list);
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
        final VehiclesHistory other = (VehiclesHistory) obj;
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        return this.list.equals(other.list);
    }
}
