/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lapr.project.model.Park;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehiclesHistoryLine;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author pedro
 */
public class VehicleRequestsDB extends DataHandler {

    /**
     * Constructor
     */
    public VehicleRequestsDB() {
        super();
    }

    /**
     * Constructor
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public VehicleRequestsDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @return
     */
    public Set<VehicleRequest> getVehicleRequests() {
        Set<VehicleRequest> vehicleRequestList = new HashSet<>();
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVehicleRequests() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int vehicleReqID = rSet.getInt(1);
                String userEmail = rSet.getString(2);
                int vehicleID = rSet.getInt(3);
                int placeOrigID = rSet.getInt(4);
                int placeDestID = rSet.getInt(5);
                String unlockTime = rSet.getString(6);
                String endTime = rSet.getString(7);
                vehicleRequestList.add(new VehicleRequest(vehicleReqID, userEmail, vehicleID, placeOrigID, placeDestID, unlockTime, endTime));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return vehicleRequestList;
    }

    /**
     *
     * @param vReq
     * @return
     */
    public boolean addRequest(VehicleRequest vReq) {
        return addRequest(vReq.getVehicleRequestID(), vReq.getUserEmail(), vReq.getVehicleID(), vReq.getOriginID(), vReq.getDestinationID(), Instant.now().toString(), "");
    }

    private boolean addRequest(int vehicleRequestID, String userEmail, int vehicleID, int placeOrigID, int placeDestID, String unlockTime, String endTime) {
        boolean flag = true;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addRequest(?,?,?,?,?,?,?) }")) {
            callStmt.setInt(1, vehicleRequestID);
            callStmt.setString(2, userEmail);
            callStmt.setInt(3, vehicleID);
            callStmt.setInt(4, (placeOrigID));
            callStmt.setInt(5, (placeDestID));
            callStmt.setTimestamp(6, Timestamp.valueOf(unlockTime));
            callStmt.setTimestamp(7, Timestamp.valueOf(endTime));
            callStmt.execute();
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBack();
            flag = false;
        }
        closeAll();
        return flag;
    }

    /**
     *
     * @param vehicleRequest
     * @return
     */
    public boolean updateUnlock(VehicleRequest vehicleRequest) {
        return updateUnlock(vehicleRequest.getVehicleRequestID());
    }

    private boolean updateUnlock(int vehicleRequestID) {
        boolean flag = true;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateUnlockTime(?) }")) {
            callStmt.setInt(1, vehicleRequestID);
            callStmt.setTimestamp(2, Timestamp.valueOf(Instant.now().toString()));
            callStmt.execute();
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBack();
            flag = false;
        }
        closeAll();
        return flag;
    }
    
    public VehicleRequest getVReqByEmailAndVeh(int vID, String email) {
        VehicleRequest vReq = null;
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVReqByEmailAndVeh(?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(1, vID);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {

                int vehicleReqID = rSet.getInt(1);
                String userEmail = rSet.getString(2);
                int vehicleID = rSet.getInt(3);
                int placeOrigID = rSet.getInt(4);
                int placeDestID = rSet.getInt(5);
                String unlockTime = rSet.getString(6);
                String endTime = rSet.getString(7);
                vReq = (new VehicleRequest(vehicleReqID, userEmail, vehicleID, placeOrigID, placeDestID, unlockTime, endTime));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return vReq;
    }

    public boolean updateFinish(VehicleRequest vehicleRequest, Park dest, String time) {
        return updateFinish(vehicleRequest.getVehicleRequestID(), dest.getId(), time);
    }

    public boolean updateFinish(int vehicleRequestID, int destId, String time) {
        boolean flag = true;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateFinish(?,?,?) }")) {
            callStmt.setInt(1, vehicleRequestID);
            callStmt.setInt(2, destId);
            callStmt.setTimestamp(3, Timestamp.valueOf(time));
            callStmt.execute();
            commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollBack();
            flag = false;
        }
        closeAll();
        return flag;
    }

    /**
     *
     * @param id
     * @return
     */
    public VehicleRequest getVehicleRequest(int id) {
        VehicleRequest vReq = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVehicleRequestByID(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(1, id);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            if (rSet.next()) {
                int vehicleReqID = rSet.getInt(1);
                String userEmail = rSet.getString(2);
                int vehicleID = rSet.getInt(3);
                int placeOrigID = rSet.getInt(4);
                int placeDestID = rSet.getInt(5);
                String unlockTime = rSet.getString(6);
                String endTime = rSet.getString(7);
                vReq = (new VehicleRequest(vehicleReqID, userEmail, vehicleID, placeOrigID, placeDestID, unlockTime, endTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return vReq;
    }

    /**
     *
     * @param userEmail
     * @return
     * @throws SQLException
     */
    public List<VehiclesHistoryLine> getVehiclesRequestHistory(String userEmail) throws SQLException {
        LinkedList<VehiclesHistoryLine> list = new LinkedList<>();
        openConnection();
        try( CallableStatement callStmt = getConnection().prepareCall("{ ? = call getVehicleRequestsHistory(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(1, userEmail);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                char lockedUnlocked = rSet.getString(1).charAt(0);
                String date = rSet.getString(2);
                String vehicleID = rSet.getString(3);
                String parkDesc = rSet.getString(4);
                VehiclesHistoryLine vhl = new VehiclesHistoryLine(lockedUnlocked, date, vehicleID, parkDesc);
                list.add(vhl);
            }

            commit();
        } catch (SQLException sQLException) {
            throw sQLException;
        }
        closeAll();
        return list;
    }

    /**
     *
     * @param date
     * @return
     */
    public Set<VehicleRequest> getActiveRequestsGivenTime(String date) {
        CallableStatement callStmt = null;
        Set<VehicleRequest> vReqs = new HashSet<>();
        openConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            java.util.Date dt = sdf.parse(date);
            java.sql.Timestamp time = new java.sql.Timestamp(dt.getTime());
            callStmt = getConnection().prepareCall("{ ? = call getActiveVehicleRequestByDate(?) }");
            callStmt.setTimestamp(2, time);
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);

            while (rSet.next()) {
                int vehicleReqID = rSet.getInt(1);
                String userEmail = rSet.getString(2);
                int vehicleID = rSet.getInt(3);
                int placeOrigID = rSet.getInt(4);
                int placeDestID = rSet.getInt(5);
                String unlockTime = rSet.getString(6);
                String endTime = rSet.getString(7);
                vReqs.add(new VehicleRequest(vehicleReqID, userEmail, vehicleID, placeOrigID, placeDestID, unlockTime, endTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Error parsing date for active requests");
        }
        closeAll();
        return vReqs;
    }
}
