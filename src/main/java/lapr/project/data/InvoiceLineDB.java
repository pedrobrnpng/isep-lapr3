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
import lapr.project.model.InvoiceLine;
import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author joaol
 */
public class InvoiceLineDB extends DataHandler {

    /**
     *
     */
    public InvoiceLineDB() {
        super();
    }

    /**
     *
     * @param jdbcUrl
     * @param username
     * @param password
     */
    public InvoiceLineDB(String jdbcUrl, String username, String password) {
        super(jdbcUrl, username, password);
    }

    /**
     *
     * @param invId
     * @return
     * @throws InvalidInvoiceLineCostException
     */
    public HashSet<InvoiceLine> getAllInvoiceLinesOfInvoice(int invId) throws InvalidInvoiceLineCostException {
        openConnection();
        HashSet<InvoiceLine> invLlist = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoiceLines(?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            while (rSet.next()) {
                int invIdp = rSet.getInt(1);
                int vrId = rSet.getInt(2);
                double cost = rSet.getBigDecimal(3).doubleValue();
                invLlist.add(new InvoiceLine(invIdp, vrId, cost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invLlist;
    }

    /**
     *
     * @param invID
     * @param vrId
     * @return
     * @throws InvalidInvoiceLineCostException
     */
    public InvoiceLine getInvoiceLine(int invID, int vrId) throws InvalidInvoiceLineCostException {
        InvoiceLine invL = null;
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call getInvoiceLine(?, ?) }")) {

            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, invID);
            callStmt.setInt(3, vrId);
            callStmt.execute();
            ResultSet rSet = (ResultSet) callStmt.getObject(1);
            if (rSet.next()) {
                int invIdp = rSet.getInt(1);
                int vrIdp = rSet.getInt(2);
                double cost = rSet.getBigDecimal(3).doubleValue();
                invL = new InvoiceLine(invIdp, vrIdp, cost);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invL;
    }

    /**
     *
     * @param invId
     * @param vrId
     * @param cost
     * @return
     */
    public boolean addInvoiceLine(int invId, int vrId, double cost) {
        openConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call addInvoiceLine(?,?,?) }")) {

            callStmt.setInt(1, invId);
            callStmt.setInt(2, vrId);
            callStmt.setBigDecimal(3, BigDecimal.valueOf(cost));
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

}
