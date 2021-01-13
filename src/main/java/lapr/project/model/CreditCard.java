/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import lapr.project.utils.exceptions.CreditCardExpiredException;

/**
 *
 * @author joaol
 */
public class CreditCard {
    
    private int tin;
    private Date expDate;
    private double credits;
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Constructor
     * @param tin
     * @param expDate
     * @param credits
     * @throws ParseException
     * @throws CreditCardExpiredException
     */
    public CreditCard(int tin, String expDate, double credits) throws ParseException, CreditCardExpiredException {
        this.tin=tin;
        this.expDate=dt.parse(expDate);
        if (isExpired()) {
            throw new CreditCardExpiredException("Credit card is expired");
        }
        this.credits=credits;
    }

    /**
     * Return TIN
     * @return
     */
    public int getTin() {
        return tin;
    }

    /**
     * Set TIN
     * @param tin
     */
    public void setTin(int tin) {
        this.tin = tin;
    }

    /**
     * Return expiration date as Date instance
     * @return
     */
    public Date getExpDate() {
        return expDate;
    }
    
    /**
     * Return expiration date as String
     * @return
     */
    public String getExpDateString() {
        return dt.format(expDate);
    }

    /**
     * Set expiration Date as String
     * @param expDate
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
    /**
     * Set expiration Date as Date instance
     * @param expDate
     * @throws ParseException
     */
    public void setExpDate(String expDate) throws ParseException {
        this.expDate = dt.parse(expDate);
    }

    /**
     * Return credits
     * @return
     */
    public double getCredits() {
        return credits;
    }

    /**
     * Set credits
     * @param credits
     */
    public void setCredits(double credits) {
        this.credits = credits;
    }
    
    /**
     * Subtracts credits from card
     * @param cost
     * @return
     */
    public boolean deductCredits(double cost) {
        double result=this.credits-cost;
        if (result<0) {
            return false;
        }
        this.credits=result;
        return true;
    }
    
    /**
     * Adds credits to card
     * @param dp
     */
    public void addCredits(double dp) {
        this.credits+=dp;
    }
    
    /**
     * Checks if card is expired
     * @return
     */
    public boolean isExpired() {
        Date today=new Date();
        return today.after(expDate);
    }

    /**
     * Hash code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.tin ^ this.tin >>> 14;
        hash = 71 * hash + (Objects.hashCode(this.expDate.toString()) ^ Objects.hashCode(this.expDate.toString()) >>> 14);
        return hash;
    }

    /**
     * Equals
     * @param obj
     * @return 
     */
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
        final CreditCard other = (CreditCard) obj;
        if (this.tin != other.tin) {
            return false;
        }
        return true;
    }
    
    /**
     * toString
     * @return 
     */
    @Override
    public String toString() {
        return "TIN =" + tin + "\nExpiration date = " + dt.format(expDate) + "\nCredits = " + credits;
    }
    
}
