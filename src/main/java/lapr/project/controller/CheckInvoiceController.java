/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceRegistry;

/**
 *
 * @author joaol
 */
public class CheckInvoiceController {

    Invoice inv;
    InvoiceRegistry ir;

    /**
     * Constructor
     */
    public CheckInvoiceController() {
        ir = new InvoiceRegistry();
    }

    /**
     * Sets Invoice Registry
     * @param ir
     */
    public void setInvoiceRegistry(InvoiceRegistry ir) {
        this.ir = ir;
    }

    /**
     * Queries Invoice through ID
     * @param id
     * @return
     * @throws ParseException
     */
    public boolean getInvoice(int id) throws ParseException {
        inv = ir.getInvoice(id);
        return inv != null;
    }

    /**
     * Returns Invoice
     * @return
     */
    public Invoice getInvoice() {
        return inv;
    }

    /**
     * Queries Invoice belonging to Client through email
     * @param email
     * @return
     * @throws ParseException
     */
    public boolean getInvoiceEmail(String email) throws ParseException {
        inv = ir.getInvoice(email);
        return inv != null;
    }

    /**
     * Queries Invoice belonging to Client from specific month 
     * @param month
     * @param email
     * @return
     * @throws ParseException
     */
    public boolean getInvoiceMonthEmail(int month, String email) throws ParseException {
        inv = ir.getInvoiceMonthEmail(month, email);
        return inv != null;
    }
    
    public boolean addInvoice(int id, String date, int period, int usedPoints, double totalCost, String email) throws ParseException {
        return ir.addInvoice(new Invoice(id, date, period, usedPoints, totalCost), email);
    }

}
