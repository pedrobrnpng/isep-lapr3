/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.Set;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceRegistry;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class CheckUserInfoController {

    Client cli;
    ClientRegistry cr;
    InvoiceRegistry ir;

    /**
     * Constructor
     */
    public CheckUserInfoController() {
        cr = new ClientRegistry();
    }

    /**
     * Set Client Registry
     * @param cr
     */
    public void setClientRegistry(ClientRegistry cr) {
        this.cr = cr;
    }

    /**
     * Queries Client through email
     * @param email
     * @return
     * @throws InvalidInfoClientException
     */
    public boolean getClient(String email) throws InvalidInfoClientException {
        this.cli = cr.getClientDB().getClient(email);
        return cli != null;
    }

    /**
     * Returns Client
     * @return
     */
    public Client getClient() {
        return cli;
    }

    /**
     * Prints Client's information
     * @return
     */
    public String showUserInfo() {
        return cli.toString();
    }

    /**
     * Queries Client through email and shows information
     * @param email
     * @return
     * @throws InvalidInfoClientException
     */
    public String getUserInfo(String email) throws InvalidInfoClientException {
        if (getClient(email)) {
            return showUserInfo();
        } else {
            return "Client not found.";
        }
    }

    /**
     * Calculates Client's total debt
     * @param email
     * @return
     * @throws InvalidInfoClientException
     * @throws ParseException
     */
    public double getUserDebt(String email) throws InvalidInfoClientException, ParseException {
        Set<Invoice> allInvoices = ir.getInvoiceList(email);
        double debt = 0;
        for (Invoice inv : allInvoices) {
            debt += inv.getTotalCost();
        }
        return debt;
    }

}
