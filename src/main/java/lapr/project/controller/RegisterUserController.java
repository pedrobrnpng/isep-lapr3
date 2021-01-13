/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.Set;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class RegisterUserController {

    ClientRegistry cr;
    private Client cli;

    /**
     * Constructor
     */
    public RegisterUserController() {
        cr = new ClientRegistry();
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
     * Returns Client list
     * @return
     */
    public Set<Client> getClientList() {
        return cr.getClients();
    }

    /**
     * Creates Client and adds it to list
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @throws InvalidInfoClientException
     */
    public void newClient(String name, String email, String pass, int age, double height, double weight, char gender) throws InvalidInfoClientException {
        cli = cr.newClient(name, email, pass, age, height, weight, gender);
    }

    /**
     * Returns Client
     * @return
     */
    public Client getClient() {
        return cli;
    }

    /**
     * Registers Client in DB
     * @return
     */
    public boolean registerClient() {
        return cr.addClient(cli);
    }

    /**
     * Registers all Clients in list
     * @return
     */
    public int registerAllClients() {
        return cr.addAllClients();
    }
}
