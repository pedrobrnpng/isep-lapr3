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
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import lapr.project.model.Bicycle;
import lapr.project.model.Park;
import lapr.project.model.Scooter;
import lapr.project.model.Vehicle;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author bruno
 */
public class BicycleDB extends DataHandler {
    
    /**
     * Constructor
     */
    public BicycleDB() {
        super();
    }
    
    /**
     * Constructor
     * 
     * @param jdbcUrl
     * @param username
     * @param password 
     */
    public BicycleDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @param b
     * @throws SQLException
     */
    public void insertBicycle(Bicycle b) throws SQLException {
        openConnection();
        CallableStatement callStmt = getConnection().prepareCall("{ call addbicycle(?,?,?,?,?,?,?,?,?) }");

        callStmt.setInt(1, b.getID());
        callStmt.setInt(2, b.getIDPark());
        callStmt.setString(3, String.valueOf(b.getAvailable()));
        callStmt.setDouble(4, b.getWeight());
        callStmt.setDouble(5, b.getAerodynamicCoefficient());
        callStmt.setDouble(6, b.getFrontalArea());
        callStmt.setString(7, b.getWheelSize());

        callStmt.execute();
        closeAll();
    }
    
    
    /**
     * returns the vehicles of a given park with the procedure getVehiclesInPark declared in the database
     * @param park object of a park
     * @return list of vehciles
     */
    public Set<Vehicle> getBicycles(Park park) {
        return getBicycles(park.getId()) ;
    }
    
    /**
     * returns the vehicles of a given park with the procedure getVehiclesInPark declared in the database
     * @param parkID
     * @return list of vehciles
     */
    public Set<Vehicle> getBicycles(int parkID) {
        openConnection();
        HashSet<Vehicle> vehicleList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getBicycle(?) }")){
            callStmt.setInt(1, parkID);
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int idVehicle = rSet.getInt(1);
                int idPark = rSet.getInt(2);
                char available = (char) rSet.getInt(3);
                BigDecimal maxBatteryLevel = rSet.getBigDecimal(4);
                BigDecimal actualBatteryLevel = rSet.getBigDecimal(5);
                String type = rSet.getString(6);
                vehicleList.add(new Scooter());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return vehicleList;
    }

    /**
     *
     * @param id_park
     * @return
     */
    public Set<Bicycle> getBicyclesByParkID(int id_park) {
        openConnection();
        HashSet<Bicycle> bicycleList= new HashSet<>();
        try {
          Statement stmt = getConnection().createStatement();
          ResultSet rSet = stmt.executeQuery("SELECT * FROM bicycles bicycle INNER JOIN vehicles vehicle ON bicycle.id_vehicle = vehicle.id_vehicle WHERE vehicle.id_park = " + id_park);
          while (rSet.next()) {
                int id = rSet.getInt(1);
                String wheel_size = rSet.getString(2);
                bicycleList.add(new Bicycle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeAll();
        return bicycleList;
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
    public void updateBicycle(String wheelSize, int id, int idPark, int weight, double aerodynamicCoefficient, double frontalArea) throws SQLException {
        openConnection();
        CallableStatement callStmt = getConnection().prepareCall("{ call updatebicycle(?,?,?,?,?,?) }");
        
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
        
        if (wheelSize == null || wheelSize.isEmpty()){
            callStmt.setNull(6, 12);
        } else{
            callStmt.setString(6, wheelSize);
        }

        callStmt.execute();
        closeAll();
    }

}
