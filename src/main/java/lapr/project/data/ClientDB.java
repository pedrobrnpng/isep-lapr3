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
import lapr.project.model.Client;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joaol
 */
public class ClientDB extends DataHandler {

    /**
     *
     */
    public ClientDB() {
        super();
    }

    /**
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public ClientDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @return
     * @throws InvalidInfoClientException
     */
    public HashSet<Client> getClients() throws InvalidInfoClientException {
        openConnection();
        HashSet<Client> clientList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getClients() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                String email = rSet.getString(1);
                String name = rSet.getString(2);
                int pass = rSet.getInt(3);
                int age = rSet.getInt(4);
                BigDecimal height = rSet.getBigDecimal(5);
                BigDecimal weight = rSet.getBigDecimal(6);
                char gender = rSet.getString(7).toCharArray()[0];
                BigDecimal avgCyclingSpeed = rSet.getBigDecimal(8);
                int points = rSet.getInt(9);
                clientList.add(new Client(name, email, pass, age, height.doubleValue(), weight.doubleValue(), gender, avgCyclingSpeed.doubleValue(), points));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    /**
     *
     * @param email
     * @return
     * @throws InvalidInfoClientException
     */
    public Client getClient(String email) throws InvalidInfoClientException {
        Client c = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getClient(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                String name = rSet.getString(2);
                int pass = rSet.getInt(3);
                int age = rSet.getInt(4);
                BigDecimal height = rSet.getBigDecimal(5);
                BigDecimal weight = rSet.getBigDecimal(6);
                char gender = rSet.getString(7).toCharArray()[0];
                BigDecimal avgCyclingSpeed = rSet.getBigDecimal(8);
                int points = rSet.getInt(9);
                c = new Client(name, email, pass, age, height.doubleValue(), weight.doubleValue(), gender, avgCyclingSpeed.doubleValue(), points);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     *
     * @param cli
     * @return
     */
    public boolean registerClient(Client cli) {
        return registerClient(cli.getName(), cli.getEmail(), cli.getPass(), cli.getAge(), BigDecimal.valueOf(cli.getHeight()), BigDecimal.valueOf(cli.getWeight()), cli.getGender());
    }

    private boolean registerClient(String name, String email, int pass, int age, BigDecimal height, BigDecimal weight, char gender) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?) }")) {

            callStmt.setString(1, email);
            callStmt.setString(2, name);
            callStmt.setInt(3, pass);
            callStmt.setInt(4, age);
            callStmt.setBigDecimal(5, height);
            callStmt.setBigDecimal(6, weight);
            callStmt.setString(7, String.valueOf(gender));
            callStmt.execute();
            super.commit();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return false;
        }
    }

    /**
     *
     * @param cli
     * @return
     */
    public boolean updateClient(Client cli) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateClient(?,?,?,?,?,?,?,?) }")) {

            callStmt.setString(1, cli.getEmail());
            callStmt.setString(2, cli.getName());
            callStmt.setInt(3, cli.getAge());
            callStmt.setBigDecimal(4, BigDecimal.valueOf(cli.getHeight()));
            callStmt.setBigDecimal(5, BigDecimal.valueOf(cli.getWeight()));
            callStmt.setString(6, String.valueOf(cli.getGender()));
            callStmt.setBigDecimal(7, BigDecimal.valueOf(cli.getAvgCyclingSpeed()));
            callStmt.setInt(8, cli.getPoints());

            callStmt.execute();
            super.commit();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return false;
        }
    }

    /**
     *
     * @param email
     * @param points
     * @return
     */
    public boolean updatePoints(String email, int points) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updatePoints(?,?) }")) {

            callStmt.setString(1, email);
            callStmt.setInt(2, points);

            callStmt.execute();
            super.commit();
            closeAll();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return false;
        }
    }

    /**
     *
     * @param clientList
     * @return
     */
    public int registerAllClients(Set<Client> clientList) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addClient(?,?,?,?,?,?,?) }")) {

            for (Client cli : clientList) {
                callStmt.setString(1, cli.getEmail());
                callStmt.setString(2, cli.getName());
                callStmt.setInt(3, cli.getPass());
                callStmt.setInt(4, cli.getAge());
                callStmt.setBigDecimal(5, BigDecimal.valueOf(cli.getHeight()));
                callStmt.setBigDecimal(6, BigDecimal.valueOf(cli.getWeight()));
                callStmt.setString(7, String.valueOf(cli.getGender()));
                callStmt.execute();
            }
            super.commit();
            closeAll();
            return clientList.size();
        } catch (SQLException e) {
            e.printStackTrace();
            super.rollBack();
            return 0;
        }
    }

}
