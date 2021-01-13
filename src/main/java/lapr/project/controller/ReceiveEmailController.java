/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
import lapr.project.utils.EmailWriter;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class ReceiveEmailController {

    private Client cli;
    private ClientRegistry cr;
    private VehicleRequest vr;
    private VehicleRequestRegistry vrr;
    private final EmailWriter ew;

    /**
     * Constructor
     */
    public ReceiveEmailController() {
        cr = new ClientRegistry();
        vrr = new VehicleRequestRegistry();
        ew = new EmailWriter();
    }

    /**
     * Sets Client
     * @param cli
     */
    public void setClient(Client cli) {
        this.cli = cli;
    }

    /**
     * Queries Client
     * @param email
     * @return
     * @throws InvalidInfoClientException
     */
    public Client getClient(String email) throws InvalidInfoClientException {
        cli = cr.getClient(email);
        return cli;
    }

    /**
     * Returns Client
     * @return
     */
    public Client getClient() {
        return cli;
    }

    /**
     * Sets Vehicle Request
     * @param vr
     */
    public void setVehicleRequest(VehicleRequest vr) {
        this.vr = vr;
    }

    /**
     * Queries Vehicle Request
     * @param vrId
     * @return
     */
    public VehicleRequest getVehicleRequest(int vrId) {
        vr = vrr.getVehicleRequest(vrId);
        return vr;
    }

    /**
     * Returns Vehicle Request
     * @return
     */
    public VehicleRequest getVehicleRequest() {
        return vr;
    }

    /**
     * Returns Client Registry
     * @return
     */
    public ClientRegistry getClientRegistry() {
        return cr;
    }

    /**
     * Sets Client Registry
     * @param cr
     */
    public void setClientRegistry(ClientRegistry cr) {
        this.cr = cr;
    }

    /**
     * Returns Vehicle Request Registry
     * @return
     */
    public VehicleRequestRegistry getVehicleRequestRegistry() {
        return vrr;
    }

    /**
     * Sets Vehicle Request Registry
     * @param vrr
     */
    public void setVehicleRequestRegistry(VehicleRequestRegistry vrr) {
        this.vrr = vrr;
    }

    /**
     * Checks if Vehicle is available
     * @return
     */
    public boolean isAvailable() {
        return vr.getEndTime() != null && !vr.getEndTime().isEmpty();
    }

    /**
     * Writes and sends email
     * @return
     */
    public boolean writeEmail() {
        return !ew.sendEmail(cli.getEmail(), cli.getName(), isAvailable(), vr.diffTime()).isEmpty();
    }

}
