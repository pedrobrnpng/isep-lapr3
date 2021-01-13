/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
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
public class RegisterUserControllerTest {
    
    RegisterUserController ruc;
    
    public RegisterUserControllerTest() {
        ruc=new RegisterUserController();
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
     * Test of newClient method, of class RegisterUserController.
     */
    @Test
    public void testNewClient() throws InvalidInfoClientException {
        System.out.println("newClient");
        String name="João Leal";
        String email="joaolealmgs3@gmail.com";
        String pass="password";
        int age=19;
        double height=1.75;
        double weight=75;
        char gender='M';
        ruc.newClient(name, email, pass, age, height, weight, gender);
        Client expResult=new Client(name, email, pass, age, height, weight, gender);
        Client result=ruc.getClient();
        assertEquals(expResult,result);
    }
    
    /**
     * Test of registerClient method, of class RegisterUserController.
     */
    @Test
    public void testRegisterClient() throws InvalidInfoClientException {
        System.out.println("registerClient");
        String name="Joao Leal";
        String email="jj";
        String pass="password";
        int age=19;
        double height=1.75;
        double weight=75;
        char gender='M';
        Client cli=new Client(name, email, pass, age, height, weight, gender);
        ClientDB cdb=mock(ClientDB.class);
        when(cdb.registerClient(cli)).thenReturn(true);
        ClientRegistry cr=new ClientRegistry();
        cr.setClientDB(cdb);
        ruc.setClientRegistry(cr);
        ruc.newClient(name, email, pass, age, height, weight, gender);
        boolean result= ruc.registerClient();
        assertEquals(true,result);
        name="js";
        email="email";
        ruc.newClient(name, email, pass, age, height, weight, gender);
        result= ruc.registerClient();
        assertEquals(false,result);
    }

    /**
     * Test of getClient method, of class RegisterUserController.
     */
    @Test
    public void testGetClient() throws InvalidInfoClientException {
        System.out.println("getClient");
        RegisterUserController instance = new RegisterUserController();
        Client expResult = new Client("j", "j", "j", 1, 1, 1, 'M');
        instance.newClient("j", "j", "j", 1, 1, 1, 'M');
        Client result = instance.getClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerAllClients method, of class RegisterUserController.
     */
    @Test
    public void testRegisterAllClients() throws InvalidInfoClientException {
        System.out.println("registerAllClients");
        RegisterUserController instance = new RegisterUserController();
        ClientDB cdb=mock(ClientDB.class);
        HashSet<Client> clientList=new HashSet<>();
        clientList.add(new Client("j", "j", "j", 1, 1, 1, 'M'));
        clientList.add(new Client("j", "j1", "j", 1, 1, 1, 'M'));
        when(cdb.registerAllClients(clientList)).thenReturn(2);
        int expResult = 2;
        instance.cr.setClientDB(cdb);
        instance.cr.setClientList(clientList);
        int result = instance.registerAllClients();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientList method, of class RegisterUserController.
     */
    @Test
    public void testGetClientList() {
        System.out.println("getClientList");
        RegisterUserController instance = new RegisterUserController();
        Set<Client> expResult = new HashSet<>();
        Set<Client> result = instance.getClientList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientRegistry method, of class RegisterUserController.
     */
    @Test
    public void testGetClientRegistry() {
        System.out.println("getClientRegistry");
        RegisterUserController instance = new RegisterUserController();
        ClientRegistry expResult = new ClientRegistry();
        ClientRegistry result = instance.getClientRegistry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClientRegistry method, of class RegisterUserController.
     */
    @Test
    public void testSetClientRegistry() {
        System.out.println("setClientRegistry");
        ClientRegistry cr = new ClientRegistry();
        RegisterUserController instance = new RegisterUserController();
        instance.setClientRegistry(cr);
        assertEquals(cr, instance.getClientRegistry());
    }
    
}
