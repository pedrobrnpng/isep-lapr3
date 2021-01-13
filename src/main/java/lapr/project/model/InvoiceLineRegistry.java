/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import lapr.project.data.InvoiceLineDB;
import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;

/**
 *
 * @author joaol
 */
public class InvoiceLineRegistry {
    
    private HashSet<InvoiceLine> invLlist;
    private InvoiceLineDB invLdb;
    
    /**
     * Constructor
     */
    public InvoiceLineRegistry() {
        this.invLlist=new HashSet<>();
        invLdb=new InvoiceLineDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }
    
    /**
     * Set Invoice Line list
     * @param invLlist
     */
    public void setInvLlist(HashSet<InvoiceLine> invLlist) {
        this.invLlist=invLlist;
    }
    
    /**
     * Set Invoice Line DB
     * @param invLdb
     */
    public void setInvoiceLineDB(InvoiceLineDB invLdb) {
        this.invLdb=invLdb;
    }
    
    /**
     * Return Invoice Line DB
     * @return
     */
    public InvoiceLineDB getInvoiceLineDB() {
        return this.invLdb;
    }
    
    /**
     * Return Invoice Line list
     * @return
     */
    public HashSet<InvoiceLine> getInvLlist() {
        return this.invLlist;
    }
    
    /**
     * Query all Invoice Lines of Invoice through Invoice ID
     * @param invId
     * @return
     * @throws InvalidInvoiceLineCostException
     */
    public HashSet<InvoiceLine> getAllInvLinesOfInv(int invId) throws InvalidInvoiceLineCostException {
        return invLdb.getAllInvoiceLinesOfInvoice(invId);
    }
    
    /**
     * Creates Invoice Line and adds it to list
     * @param invId
     * @param vrId
     * @param cost
     * @return
     * @throws InvalidInvoiceLineCostException
     */
    public InvoiceLine newInvoiceLine(int invId, int vrId, double cost) throws InvalidInvoiceLineCostException {
        InvoiceLine invL=new InvoiceLine(invId, vrId, cost);
        invLlist.add(invL);
        return invL;
    }
    
    /**
     * Registers Invoice Line in DB
     * @param invL
     * @return
     */
    public boolean addInvoiceLine(InvoiceLine invL) {
        return invLdb.addInvoiceLine(invL.getInvId(), invL.getVrId(), invL.getCost());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.invLlist);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InvoiceLineRegistry other = (InvoiceLineRegistry) obj;
        return this.invLlist.equals(other.invLlist);
    }
    
}
