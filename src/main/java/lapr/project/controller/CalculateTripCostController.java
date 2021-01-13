/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.model.InvoiceLine;
import lapr.project.model.InvoiceLineRegistry;
import lapr.project.model.InvoiceRegistry;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;

/**
 *
 * @author joaol
 */
public class CalculateTripCostController {

    VehicleRequest vr;
    VehicleRequestRegistry vrr;
    InvoiceRegistry ir;
    InvoiceLineRegistry ilr;

    /**
     *
     */
    public CalculateTripCostController() {
        vrr = new VehicleRequestRegistry();
        ilr = new InvoiceLineRegistry();
    }

    /**
     *
     * @param vrr
     */
    public void setVehicleRequestRegistry(VehicleRequestRegistry vrr) {
        this.vrr = vrr;
    }

    /**
     *
     * @param vrId
     * @return
     * @throws ParseException
     * @throws InvalidInvoiceLineCostException
     */
    public double calculateCost(int vrId) throws ParseException, InvalidInvoiceLineCostException {
        getVehicleRequest(vrId);
        double cost = vr.calculateCost();
        int invId = ir.getInvoice(vr.getUserEmail()).getInvId();
        ilr.addInvoiceLine(new InvoiceLine(invId, vr.getVehicleRequestID(), cost));
        return cost;
    }

    /**
     *
     * @param vrId
     */
    public void getVehicleRequest(int vrId) {
        this.vr = vrr.getVehicleRequest(vrId);
    }

    /**
     *
     * @param ir
     */
    public void setInvoiceRegistry(InvoiceRegistry ir) {
        this.ir = ir;
    }

    /**
     *
     * @param ilr
     */
    public void setInvoiceLineRegistry(InvoiceLineRegistry ilr) {
        this.ilr = ilr;
    }

}
