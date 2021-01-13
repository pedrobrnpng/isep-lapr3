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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author joaol
 */
public class ClientRegistryTest {
    
    ClientRegistry cr;
    
    public ClientRegistryTest() throws InvalidInfoClientException {
        HashSet<Client> clientList= new HashSet<>();
        clientList.add(new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M'));
        clientList.add(new Client("Cristiano Ronaldo", "chano_ronaldo@cr7mail.com", "siiiiiiiim", 35, 1.80, 80, 'M'));
        cr = new ClientRegistry();
        cr.setClientList(clientList);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getClients method, of class ClientRegistry.
     */
    @Test
    public void testGetClients() throws InvalidInfoClientException {
        System.out.println("getClients");
        Set<Client> expResult = new HashSet<>();
        expResult.add(new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M'));
        expResult.add(new Client("Cristiano Ronaldo", "chano_ronaldo@cr7mail.com", "siiiiiiiim", 35, 1.80, 80, 'M'));
        Set<Client> result = cr.getClients();
        assertEquals(expResult, result);
    }

    /**
     * Test of newClient method, of class ClientRegistry.
     */
    @Test
    public void testNewClient() throws InvalidInfoClientException {
        System.out.println("newClient");
        String name = "Vasco da Gama";
        String email = "incredibleindia@pombo-correio.pt";
        String pass = "1497";
        int age=45;
        double height = 1.70;
        double weight = 90;
        char gender = 'M';
        Client expResult = new Client("Vasco da Gama", "incredibleindia@pombo-correio.pt", "1497", 45, 1.70, 90, 'M');
        Client result = cr.newClient(name, email, pass, age, height, weight, gender);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateClient method, of class ClientRegistry.
     */
    @Test
    public void testValidateClient() throws InvalidInfoClientException {
        System.out.println("validateClient");
        HashSet<Client> clientList=new HashSet<>();
        ClientRegistry instance=new ClientRegistry();
        instance.setClientList(clientList);
        Client client = new Client("Fernando Pessoa", "joaolealmgs3@gmail.com", "iLuvDesassossego", 39, 1.70, 65, 'M');
        boolean expResult = true;
        boolean result = instance.validateClient(client);
        assertEquals(expResult, result);
        clientList.add(client);
        instance.setClientList(clientList);
        expResult = false;
        result = instance.validateClient(client);
        assertEquals(expResult, result);
        client = new Client("Fernando Pessoa", "fpessoa@gmail.com", "iLuvDesassossego", 39, 1.70, 65, 'M');
        expResult=true;
        result=instance.validateClient(client);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setClientList method, of class ClientRegistry.
     */
    @Test
    public void testSetClientList() throws InvalidInfoClientException {
        System.out.println("setClientList");
        HashSet<Client> clientList = new HashSet<>();
        ClientRegistry instance = new ClientRegistry();
        clientList.add(new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M'));
        clientList.add(new Client("Cristiano Ronaldo", "chano_ronaldo@cr7mail.com", "siiiiiiiim", 35, 1.80, 80, 'M'));
        instance.setClientList(clientList);
        assertEquals(clientList, instance.getClients());
    }

    /**
     * Test of setClientDB method, of class ClientRegistry.
     */
    @Test
    public void testSetClientDB() {
        System.out.println("setClientDB");
        ClientDB cdb = new ClientDB();
        ClientRegistry instance = new ClientRegistry();
        instance.setClientDB(cdb);
        assertEquals(cdb, instance.getClientDB());
    }

    /**
     * Test of addClient method, of class ClientRegistry.
     */
    @Test
    public void testAddClient() throws InvalidInfoClientException {
        System.out.println("addClient");
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        ClientRegistry instance = new ClientRegistry();
        ClientDB cdb= mock(ClientDB.class);
        when(cdb.registerClient(cli)).thenReturn(true);
        instance.setClientDB(cdb);
        boolean expResult = true;
        boolean result = instance.addClient(cli);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getClientDB method, of class ClientRegistry.
     */
    @Test
    public void testGetClientDB() {
        System.out.println("getClientDB");
        ClientRegistry instance = new ClientRegistry();
        ClientDB expResult = new ClientDB();
        instance.setClientDB(expResult);
        ClientDB result = instance.getClientDB();
        assertEquals(expResult, result);
    }

    /**
     * Test of newClient method, of class ClientRegistry.
     */
    @Test
    public void testNewClient_7args() throws Exception {
        System.out.println("newClient");
        String name = "j";
        String email = "j";
        String pass = "j";
        int age = 0;
        double height = 0.0;
        double weight = 0.0;
        char gender = 'F';
        Client expResult = new Client(name, email, pass, age, height, weight, gender);
        Client result = cr.newClient(name, email, pass, age, height, weight, gender);
        assertEquals(expResult, result);
    }

    /**
     * Test of newClient method, of class ClientRegistry.
     */
    @Test
    public void testNewClient_9args() throws Exception {
        System.out.println("newClient");
        String name = "j";
        String email = "j";
        int pass = 0;
        int age = 0;
        double height = 0.0;
        double weight = 0.0;
        char gender = 'M';
        double avgCyclingSpeed = 0.0;
        int points = 0;
        Client expResult = new Client(name, email, pass, age, height, weight, gender, avgCyclingSpeed, points);
        Client result = cr.newClient(name, email, pass, age, height, weight, gender, avgCyclingSpeed, points);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateClient method, of class ClientRegistry.
     */
    @Test
    public void testUpdateClient() throws InvalidInfoClientException {
        System.out.println("updateClient");
        Client cli = new Client("Fernando Pessoa", "incredibleindia@pombo-correio.pt", "iLuvDesassossego", 39, 1.70, 65, 'M');
        boolean expResult = true;
        ClientDB cdb=mock(ClientDB.class);
        when(cdb.updateClient(cli)).thenReturn(true);
        cr.setClientDB(cdb);
        boolean result = cr.updateClient(cli);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addAllClients method, of class ClientRegistry.
     */
    @Test
    public void testAddAllClients() throws InvalidInfoClientException {
        System.out.println("addAllClients");
        HashSet<Client> clientList = new HashSet<>();
        ClientRegistry instance = new ClientRegistry();
        clientList.add(new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M'));
        clientList.add(new Client("Cristiano Ronaldo", "chano_ronaldo@cr7mail.com", "siiiiiiiim", 35, 1.80, 80, 'M'));
        ClientDB cdb=mock(ClientDB.class);
        when(cdb.registerAllClients(clientList)).thenReturn(2);
        instance.setClientDB(cdb);
        instance.setClientList(clientList);
        int expResult = 2;
        int result = instance.addAllClients();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ClientRegistry.
     */
    @Test
    public void testHashCode() throws InvalidInfoClientException {
        System.out.println("hashCode");
        HashSet<Client> clientList=new HashSet<>();
        clientList.add(new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M'));
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(clientList);
        int expResult = hash;
        ClientRegistry instance=new ClientRegistry();
        instance.setClientList(clientList);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ClientRegistry.
     */
    @Test
    public void testEquals() throws InvalidInfoClientException {
        System.out.println("equals");
        Object obj = null;
        ClientRegistry instance = new ClientRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new ClientRegistry();
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj=instance;
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj=new Client("w", "w", "w", 1, 1, 1, 'M');
        expResult=false;
        result=instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ClientRegistry.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals2");
        ClientRegistry instance = new ClientRegistry();
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ClientRegistry.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals3");
        Object obj = new ClientRegistry();
        ClientRegistry instance = new ClientRegistry();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ClientRegistry.
     */
    @Test
    public void testEquals4() throws InvalidInfoClientException {
        System.out.println("equals4");
        Object obj = new Client("Fernando Pessoa", "incredibleindia@pombo-correio.pt", "iLuvDesassossego", 39, 1.70, 65, 'M');
        ClientRegistry instance = new ClientRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class ClientRegistry.
     */
    @Test
    public void testGetClient() throws Exception {
        System.out.println("getClient");
        String email = "joaolealmgs3@gmail.com";
        ClientRegistry instance = new ClientRegistry();
        Client expResult = new Client("João Leal", "joaolealngs3@gmail.com", 123, 19, 1.75, 70, 'M', 0, 0);
        ClientDB cdb=mock(ClientDB.class);
        when(cdb.getClient(email)).thenReturn(expResult);
        instance.setClientDB(cdb);
        Client result = instance.getClient(email);
        assertEquals(expResult, result);
    }
    
}
