/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.HashSet;
import lapr.project.data.CreditCardDB;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.CreditCardRegistry;
import lapr.project.utils.exceptions.CreditCardExpiredException;

/**
 *
 * @author joaol
 */
public class InsertCreditCardController {
    
    private CreditCardRegistry ccr;
    private CreditCardDB ccdb;
    private CreditCard cc;
    private Client cli;
    
    /**
     * Constructor
     */
    public InsertCreditCardController() {
        ccdb=new CreditCardDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
        ccr=new CreditCardRegistry();
    }
    
    /**
     * Creates Credit Card
     * @param tin
     * @param expDate
     * @param credits
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public void newCreditCard(int tin, String expDate, double credits) throws ParseException, CreditCardExpiredException {
        cc=new CreditCard(tin, expDate, credits);
    }
    
    /**
     * Sets Client
     * @param cli
     */
    public void setClient(Client cli) {
        this.cli=cli;
    }
    
    /**
     * Sets Client List
     * @param creditCardList
     */
    public void setCreditCardList(HashSet<CreditCard> creditCardList) {
        this.ccr=new CreditCardRegistry();
        ccr.setCreditCardList(creditCardList);
    }
    
    /**
     * Returns Client
     * @return
     */
    public Client getClient() {
        return cli;
    }
    
    /**
     * Returns Credit Card
     * @return
     */
    public CreditCard getCreditCard() {
        return cc;
    }
    
    /**
     * Sets Credit Card DB
     * @param ccdb
     */
    public void setCreditCardDB(CreditCardDB ccdb) {
        this.ccdb=ccdb;
    }
    
    /**
     * Registers Credit Card
     * @return
     */
    public boolean registerCreditCard() {
        if (ccr.validateCreditCard(cc)) {
            return ccdb.registerCreditCard(cc, cli);
        }
        return false;
    }
}