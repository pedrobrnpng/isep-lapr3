/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.data.CreditCardDB;
import lapr.project.model.CreditCard;
import lapr.project.utils.exceptions.CreditCardExpiredException;

/**
 *
 * @author joaol
 */
public class PayRegistrationFeeController {

    private CreditCardDB ccdb;
    private CreditCard cc;

    /**
     * Constructor
     */
    public PayRegistrationFeeController() {
        ccdb = new CreditCardDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }

    /**
     * Deducts credits from CreditCard
     * @param cost
     * @return
     */
    public boolean deductCost(double cost) {
        return ccdb.deductCost(cc, cost);
    }

    /**
     * Sets Credit Card
     * @param cc
     */
    public void setCreditCard(CreditCard cc) {
        this.cc = cc;
    }

    /**
     * Returns Credit Card
     * @return
     */
    public CreditCard getThisCreditCard() {
        return cc;
    }

    /**
     * Queries Credit Card through TIN
     * @param tin
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public void getCreditCard(int tin) throws ParseException, CreditCardExpiredException {
        cc = ccdb.getCreditCard(tin);
    }

    /**
     * Sets CreditCardDB
     * @param ccdb
     */
    public void setCreditCardDB(CreditCardDB ccdb) {
        this.ccdb = ccdb;
    }

}
