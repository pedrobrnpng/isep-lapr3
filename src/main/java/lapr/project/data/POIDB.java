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
import lapr.project.model.POI;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Utilizador
 */
public class POIDB extends DataHandler {

    /**
     * Constructor
     */
    public POIDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public POIDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     * Adds a POI to the database
     *
     * @param poi - point of interest
     * @return
     */
    public boolean addPOIDB(POI poi) {
        return addPOIDB(new BigDecimal(poi.getLatitude()), new BigDecimal(poi.getLongitude()), new BigDecimal(poi.getElevation()), poi.getDescription());
    }

    /**
     * Adds a poi to the database
     *
     * @param latitude
     * @param longitude
     * @param elevation
     * @param description
     * @return boolean that indicates if the poi was added
     */
    private boolean addPOIDB(BigDecimal latitude, BigDecimal longitude, BigDecimal elevation, String description) {
        openConnection();
        try (CallableStatement callStmtAddPOI = getConnection().prepareCall("{ call addPOI(?,?,?,?) }")) {

            callStmtAddPOI.setBigDecimal(1, latitude);
            callStmtAddPOI.setBigDecimal(2, longitude);
            callStmtAddPOI.setBigDecimal(3, elevation);
            callStmtAddPOI.setString(4, description);
            callStmtAddPOI.execute();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets all existing POI from the database
     *
     * @return existing POI
     */
    public Set<POI> getExistingPOI() {
        openConnection();
        HashSet<POI> poiList = new HashSet<>();
        try (CallableStatement callStmtGetPOIS = getConnection().prepareCall("{ ? = call getPOIS() }")) {
            callStmtGetPOIS.registerOutParameter(1, OracleTypes.CURSOR);
            callStmtGetPOIS.execute();
            ResultSet rSetPois = (ResultSet) callStmtGetPOIS.getObject(1);
            while (rSetPois.next()) {
                int id = rSetPois.getInt(1);
                String description = rSetPois.getString(2);
                BigDecimal latitude = rSetPois.getBigDecimal(4);
                BigDecimal longitude = rSetPois.getBigDecimal(5);
                BigDecimal elevation = rSetPois.getBigDecimal(6);
                poiList.add(new POI(id, latitude.doubleValue(), longitude.doubleValue(), elevation.doubleValue(), description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return poiList;
    }

    /**
     * Adds several POI to the database
     *
     * @param poilist - list of POI
     * @return
     */
    public int addPOIDB(Set<POI> poilist) {
        openConnection();
        try (CallableStatement callStmtAddAllPOI = getConnection().prepareCall("{ call addPOI(?,?,?,?) }")) {
            for (POI p : poilist) {
                callStmtAddAllPOI.setBigDecimal(1, BigDecimal.valueOf(p.getLatitude()));
                callStmtAddAllPOI.setBigDecimal(2, BigDecimal.valueOf(p.getLongitude()));
                callStmtAddAllPOI.setBigDecimal(3, BigDecimal.valueOf(p.getElevation()));
                callStmtAddAllPOI.setString(4, p.getDescription());
                callStmtAddAllPOI.execute();
            }
            super.commit();
            closeAll();
            return poilist.size();
        } catch (SQLException ex) {
            Logger.getLogger(POIDB.class.getName()).log(Level.SEVERE, null, ex);
            super.rollBack();
            return 0;
        }
    }
}
