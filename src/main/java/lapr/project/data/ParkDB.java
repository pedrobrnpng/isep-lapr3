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
import lapr.project.model.Park;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class ParkDB extends DataHandler {

    /**
     * Constructor
     */
    public ParkDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public ParkDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Returns the existing parks in the data base
     *
     * @return set of parks
     */
    public Set<Park> getExistingParks() {
        openConnection();
        HashSet<Park> parkList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getParks() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int id = rSet.getInt(1);
                String identification = rSet.getString(2);
                BigDecimal latitude = rSet.getBigDecimal(10);
                BigDecimal longitude = rSet.getBigDecimal(11);
                BigDecimal elevation = rSet.getBigDecimal(12);
                String description = rSet.getString(3);
                int maxNumberBicycles = rSet.getInt(4);
                int maxNumberScooter = rSet.getInt(5);
                BigDecimal inputVoltage = rSet.getBigDecimal(6);
                BigDecimal inputCurrent = rSet.getBigDecimal(7);
                parkList.add(new Park(id, identification, latitude.doubleValue(), longitude.doubleValue(), elevation.doubleValue(), description, maxNumberBicycles, maxNumberScooter, inputVoltage.doubleValue(), inputCurrent.doubleValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return parkList;
    }

    /**
     * Returns the existing parks in the data base
     *
     * @param latitude_park
     * @param longitude_park
     * @return set of parks
     */
    public Park getParkByCoordinates(double latitude_park, double longitude_park) {
        openConnection();
        Park park = null;
        try (CallableStatement callStmt1 = getConnection().prepareCall("{ ? = call getParkByCoordinates(?,?) }")) {

            callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt1.execute();
            ResultSet rSet1 = (ResultSet) callStmt1.getObject(1);
            if (rSet1.next()) {
                int id = rSet1.getInt(1);
                String identification = rSet1.getString(2);
                BigDecimal latitude = rSet1.getBigDecimal(10);
                BigDecimal longitude = rSet1.getBigDecimal(11);
                BigDecimal elevation = rSet1.getBigDecimal(12);
                String description = rSet1.getString(3);
                int maxNumberBicycles = rSet1.getInt(4);
                int maxNumberScooter = rSet1.getInt(5);
                BigDecimal inputVoltage = rSet1.getBigDecimal(6);
                BigDecimal inputCurrent = rSet1.getBigDecimal(7);
                park = new Park(id, identification, latitude.doubleValue(), longitude.doubleValue(), elevation.doubleValue(), description, maxNumberBicycles, maxNumberScooter, inputVoltage.doubleValue(), inputCurrent.doubleValue());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return park;
    }

    /**
     * Adds a park to the database
     *
     * @param park
     * @return boolean
     */
    public boolean addPark(Park park) {
        return addPark(park.getIdentification(), BigDecimal.valueOf(park.getLatitude()), BigDecimal.valueOf(park.getLongitude()), BigDecimal.valueOf(park.getElevation()), park.getDescription(), park.getMaxNumberBicycles(), park.getMaxNumberScooter(), BigDecimal.valueOf(park.getInputVoltage()), BigDecimal.valueOf(park.getInputCurrent()));
    }

    /**
     * Adds a park to the database
     *
     * @param identification
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param maxNumerBicycles
     * @param maxNumberScooter
     * @param inputVoltage
     * @param inputCurrent
     * @return boolen
     */
    private boolean addPark(String identification, BigDecimal latitude, BigDecimal longitude, BigDecimal elevation, String description, int maxNumerBicycles, int maxNumberScooter, BigDecimal inputVoltage, BigDecimal inputCurrent) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addPark(?,?,?,?,?,?,?,?,?) }")) {
            callStmt.setString(1, identification);

            callStmt.setBigDecimal(2, latitude);
            callStmt.setBigDecimal(3, longitude);
            callStmt.setBigDecimal(4, elevation);
            callStmt.setString(5, description);
            callStmt.setInt(6, maxNumerBicycles);
            callStmt.setInt(7, maxNumberScooter);
            callStmt.setBigDecimal(8, inputVoltage);
            callStmt.setBigDecimal(9, inputCurrent);
            callStmt.execute();
            closeAll();
            super.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return false;
        }
    }

    /**
     * Gets a park from the database receiving the identification as parameter
     *
     * @param id
     * @return park
     */
    public Park getPark(int id) {
        Park park = null;
        openConnection();
        try (CallableStatement callStmt1 = getConnection().prepareCall("{ ? = call getPark(?) }")) {

            callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt1.setInt(2, id);
            callStmt1.execute();
            ResultSet rSet1 = (ResultSet) callStmt1.getObject(1);
            if (rSet1.next()) {
                int idq = rSet1.getInt(1);
                String identificationq = rSet1.getString(2);
                BigDecimal latitude = rSet1.getBigDecimal(10);
                BigDecimal longitude = rSet1.getBigDecimal(11);
                BigDecimal elevation = rSet1.getBigDecimal(12);
                String description = rSet1.getString(3);
                int maxNumberBicycles = rSet1.getInt(4);
                int maxNumberScooter = rSet1.getInt(5);
                BigDecimal inputVoltage = rSet1.getBigDecimal(6);
                BigDecimal inputCurrent = rSet1.getBigDecimal(7);
                park = new Park(idq, identificationq, latitude.doubleValue(), longitude.doubleValue(), elevation.doubleValue(), description, maxNumberBicycles, maxNumberScooter, inputVoltage.doubleValue(), inputCurrent.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return park;
    }

    /**
     * Updates information related to a park in the database
     *
     * @param identification
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @param maxNumerBicycles
     * @param maxNumberScooter
     * @param inputVoltage
     * @param inputCurrent
     * @return
     */
    private boolean updatePark(int id, String identification, BigDecimal latitude, BigDecimal longitude, BigDecimal elevation, String description, int maxNumerBicycles, int maxNumberScooter, BigDecimal inputVoltage, BigDecimal inputCurrent) {
        openConnection();
        try (CallableStatement callStmt2 = getConnection().prepareCall("{ call updatePark(?,?,?,?,?,?,?,?,?,?) }")) {
            callStmt2.setInt(1, id);
            callStmt2.setString(2, identification);
            callStmt2.setBigDecimal(3, latitude);
            callStmt2.setBigDecimal(4, longitude);
            callStmt2.setBigDecimal(5, elevation);
            callStmt2.setString(6, description);
            callStmt2.setInt(7, maxNumerBicycles);
            callStmt2.setInt(8, maxNumberScooter);
            callStmt2.setBigDecimal(9, inputVoltage);
            callStmt2.setBigDecimal(10, inputCurrent);
            callStmt2.execute();
            closeAll();
            super.commit();
            return true;
        } catch (SQLException e) {
            super.rollBack();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates information relative to a park in the database
     *
     * @param park
     * @return boolean
     */
    public boolean updatePark(Park park) {
        return updatePark(park.getId(), park.getIdentification(), BigDecimal.valueOf(park.getLatitude()), BigDecimal.valueOf(park.getLongitude()), BigDecimal.valueOf(park.getElevation()), park.getDescription(), park.getMaxNumberBicycles(), park.getMaxNumberScooter(), BigDecimal.valueOf(park.getInputVoltage()), BigDecimal.valueOf(park.getInputCurrent()));
    }

    /**
     * Deactivates a park in the database
     *
     * @param park
     * @return park
     */
    public boolean deactivatePark(Park park) {
        return deactivatePark(park.getId());
    }

    /**
     * Deactivates a park in the databse
     *
     * @param identification
     * @return boolean
     */
    private boolean deactivatePark(int identification) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call deactivatePark(?) }")) {
            callStmt.setInt(1, identification);
            callStmt.execute();
            super.commit();
            closeAll();
            return true;
        } catch (SQLException e) {
            super.rollBack();
            closeAll();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds several parks to the database
     *
     * @param parkList - list of parks
     * @return
     */
    public int AddAllParks(Set<Park> parkList) {
        openConnection();
        try (CallableStatement callStmtAddAllParks = getConnection().prepareCall("{ call addPark(?,?,?,?,?,?,?,?,?) }")) {
            for (Park p : parkList) {
                callStmtAddAllParks.setString(1, p.getIdentification());
                callStmtAddAllParks.setBigDecimal(2, BigDecimal.valueOf(p.getLatitude()));
                callStmtAddAllParks.setBigDecimal(3, BigDecimal.valueOf(p.getLongitude()));
                callStmtAddAllParks.setBigDecimal(4, BigDecimal.valueOf(p.getElevation()));
                callStmtAddAllParks.setString(5, p.getDescription());
                callStmtAddAllParks.setInt(6, p.getMaxNumberBicycles());
                callStmtAddAllParks.setInt(7, p.getMaxNumberScooter());
                callStmtAddAllParks.setBigDecimal(8, BigDecimal.valueOf(p.getInputVoltage()));
                callStmtAddAllParks.setBigDecimal(9, BigDecimal.valueOf(p.getInputCurrent()));
                callStmtAddAllParks.execute();
            }
            super.commit();
            closeAll();
            return parkList.size();
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return 0;
        }
    }

    /**
     * Gets a park by its identification
     *
     * @param identification - identification of the park
     * @return
     */
    public Park getPark(String identification) {
        Park park = null;
        openConnection();
        try (CallableStatement callStmt1 = getConnection().prepareCall("{ ? = call getParkByIdentification(?) }")) {

            callStmt1.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt1.setString(2, identification);
            callStmt1.execute();
            ResultSet rSet1 = (ResultSet) callStmt1.getObject(1);
            if (rSet1.next()) {
                int idq = rSet1.getInt(1);
                String identificationq = rSet1.getString(2);
                BigDecimal latitude = rSet1.getBigDecimal(10);
                BigDecimal longitude = rSet1.getBigDecimal(11);
                BigDecimal elevation = rSet1.getBigDecimal(12);
                String description = rSet1.getString(3);
                int maxNumberBicycles = rSet1.getInt(4);
                int maxNumberScooter = rSet1.getInt(5);
                BigDecimal inputVoltage = rSet1.getBigDecimal(6);
                BigDecimal inputCurrent = rSet1.getBigDecimal(7);
                park = new Park(idq, identificationq, latitude.doubleValue(), longitude.doubleValue(), elevation.doubleValue(), description, maxNumberBicycles, maxNumberScooter, inputVoltage.doubleValue(), inputCurrent.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return park;
    }
}
