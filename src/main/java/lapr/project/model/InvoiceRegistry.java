/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.InvoiceDB;

/**
 *
 * @author joaol
 */
public class InvoiceRegistry {
    
    HashSet<Invoice> invoiceList;
    InvoiceDB idb;
    
    /**
     * Constructor
     */
    public InvoiceRegistry() {
        this.invoiceList=new HashSet<>();
        idb=new InvoiceDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }
    
    /**
     * Set Invoice list
     * @param invoiceList
     */
    public void setInvoiceList(HashSet<Invoice> invoiceList) {
        this.invoiceList=invoiceList;
    }
    
    /**
     * Set InvoiceDB
     * @param idb
     */
    public void setInvoiceDB(InvoiceDB idb) {
        this.idb=idb;
    }
    
    /**
     * Return Invoice List
     * @return
     */
    public HashSet<Invoice> getInvoiceList() {
        return invoiceList;
    }
    
    /**
     * Return InvoiceDB
     * @return
     */
    public InvoiceDB getInvoiceDB() {
        return idb;
    }
    
    /**
     * Creates Invoice and adds it to list
     * @param invId
     * @param date
     * @param period
     * @param usedPoints
     * @param totalCost
     * @return
     * @throws ParseException
     */
    public Invoice newInvoice(int invId, String date, int period, int usedPoints, double totalCost) throws ParseException {
        Invoice inv = new Invoice(invId, date, period, usedPoints, totalCost);
        invoiceList.add(inv);
        return inv;
    }
    
    /**
     * Registers Invoice in DB
     * @param invoice
     * @param email
     * @return 
     */
    public boolean addInvoice(Invoice invoice, String email) {
        return idb.addInvoice(invoice, email);
    }
    
    /**
     * Queries all Invoices belonging to Client through email
     * @param email
     * @return
     * @throws ParseException
     */
    public Set<Invoice> getInvoiceList(String email) throws ParseException {
        invoiceList=idb.getAllInvoices(email);
        return invoiceList;
    }
    
    /**
     * Updates Invoice information
     * @param inv
     * @return
     */
    public boolean updateInvoice(Invoice inv) {
        return idb.updateInvoice(inv);
    }
    
    /**
     * Queries most recent Invoice of Client trough email
     * @param email
     * @return
     * @throws ParseException
     */
    public Invoice getInvoice(String email) throws ParseException {
        return idb.getInvoice(email);
    }
    
    /**
     * Queries Invoice through Invoice ID
     * @param id
     * @return
     * @throws ParseException
     */
    public Invoice getInvoice(int id) throws ParseException {
        return idb.getInvoice(id);
    }
    
    /**
     * Queries Invoice belonging to Client from a specific month
     * @param month
     * @param email
     * @return
     * @throws ParseException
     */
    public Invoice getInvoiceMonthEmail(int month, String email) throws ParseException {
        return idb.getInvoiceForMonth(month, email);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.invoiceList);
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
        final InvoiceRegistry other = (InvoiceRegistry) obj;
        return this.invoiceList.equals(other.invoiceList);
    }
    
}
