/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class ChangeUserInfoController {

    Client cli;
    ClientRegistry cr;

    /**
     * Constructor
     */
    public ChangeUserInfoController() {
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
     * Changes Client's information
     * @param email
     * @param name
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @param avgCyclingSpeed
     * @param points
     * @return
     * @throws InvalidInfoClientException
     */
    public boolean changeInfo(String email, String name, int age, double height, double weight, char gender, double avgCyclingSpeed, int points) throws InvalidInfoClientException {
        if (!getClient(email)) {
            return false;
        }
        Client cli2 = cr.newClient(name, email, cli.getPass(), age, height, weight, gender, avgCyclingSpeed, points);
        return cr.updateClient(cli2);
    }

}
