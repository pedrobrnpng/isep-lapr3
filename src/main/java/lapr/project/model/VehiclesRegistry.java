/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import lapr.project.data.BicycleDB;
import lapr.project.data.ScooterDB;
import lapr.project.data.VehicleDB;

/**
 *
 * @author bruno
 */
public class VehiclesRegistry {

    private final HashSet<Vehicle> vehicles;
    private final HashSet<Bicycle> bicycles;
    private final HashSet<Scooter> scooters;
    private ScooterDB sdb;
    private BicycleDB bdb;
    private VehicleDB vdb;

    /**
     *
     */
    public VehiclesRegistry() {
        vehicles = new HashSet<>();
        bicycles = new HashSet<>();
        scooters = new HashSet<>();
        sdb = new ScooterDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        bdb = new BicycleDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        vdb = new VehicleDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     *
     * @param type
     * @param maxBatteryCapacity
     * @param actualBatteryCapacity
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @param motor
     * @throws SQLException
     */
    public void registerScooters() throws SQLException {
        for (Scooter s : scooters) {
            sdb.insertScooter(s);
        }
    }

    /**
     *
     * @param wheelSize
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @throws SQLException
     */
    public void registerBicycles() throws SQLException {
        for (Bicycle b : bicycles) {
            bdb.insertBicycle(b);
        }
    }

    public boolean addScooter(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) {
        Scooter s = new Scooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        return scooters.add(s);
    }

    public boolean addBicycle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) {
        Bicycle b = new Bicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        return bicycles.add(b);
    }

    /**
     *
     * @param type
     * @param maxBatteryCapacity
     * @param actualBatteryCapacity
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @param motor
     * @throws SQLException
     */
    public void changeVehicle(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) throws SQLException {
        sdb.updateScooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor);
        Scooter s = new Scooter();
        s.setID(id);
        vehicles.remove(s);
    }

    /**
     *
     * @param wheelSize
     * @param id
     * @param idPark
     * @param weight
     * @param aerodynamicCoefficient
     * @param frontalArea
     * @throws SQLException
     */
    public void changeVehicle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) throws SQLException {
        bdb.updateBicycle(wheelSize, id, idPark, weight, aerodynamicCoefficient, frontalArea);
        Bicycle b = new Bicycle();
        b.setID(id);
        vehicles.remove(b);
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void removeVehicle(int id) throws SQLException {
        vdb.removeVehicle(id);
        Vehicle v = new Scooter();
        v.setID(id);
        vehicles.remove(v);
    }

    /**
     *
     * @param idPark
     * @return
     * @throws SQLException
     */
    public Set<Vehicle> getVehicleInPark(int idPark) throws SQLException {
        Set<Vehicle> list = vdb.getAllVehicles();
        Iterator<Vehicle> i = list.iterator();
        while (i.hasNext()) {
            if (i.next().getIDPark() != idPark) {
                i.remove();
            }
        }
        return list;
    }

    /**
     *
     * @return @throws SQLException
     */
    public Set<Scooter> receiveScooterAutonomyReport() throws SQLException {
        return sdb.getAllAvailableScooters();
    }

    /**
     *
     * @return @throws SQLException
     */
    public Set<Vehicle> getAllVehicles() throws SQLException {
        return vdb.getAllVehicles();
    }

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
     * @param vdb
     */
    public void setVehicleDB(VehicleDB vdb) {
        this.vdb = vdb;
    }

    /**
     * returns the vehicle with the id received by parameter
     *
     * @param id
     * @return
     */
    public Vehicle getVehicle(int id) {
        vehicles.addAll(scooters);
        vehicles.addAll(bicycles);
        for (Vehicle v : vehicles) {
            if (id == v.getID()) {
                return v;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void updateAvailability(int id) throws SQLException {
        vdb.updateAvailability(id);
    }

    /**
     *
     * @param idPark
     * @return
     */
    public Set<Scooter> getVehiclesByPark(int idPark) {
        return this.vdb.getVehiclesByPark(idPark);
    }

    /**
     *
     * @throws SQLException
     */
    public void chargeVehicles() throws SQLException {
        vdb.chargeVehicles();
    }
}
