/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;
import lapr.project.data.CreditCardDB;
import lapr.project.utils.exceptions.CreditCardExpiredException;

/**
 *
 * @author joaol
 */
public class CreditCardRegistry {
    private HashSet<CreditCard> creditCardList;
    private CreditCardDB ccdb;
    
    /**
     * Contructor (Initializes CreditCardDB instance)
     */
    public CreditCardRegistry() {
       this.creditCardList=new HashSet<>();
        ccdb=new CreditCardDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }
    
    /**
     * Set Credit Card list
     * @param creditCardList
     */
    public void setCreditCardList(HashSet<CreditCard> creditCardList) {
        this.creditCardList=creditCardList;
    }
    
    /**
     * Return Credit Card list
     * @return
     */
    public HashSet<CreditCard> getCreditCards() {
        return this.creditCardList;
    }
    
    /**
     * Set CreditCardDB
     * @param ccdb
     */
    public void setCreditCardDB(CreditCardDB ccdb) {
        this.ccdb=ccdb;
    }
    
    /**
     * Return CreditCardDB
     * @return
     */
    public CreditCardDB getCreditCardDB() {
        return ccdb;
    }
    
    /**
     * Queries Credit Card through TIN
     * @param tin
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard getCreditCard(int tin) throws ParseException, CreditCardExpiredException {
        return ccdb.getCreditCard(tin);
    }
    
    /**
     * Queries Credit Card from Client's email
     * @param email
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard getCreditCard(String email) throws ParseException, CreditCardExpiredException {
        return ccdb.getCreditCard(email);
    }
    
    /**
     * Updates Credit Card information
     * @param cc
     * @param cost
     * @return
     */
    public boolean updateCreditCard(CreditCard cc, double cost) {
        return ccdb.deductCost(cc, cost);
    }
    
    /**
     * Creates a Credit Card instance and adds it to list
     * @param tin
     * @param expDate
     * @param credits
     * @return
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard newCreditCard(int tin, String expDate, double credits) throws ParseException, CreditCardExpiredException {
        CreditCard cc= new CreditCard(tin, expDate, credits);
        creditCardList.add(cc);
        return cc;
    }
    
    /**
     * Validates Credit Card's TIN
     * @param creditCard
     * @return
     */
    public boolean validateCreditCard(CreditCard creditCard) {
        if (!creditCardList.isEmpty()) {
            for (CreditCard p: creditCardList) {
                if(creditCard.getTin()==p.getTin()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.creditCardList);
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
        final CreditCardRegistry other = (CreditCardRegistry) obj;
        return Objects.equals(this.creditCardList, other.creditCardList);
    }
}

