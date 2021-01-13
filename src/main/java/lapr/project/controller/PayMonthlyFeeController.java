/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashSet;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.CreditCard;
import lapr.project.model.CreditCardRegistry;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceLine;
import lapr.project.model.InvoiceLineRegistry;
import lapr.project.model.InvoiceRegistry;
import lapr.project.utils.exceptions.CreditCardExpiredException;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;

/**
 *
 * @author joaol
 */
public class PayMonthlyFeeController {

    private Invoice inv;
    private InvoiceRegistry ir;
    private HashSet<InvoiceLine> ilList;
    private InvoiceLineRegistry ilr;
    private CreditCard cc;
    private CreditCardRegistry ccr;
    private Client cli;
    private ClientRegistry cr;
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor
     */
    public PayMonthlyFeeController() {
        ir = new InvoiceRegistry();
        ilr = new InvoiceLineRegistry();
        ccr = new CreditCardRegistry();
        cr = new ClientRegistry();
    }

    /**
     * Returns Invoice Registry
     * @return
     */
    public InvoiceRegistry getIr() {
        return ir;
    }

    /**
     * Sets Invoice Registry
     * @param ir
     */
    public void setIr(InvoiceRegistry ir) {
        this.ir = ir;
    }

    /**
     * Returns Invoice Line Registry
     * @return
     */
    public InvoiceLineRegistry getIlr() {
        return ilr;
    }

    /**
     * Sets Invoice Line Registry
     * @param ilr
     */
    public void setIlr(InvoiceLineRegistry ilr) {
        this.ilr = ilr;
    }

    /**
     * Returns Credit Card Registry
     * @return
     */
    public CreditCardRegistry getCcr() {
        return ccr;
    }

    /**
     * Sets Credit Card Registry
     * @param ccr
     */
    public void setCcr(CreditCardRegistry ccr) {
        this.ccr = ccr;
    }

    /**
     * Returns Client Registry
     * @return
     */
    public ClientRegistry getCr() {
        return cr;
    }

    /**
     * Sets Client Registry
     * @param cr
     */
    public void setCr(ClientRegistry cr) {
        this.cr = cr;
    }

    /**
     * Calculates monthly fee, and deducts credits from Client's Credit Card
     * @param email
     * @return
     * @throws ParseException
     * @throws InvalidInvoiceLineCostException
     * @throws InvalidInfoClientException
     * @throws CreditCardExpiredException
     */
    public boolean payMonthlyFee(String email) throws ParseException, InvalidInvoiceLineCostException, InvalidInfoClientException, CreditCardExpiredException {
        inv = ir.getInvoice(email);
        cli = cr.getClient(email);
        cc = ccr.getCreditCard(email);
        if (cli == null || inv == null) {
            return false;
        }
        ilList = ilr.getAllInvLinesOfInv(inv.getInvId());
        double cost = 0;
        for (InvoiceLine il : ilList) {
            cost += il.getCost();
        }
        double costInv = inv.getTotalCost();
        double totalCost = Math.round((cost + costInv) * 1e2) / 1e2;
        int points = cli.getPoints();
        int count = calculateTotalCost(totalCost, points);
        totalCost -= count;
        inv.setUsedPoints(points - (points - count * 10));
        points -= count * 10;
        cli.setPoints(points);
        inv.setTotalCost(totalCost);
        inv.setDate(dt.parse(Instant.now().toString()));
        return (cr.updateClient(cli) && ir.updateInvoice(inv) && ccr.updateCreditCard(cc, totalCost));
    }

    /**
     * Calculates total cost using points
     * @param totalCost
     * @param points
     * @return
     */
    public int calculateTotalCost(double totalCost, int points) {
        int count = 0;
        int whole = (int) totalCost;
        while (whole != 0 && points / 10 != 0) {
            whole--;
            points -= 10;
            count++;
        }
        return count;
    }

}
