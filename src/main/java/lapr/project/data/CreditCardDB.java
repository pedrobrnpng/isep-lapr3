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
import java.text.ParseException;
import java.util.HashSet;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.utils.exceptions.CreditCardExpiredException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joaol
 */
public class CreditCardDB extends DataHandler {

    /**
     *
     */
    public CreditCardDB() {
        super();
    }

    /**
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public CreditCardDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public HashSet<CreditCard> getCreditCards() throws ParseException, CreditCardExpiredException {
        openConnection();
        HashSet<CreditCard> creditCardList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCreditCards() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int tin = rSet.getInt(2);
                String expDate = rSet.getString(3);
                BigDecimal credits = rSet.getBigDecimal(4);
                creditCardList.add(new CreditCard(tin, expDate, credits.doubleValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCardList;
    }

    /**
     *
     * @param cc
     * @param cli
     * @return
     */
    public boolean registerCreditCard(CreditCard cc, Client cli) {
        return registerCreditCard(cc.getTin(), cc.getExpDateString(), BigDecimal.valueOf(cc.getCredits()), cli.getEmail());
    }

    private boolean registerCreditCard(int tin, String expDate, BigDecimal credits, String email) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addCreditCard(?,?,?,?) }")) {

            callStmt.setInt(1, tin);
            callStmt.setString(2, email);
            callStmt.setString(3, expDate);
            callStmt.setBigDecimal(4, credits);

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
     *
     * @param tin
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard getCreditCard(int tin) throws ParseException, CreditCardExpiredException {
        CreditCard cc = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCreditCard(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, tin);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                String expDate = rSet.getString(3);
                BigDecimal credits = rSet.getBigDecimal(4);
                cc = new CreditCard(tin, expDate, credits.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    /**
     *
     * @param email
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard getCreditCard(String email) throws ParseException, CreditCardExpiredException {
        CreditCard cc = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getCreditCardEmail(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int tin = rSet.getInt(1);
                String expDate = rSet.getString(3);
                BigDecimal credits = rSet.getBigDecimal(4);
                cc = new CreditCard(tin, expDate, credits.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cc;
    }

    /**
     *
     * @param tin
     * @param expDate
     * @param credits
     * @return
     */
    public boolean updateCreditCard(int tin, String expDate, double credits) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateCreditCard(?,?,?) }")) {

            callStmt.setInt(1, tin);
            callStmt.setString(2, expDate);
            callStmt.setBigDecimal(3, BigDecimal.valueOf(credits));

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
     *
     * @param cc
     * @param cost
     * @return
     */
    public boolean deductCost(CreditCard cc, double cost) {
        cc.deductCredits(cost);
        return updateCreditCard(cc.getTin(), cc.getExpDateString(), cc.getCredits());
    }
}
