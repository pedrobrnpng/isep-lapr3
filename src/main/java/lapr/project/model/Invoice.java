/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author joaol
 */
public class Invoice {
    
    private int invId;
    private Date date;
    private Month period;
    private int usedPoints;
    private double totalCost;
    
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Constructor
     * @param invId
     * @param date
     * @param period
     * @param usedPoints
     * @param totalCost
     * @throws ParseException
     */
    public Invoice(int invId, String date, int period, int usedPoints, double totalCost) throws ParseException {
        this.invId=invId;
        this.usedPoints=usedPoints;
        this.totalCost=totalCost;
        this.date=dt.parse(date);
        this.period=Month.of(period);
    }

    /**
     * Return Invoice ID
     * @return
     */
    public int getInvId() {
        return invId;
    }

    /**
     * Set Invoice ID
     * @param invId
     */
    public void setInvId(int invId) {
        this.invId = invId;
    }

    /**
     * Return date as Date instance
     * @return
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Return date as String
     * @return
     */
    public String getDateString() {
        return dt.format(date);
    }

    /**
     * Set date as Date instance
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Return period
     * @return
     */
    public Month getPeriod() {
        return period;
    }

    /**
     * Set period
     * @param period
     */
    public void setPeriod(Month period) {
        this.period = period;
    }

    /**
     * Return used points
     * @return
     */
    public int getUsedPoints() {
        return usedPoints;
    }

    /**
     * Set used points
     * @param usedPoints
     */
    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    /**
     * Return total cost
     * @return
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Set total cost
     * @param totalCost
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.invId;
        hash = 37 * hash + Objects.hashCode(this.date.toString());
        hash = 37 * hash + this.period.getValue();
        hash = 37 * hash + this.usedPoints;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.totalCost) ^ (Double.doubleToLongBits(this.totalCost) >>> 32));
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
        final Invoice other = (Invoice) obj;
        if (this.invId != other.invId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Invoice ID: " + invId + "\n    -Date of payment = " + dt.format(date) + "\n    -Period (Month of) = " + period + "\n    Total of points used = " + usedPoints + "\n    Total cost = " + totalCost;
    }
    
}
