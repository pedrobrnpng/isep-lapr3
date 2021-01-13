/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class VehicleDB extends DataHandler {

    /**
     * Constructor
     */
    public VehicleDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public VehicleDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Receives the object vehicle and calls private method unlockVechile with
     * the id
     *
     * @param vehicle
     * @return the success of the operation
     */
    public boolean unlockVehicle(Vehicle vehicle) {
        return unlockVehicle(vehicle.getID());
    }

    /**
     * Receives the id of a vehicle and unlocks it, setting the park location to
     * 0 using the function unlockVehicle in the database
     *
     * @param vehicleID
     * @return the success of the operation
     */
    public boolean unlockVehicle(int vehicleID) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call unlockVehicle(?) }")) {
            callStmt.setInt(1, vehicleID);
            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Associates a park to a vehicle
     *
     * @param vehicle
     * @param park
     * @return success of the operation
     */
    public boolean parkVehicle(int vehicle, Park park) {
        return parkVehicle(vehicle, park.getId());
    }

    /**
     * Associates a park to a vehicle
     *
     * @param vehicleID
     * @param parkID
     * @return success of the operation
     */
    public boolean parkVehicle(int vehicleID, int parkID) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call parkVehicle(?,?,?) }")) {
            callStmt.setInt(1, vehicleID);
            callStmt.setInt(2, parkID);
            callStmt.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param id_vehicle
     * @throws SQLException
     */
    public void removeVehicle(int id_vehicle) throws SQLException {
        openConnection();
        CallableStatement callStmt = null;
        callStmt = getConnection().prepareCall("{ call removeVehicle(?) }");
        callStmt.setInt(1, id_vehicle);
        callStmt.execute();
        closeAll();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public HashSet<Vehicle> getAllVehicles() throws SQLException {
        HashSet<Vehicle> l = new HashSet<>();
        openConnection();
        CallableStatement callStmt = getConnection().prepareCall("{ ? = call allscooters() }");
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.execute();
        ResultSet rSet = (ResultSet) callStmt.getObject(1);
        while (rSet.next()) {
            int id = rSet.getInt(1);
            int idPark = rSet.getInt(2);
            char available = rSet.getString(3).charAt(0);
            int weigth = rSet.getInt(4);
            double aerodynamicCoefficient = rSet.getDouble(5);
            double frontalArea = rSet.getDouble(6);
            int maxBatteryCapacity = rSet.getInt(8);
            int actualBatteryCapacity = rSet.getInt(9);
            String type = rSet.getString(10);
            int motor = rSet.getInt(11);
            Scooter s = new Scooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weigth, aerodynamicCoefficient, frontalArea, motor);
            s.setAvailable(available);
            l.add(s);
        }
        callStmt = getConnection().prepareCall("{ ? = call allbicycles() }");
        callStmt.registerOutParameter(1, OracleTypes.CURSOR);
        callStmt.execute();
        rSet = (ResultSet) callStmt.getObject(1);
        while (rSet.next()) {
            int id = rSet.getInt(1);
            int idPark = rSet.getInt(2);
            char available = rSet.getString(3).charAt(0);
            int weigth = rSet.getInt(4);
            double aerodynamicCoefficient = rSet.getDouble(5);
            double frontalArea = rSet.getDouble(6);
            String wheelSize = rSet.getString(8);
            Bicycle b = new Bicycle(wheelSize, id, idPark, weigth, aerodynamicCoefficient, frontalArea);
            b.setAvailable(available);
            l.add(b);
        }
        closeAll();
        return l;
    }

    /**
     *
     * @param id_vehicle
     * @throws SQLException
     */
    public void updateAvailability(int id_vehicle) throws SQLException {
        try {
            CallableStatement callStmt = getConnection().prepareCall("{ call updateAvailability(?) }");
            callStmt.setInt(1, id_vehicle);
            callStmt.execute();
            commit();
        } catch (SQLException sQLException) {
            rollBack();
            throw sQLException;
        } finally {
            closeAll();
        }
    }

    /**
     *
     * @param idPark
     * @return
     */
    public Set<Scooter> getVehiclesByPark(int idPark) {
        Set<Scooter> vList = new HashSet<>();
        try (CallableStatement callStmtBicycles = getConnection().prepareCall("{ ? = call getScooters(?) }")) {
            callStmtBicycles.setInt(2, idPark);
            callStmtBicycles.registerOutParameter(1, OracleTypes.CURSOR);
            callStmtBicycles.execute();
            ResultSet rSetBicycle = (ResultSet) callStmtBicycles.getObject(1);
            while (rSetBicycle.next()) {
                int idVehicle = rSetBicycle.getInt(1);
                int idParkq = rSetBicycle.getInt(2);
                char available = (char) rSetBicycle.getInt(3);
                int weight = rSetBicycle.getInt(4);
                float aerodynamicCoefficient = rSetBicycle.getFloat(5);
                float frontalArea = rSetBicycle.getFloat(6);
                int maxBatteryLevel = rSetBicycle.getInt(8);
                int actualBatteryLevel = rSetBicycle.getInt(9);
                String type = rSetBicycle.getString(10);
                int motorPower = rSetBicycle.getInt(11);
                vList.add(new Scooter(type, maxBatteryLevel, actualBatteryLevel, idVehicle, idPark, weight, aerodynamicCoefficient, frontalArea, motorPower));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return vList;
    }
    
    /**
     *
     * @throws SQLException
     */
    public void chargeVehicles() throws SQLException{
        try (CallableStatement callStmt = getConnection().prepareCall("{ call charge_Vehicles }")) {
            callStmt.execute();
            commit();
        } catch (SQLException e){
            rollBack();
            throw e;
        } finally {
            closeAll();
        }
    }
}
