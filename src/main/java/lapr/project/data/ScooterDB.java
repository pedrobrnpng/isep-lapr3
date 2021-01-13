/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author bruno
 */
public class ScooterDB extends DataHandler {

    /**
     * Constructor
     */
    public ScooterDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public ScooterDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public Set<Scooter> getAllAvailableScooters() throws SQLException {
        HashSet<Scooter> vehicleList = new HashSet<>();
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getAllAvailableScooters() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int id = rSet.getInt(1);
                int idPark = rSet.getInt(2);
                int weight = rSet.getInt(4);
                double aerodynamicCoefficient = rSet.getDouble(5);
                double frontalArea = rSet.getDouble(6);
                String type = rSet.getString(7);
                int maxBatteryCapacity = rSet.getInt(8);
                int actualBatteryCapacity = rSet.getInt(9);
                int motor = rSet.getInt(10);
                vehicleList.add(new Scooter(type, maxBatteryCapacity, actualBatteryCapacity, id, idPark, weight, aerodynamicCoefficient, frontalArea, motor));
            }
        } catch (SQLException sQLException) {
            throw sQLException;
        } finally {
            closeAll();
        }
        return vehicleList;
    }

    /**
     *
     * @param s
     * @throws SQLException
     */
    public void insertScooter(Scooter s) throws SQLException {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addscooter(?,?,?,?,?,?,?,?,?,?) }")) {

            callStmt.setInt(1, s.getID());
            callStmt.setInt(2, s.getIDPark());
            callStmt.setString(3, String.valueOf(s.getAvailable()));
            callStmt.setDouble(4, s.getWeight());
            callStmt.setDouble(5, s.getAerodynamicCoefficient());
            callStmt.setDouble(6, s.getFrontalArea());
            callStmt.setInt(7, s.getMaxBatteryCapacity());
            callStmt.setInt(8, s.getActualBatteryCapacity());
            callStmt.setString(9, s.getType());
            callStmt.setInt(10, s.getMotor());

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
     * returns the vehicles of a given park with the procedure getVehiclesInPark
     * declared in the database
     *
     * @param park object of a park
     * @return list of vehciles
     */
    public HashSet<Vehicle> getScooters(Park park) {
        return getScooters(park.getId());
    }

    /**
     * returns the vehicles of a given park with the procedure getVehiclesInPark
     * declared in the database
     *
     * @param parkID id of the park
     * @return list of vehicles
     */
    private HashSet<Vehicle> getScooters(int parkID) {
        openConnection();
        HashSet<Vehicle> vehicleList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getScooters(?) }")) {
            callStmt.setInt(1, parkID);
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int idVehicle = rSet.getInt(1);
                int idPark = rSet.getInt(2);
                char available = (char) rSet.getInt(3);
                BigDecimal maxBatteryCapacity = rSet.getBigDecimal(4);
                BigDecimal actualBatteryCapacity = rSet.getBigDecimal(5);
                String type = rSet.getString(6);
                vehicleList.add(new Scooter());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return vehicleList;
    }

    /**
     * returns the vehicles of a given park with the procedure getVehiclesInPark
     * declared in the database
     *
     * @param idPark
     * @return list of vehicles
     */
    public Set<Scooter> getScootersByParkID(int idPark) {
        openConnection();
        HashSet<Scooter> scooterList = new HashSet<>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rSet = stmt.executeQuery("SELECT * FROM scooters scooter INNER JOIN vehicles vehicle ON scooter.id_vehicle = vehicle.id_vehicle WHERE vehicle.idPark = " + idPark);
            while (rSet.next()) {
                int id = rSet.getInt(1);
                float max_battery_level = rSet.getInt(2);
                float actual_battery_level = rSet.getInt(3);
                String scooter_type = rSet.getString(4);
                scooterList.add(new Scooter());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return scooterList;
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
    public void updateScooter(String type, int maxBatteryCapacity, int actualBatteryCapacity, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea, int motor) throws SQLException {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updatescooter(?,?,?,?,?,?,?,?,?) }")) {

            callStmt.setInt(1, id);
            if (idPark == 0) {
                callStmt.setNull(2, 4);
            } else {
                callStmt.setInt(2, idPark);
            }

            if (weight == 0) {
                callStmt.setNull(3, 4);
            } else {
                callStmt.setInt(3, weight);
            }

            if (aerodynamicCoefficient == 0) {
                callStmt.setNull(4, 6);
            } else {
                callStmt.setDouble(4, aerodynamicCoefficient);
            }

            if (frontalArea == 0) {
                callStmt.setNull(5, 6);
            } else {
                callStmt.setDouble(5, frontalArea);
            }

            if (maxBatteryCapacity == 0) {
                callStmt.setNull(6, 4);
            } else {
                callStmt.setInt(6, maxBatteryCapacity);
            }

            if (actualBatteryCapacity == 0) {
                callStmt.setNull(7, 4);
            } else {
                callStmt.setInt(7, actualBatteryCapacity);
            }

            if (type == null || type.isEmpty()) {
                callStmt.setNull(8, 12);
            } else {
                callStmt.setString(8, type);
            }

            if (motor == 0) {
                callStmt.setNull(9, 4);
            } else {
                callStmt.setInt(9, motor);
            }

            callStmt.execute();
            commit();
        } catch (SQLException sQLException) {
            rollBack();
            throw sQLException;
        } finally {
            closeAll();
        }
    }

}
