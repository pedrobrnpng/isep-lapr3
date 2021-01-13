/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class ClientRegistry {
    private Set<Client> clientList;
    private ClientDB cdb;
    
    /**
     * Constructor (initializes DB instance)
     */
    public ClientRegistry() {
        this.clientList=new HashSet<>();
        cdb=new ClientDB("jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_2019_G022", "qwerty");
    }
    
    /**
     * Set Client list
     * @param clientList
     */
    public void setClientList(Set<Client> clientList) {
        this.clientList=clientList;
    }
    
    /**
     * Set ClientDB
     * @param cdb
     */
    public void setClientDB(ClientDB cdb) {
        this.cdb=cdb;
    }
    
    /**
     * Return ClientDB
     * @return
     */
    public ClientDB getClientDB() {
        return cdb;
    }
    
    /**
     * Return Client list
     * @return
     */
    public Set<Client> getClients() {
        return this.clientList;
    }
    
    /**
     * Query Client by email
     * @param email
     * @return
     * @throws InvalidInfoClientException
     */
    public Client getClient(String email) throws InvalidInfoClientException {
        return cdb.getClient(email);
    }
    
    /**
     * Creates and adds Client to list (unencrypted)
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @return
     * @throws InvalidInfoClientException
     */
    public Client newClient(String name, String email, String pass, int age, double height, double weight, char gender) throws InvalidInfoClientException {
        Client cli=new Client(name, email, pass, age, height, weight, gender);
        this.clientList.add(cli);
        return cli;
    }
    
    /**
     * Creates and adds Client to list (encrypted, full constructor)
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @param avgCyclingSpeed
     * @param points
     * @return
     * @throws InvalidInfoClientException
     */
    public Client newClient(String name, String email, int pass, int age, double height, double weight, char gender, double avgCyclingSpeed, int points) throws InvalidInfoClientException {
        Client cli= new Client(name, email, pass, age, height, weight, gender, avgCyclingSpeed, points);
        this.clientList.add(cli);
        return cli;
    }
    
    /**
     * Validates Client's email
     * @param client
     * @return
     */
    public boolean validateClient(Client client) {
        if (!clientList.isEmpty()) {
            for (Client p: clientList) {
                if(client.getEmail().equals(p.getEmail())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Registers Client in DB
     * @param cli
     * @return
     */
    public boolean addClient(Client cli) {
        return cdb.registerClient(cli);
    }
    
    /**
     * Registers multiple Clients in DB
     * @return
     */
    public int addAllClients() {
        return cdb.registerAllClients(clientList);
    }
    
    /**
     * Updates Client's information
     * @param cli
     * @return
     */
    public boolean updateClient(Client cli) {
        return cdb.updateClient(cli);
    }

    /**
     * Hash code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.clientList);
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
        final ClientRegistry other = (ClientRegistry) obj;
        return Objects.equals(this.clientList, other.clientList);
    }
}

