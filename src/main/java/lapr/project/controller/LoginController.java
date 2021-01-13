/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.User;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author Simao
 */
public class LoginController {

    private HashSet<Client> clientList;
    private ClientDB cdb;

    /**
     *
     * @param cdb
     */
    public void setClientDB(ClientDB cdb) {
        this.cdb = cdb;
    }

    /**
     *
     * @param name
     * @param pass
     * @return
     * @throws InvalidInfoClientException
     */
    public boolean loginUser(String name, String pass) throws InvalidInfoClientException {
        getExistingAccounts();
        User user = new User(name, null, pass);
        int accesslevel = logUser(user);
        return accesslevel != 0;
    }

    private void getExistingAccounts() throws InvalidInfoClientException {
        clientList = cdb.getClients();
    }

    /**
     * looks for user on the client database. If data is correct and found,
     * returns 1 for accesslevel, otherwise 0
     *
     * @param name name of client
     * @param pass password of client
     * @return 1 or 0
     */
    private int logUser(User user) {
        for (Client client : clientList) {
            if (client.getName().equals(user.getName()) && client.getPass() == user.getPass()) {
                return 1;

            }
        }
        return 0;
    }
}
