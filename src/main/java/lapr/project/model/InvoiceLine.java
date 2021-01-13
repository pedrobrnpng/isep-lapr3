/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;

/**
 *
 * @author joaol
 */
public class InvoiceLine {
    
    private int invId;
    private int vrId;
    private double cost;
    
    /**
     * Constructor
     * @param invId
     * @param vrId
     * @param cost
     * @throws InvalidInvoiceLineCostException
     */
    public InvoiceLine(int invId, int vrId, double cost) throws InvalidInvoiceLineCostException {
        if (cost<0) throw new InvalidInvoiceLineCostException("Invoice line cost can't be negative.");
        this.cost=cost;
        this.invId=invId;
        this.vrId=vrId;
    }

    /**
     * Return cost
     * @return
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set cost
     * @param cost
     * @throws InvalidInvoiceLineCostException
     */
    public void setCost(double cost) throws InvalidInvoiceLineCostException {
        if (cost<0) throw new InvalidInvoiceLineCostException("Invoice line cost can't be negative.");
        this.cost = cost;
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
     * Return Vehicle Request ID
     * @return
     */
    public int getVrId() {
        return vrId;
    }

    /**
     * Set Vehicle Request ID
     * @param vrId
     */
    public void setVrId(int vrId) {
        this.vrId = vrId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.invId;
        hash = 59 * hash + this.vrId;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.cost) ^ (Double.doubleToLongBits(this.cost) >>> 32));
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
        final InvoiceLine other = (InvoiceLine) obj;
        if (this.invId != other.invId) {
            return false;
        }
        if (this.vrId != other.vrId) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Invoice ID: "+invId+"\nVehicle request ID: "+vrId+"\nCost of invoice line = "+cost;
    }
    
}
