/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.ParkDB;

/**
 *
 * @author Utilizador
 */
public class ParkRegistry {

    /**
     * Set of park
     */
    private Set<Park> parkList;
    /**
     * Class ParkDB
     */
    private ParkDB pdb;

    /**
     * Constructor
     */
    public ParkRegistry() {
        this.parkList = new HashSet<>();
        this.pdb = new ParkDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     * Constructor
     *
     * @param parkList
     */
    public ParkRegistry(Set<Park> parkList) {
        this.parkList = parkList;
    }

    /**
     *
     * @param list
     */
    public void setParkList(Set<Park> list) {
        this.parkList = list;
    }

    /**
     *
     * @param pdb
     */
    public void setParkDB(ParkDB pdb) {
        this.pdb = pdb;
    }

    /**
     * Returns a list of parks
     *
     * @return list of parks
     */
    public Set<Park> getParks() {
        return this.parkList;
    }

    /**
     *
     * @param parkID
     * @return
     */
    public Park getPark(int parkID) {
        for (Park p : parkList) {
            if (parkID == p.getId()) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public Park getParkByCoordinates(double latitude, double longitude) {
        for (Park p : parkList) {
            if (latitude == p.getLatitude() && longitude == p.getLongitude()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Creates a new park
     *
     * @param identification
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param maxNumberBicycles
     * @param maxNumberScooter
     * @param inputVoltage
     * @param inputCurrent
     * @return
     */
    public Park newPark(String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        Park p = new Park(identification, latitude, longitude, elevation, description, maxNumberBicycles, maxNumberScooter, inputVoltage, inputCurrent);
        this.parkList.add(p);
        return p;
    }

    /**
     *
     * @return
     */
    public int addAllParks() {
        return this.pdb.AddAllParks(parkList);
    }

    /**
     * Updates information concerning a park
     *
     * @param p
     * @param identification
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param maxNumberBicycles
     * @param maxNumberScooter
     * @param inputVoltage
     * @param inputCurrent
     * @return
     */
    public boolean updateInformation(Park p, String identification, double latitude, double longitude, double elevation, String description, int maxNumberBicycles, int maxNumberScooter, double inputVoltage, double inputCurrent) {
        if (p != null) {
            p.setIdentification(identification);
            p.setLatitude(latitude);
            p.setLongitude(longitude);
            p.setElevation(elevation);
            p.setDescription(description);
            p.setMaxNumberBicycles(maxNumberBicycles);
            p.setMaxNumberScooter(maxNumberScooter);
            p.setInputCurrent(inputCurrent);
            p.setInputVoltage(inputVoltage);
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Set<Park> getAllParks() {
        this.parkList = pdb.getExistingParks();
        return pdb.getExistingParks();
    }

    /**
     *
     * @param parkID
     * @return
     */
    public boolean validatePark(String parkID) {
        for (Park p : parkList) {
            if (parkID.equalsIgnoreCase(p.getIdentification())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public boolean validateCoordinates(double latitude, double longitude) {
        for (Park p : parkList) {
            if (latitude == p.getLatitude() && longitude == p.getLongitude()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Defines Hash code
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.parkList);
        return hash;
    }

    /**
     * Method equals
     *
     * @param obj
     * @return boolean
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
        final ParkRegistry other = (ParkRegistry) obj;
        return Objects.equals(this.parkList, other.parkList);
    }

    /**
     * Gets a report of a park
     *
     * @param park - park
     * @param vl - list of vehicles that belong to the park
     * @param list
     * @return
     */
    public long getParkReport(Park park, Set<Scooter> vl, List<String> list) {
        int numScooter = 0;
        for (Scooter s : vl) {
            if (s.getActualBatteryCapacity() < 100) {
                numScooter++;
            }
        }
        for (Scooter v : vl) {
            list.add(v.getDescription() + ";" + v.getActualBatteryCapacity() + ";" + timeToCharge(park, v, numScooter) + "\n");
        }
        orderReport(list);
        return numScooter;
    }

    /**
     * Calculates the time for a scooter to charge
     *
     * @param p - park
     * @param v - scooter
     * @param numScooter - number of scooter in the park
     * @return
     */
    private int timeToCharge(Park p, Scooter v, int numScooter) {
        double chargePower = p.getParkChargePower();
        double chargePowerPlace = chargePower / numScooter;
        if (chargePowerPlace > 3000) {
            chargePowerPlace = 3000;
        }
        double remainBattery = ((v.getMaxBatteryCapacity() - (v.getMaxBatteryCapacity() * ((double) (v.getActualBatteryCapacity()) / 100))) * 1000 * 3600);
        int timeS = (int) (remainBattery / (chargePowerPlace * 0.90));
        return timeS;
    }

    /**
     *
     * @param parkIdentification
     * @return returns a park by its identification
     */
    public Park getParkDB(String parkIdentification) {
        return this.pdb.getPark(parkIdentification);
    }

    /**
     * Orders the park report
     * @param list 
     */
    private void orderReport(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String[] words = list.get(i).split(";");
                String[] words2 = list.get(j).split(";");
                if (Integer.parseInt(words[2].trim()) == Integer.parseInt(words2[2].trim())) {
                    if (words[0].compareTo(words2[0]) > 0) {
                        String aux = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, aux);
                    }
                }
                if (Integer.parseInt(words[2].trim()) < Integer.parseInt(words2[2].trim())) {
                    String aux = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, aux);
                }
            }
        }
    }

}
