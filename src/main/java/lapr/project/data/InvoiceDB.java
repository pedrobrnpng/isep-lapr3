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
import lapr.project.model.Invoice;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joaol
 */
public class InvoiceDB extends DataHandler {

    /**
     *
     */
    public InvoiceDB() {
        super();
    }

    /**
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public InvoiceDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @return
     * @throws ParseException
     */
    public HashSet<Invoice> getAllInvoices() throws ParseException {
        openConnection();
        HashSet<Invoice> invoiceList = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoices() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int invId = rSet.getInt(1);
                String date = rSet.getString(3);
                int period = rSet.getInt(4);
                int usedPoints = rSet.getInt(5);
                BigDecimal totalCost = rSet.getBigDecimal(6);
                invoiceList.add(new Invoice(invId, date, period, usedPoints, totalCost.doubleValue()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    /**
     *
     * @param invId
     * @return
     * @throws ParseException
     */
    public Invoice getInvoice(int invId) throws ParseException {
        Invoice inv = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoice(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int invIdp = rSet.getInt(1);
                String date = rSet.getString(3);
                int period = rSet.getInt(4);
                int usedPoints = rSet.getInt(5);
                BigDecimal totalCost = rSet.getBigDecimal(6);
                inv = new Invoice(invIdp, date, period, usedPoints, totalCost.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }

    /**
     *
     * @param inv
     * @return
     */
    public boolean updateInvoice(Invoice inv) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call updateInvoice(?,?,?,?,?) }")) {

            callStmt.setInt(1, inv.getInvId());
            callStmt.setString(2, inv.getDateString());
            callStmt.setInt(3, inv.getPeriod().getValue());
            callStmt.setInt(4, inv.getUsedPoints());
            callStmt.setBigDecimal(5, BigDecimal.valueOf(inv.getTotalCost()));

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
    
    public boolean addInvoice(Invoice inv, String email) {
        return addInvoice(inv.getInvId(), inv.getPeriod().getValue(), inv.getDateString(), inv.getUsedPoints(), inv.getTotalCost(), email);
    }
    
    public boolean addInvoice(int id, int period, String date, int usedPoints, double totalCost, String email) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addInvoice(?,?,?,?,?,?) }")) {
            callStmt.setInt(1, id);
            callStmt.setString(2, date);
            callStmt.setInt(3, period);
            callStmt.setInt(4, usedPoints);
            callStmt.setBigDecimal(5, BigDecimal.valueOf(totalCost));
            callStmt.setString(6, email);
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
     * @return
     * @throws ParseException
     */
    public Invoice getInvoice(String email) throws ParseException {
        Invoice inv = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoiceEmail(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int invIdp = rSet.getInt(1);
                String date = rSet.getString(3);
                int period = rSet.getInt(4);
                int usedPoints = rSet.getInt(5);
                BigDecimal totalCost = rSet.getBigDecimal(6);
                inv = new Invoice(invIdp, date, period, usedPoints, totalCost.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }

    /**
     *
     * @param email
     * @return
     * @throws ParseException
     */
    public HashSet<Invoice> getAllInvoices(String email) throws ParseException {
        System.out.println(email);
        HashSet<Invoice> invList = new HashSet<>();
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getAllInvoicesEmail(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setString(2, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int invId = rSet.getInt(1);
                String date = rSet.getString(3);
                int period = rSet.getInt(4);
                int usedPoints = rSet.getInt(5);
                BigDecimal totalCost = rSet.getBigDecimal(6);
                Invoice inv=new Invoice(invId, date, period, usedPoints, totalCost.doubleValue());
                System.out.println(inv);
                invList.add(inv);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invList;
    }

    /**
     *
     * @param month
     * @param email
     * @return
     * @throws ParseException
     */
    public Invoice getInvoiceForMonth(int month, String email) throws ParseException {
        Invoice inv = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoiceMonthEmail(?,?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, month);
            callStmt.setString(3, email);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int invIdp = rSet.getInt(1);
                String date = rSet.getString(3);
                int period = rSet.getInt(4);
                int usedPoints = rSet.getInt(5);
                BigDecimal totalCost = rSet.getBigDecimal(6);
                inv = new Invoice(invIdp, date, period, usedPoints, totalCost.doubleValue());
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inv;
    }
}
