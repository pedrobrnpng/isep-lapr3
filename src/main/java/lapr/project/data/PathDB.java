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
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Path;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class PathDB extends DataHandler {

    /**
     * Constructor
     */
    public PathDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public PathDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Gets all the existing paths in the database
     *
     * @return list of paths
     */
    public Set<Path> getExistingPaths() {
        Set<Path> PathList = new HashSet<>();
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPaths() }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int path_id = rSet.getInt(1);
                int id_place_origin = rSet.getInt(2);
                int id_place_end = rSet.getInt(3);
                BigDecimal kinectic_coeficient = rSet.getBigDecimal(4);
                BigDecimal wind_dir = rSet.getBigDecimal(5);
                BigDecimal wind_speed = rSet.getBigDecimal(6);
                PathList.add(new Path(path_id, id_place_origin, id_place_end, kinectic_coeficient.doubleValue(), wind_dir.doubleValue(), wind_speed.doubleValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PathList;
    }

    /**
     * Adds a path to the databsae
     *
     * @param path - path
     * @return boolean that indicates if the path was added
     */
    public boolean addPath(Path path) {
        return addPath(path.getId_place_origin(), path.getId_place_destination(), BigDecimal.valueOf(path.getKinectic_coeficient()), BigDecimal.valueOf(path.getWind_dir()), BigDecimal.valueOf(path.getWind_speed()));
    }

    /**
     * Adds a path to the database
     *
     * @param id_place_origin - origin place id
     * @param id_place_destination - destination place id
     * @param kinectic_coeficient - kinectic_coeficient
     * @param wind_dir - wind direction
     * @param wind_speed - wind speed
     * @return boolean that indicates if the path was added
     */
    private boolean addPath(int id_place_origin, int id_place_destination, BigDecimal kinectic_coeficient, BigDecimal wind_dir, BigDecimal wind_speed) {
        openConnection();
        try (CallableStatement callStmtAddPath = getConnection().prepareCall("{ call addPath(?,?,?,?,?,?,?) }")) {

            callStmtAddPath.setInt(1, id_place_origin);
            callStmtAddPath.setInt(2, id_place_destination);
            callStmtAddPath.setBigDecimal(3, kinectic_coeficient);
            callStmtAddPath.setBigDecimal(4, wind_dir);
            callStmtAddPath.setBigDecimal(5, wind_speed);
            callStmtAddPath.execute();
            super.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return false;
        }
    }

    /**
     * Gets a place by its coordinates
     *
     * @param latitudeO - place latitude
     * @param longitudeO - place longitude
     * @return place's id
     */
    public int getPlace(double latitudeO, double longitudeO) {
        int p = -1;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getPlaceByCoord(?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setBigDecimal(2, BigDecimal.valueOf(latitudeO));
            callStmt.setBigDecimal(3, BigDecimal.valueOf(longitudeO));
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                p = rSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * Adds several paths to the database
     *
     * @param pathList - list of paths
     * @return number of paths added
     */
    public int addAllPaths(Set<Path> pathList) {
        openConnection();
        try (CallableStatement callStmtAddAllPATH = getConnection().prepareCall("{ call addPath(?,?,?,?,?) }")) {
            for (Path p : pathList) {
                callStmtAddAllPATH.setInt(1, p.getId_place_origin());
                callStmtAddAllPATH.setInt(2, p.getId_place_destination());
                callStmtAddAllPATH.setBigDecimal(3, BigDecimal.valueOf(p.getKinectic_coeficient()));
                callStmtAddAllPATH.setBigDecimal(4, BigDecimal.valueOf(p.getWind_dir()));
                callStmtAddAllPATH.setBigDecimal(5, BigDecimal.valueOf(p.getWind_speed()));
                callStmtAddAllPATH.execute();
            }
            super.commit();
            closeAll();
            return pathList.size();
        } catch (SQLException ex) {
            Logger.getLogger(POIDB.class.getName()).log(Level.SEVERE, null, ex);
            super.rollBack();
            closeAll();
            return 0;
        }
    }

}
