/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import lapr.project.data.ClientDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

/**
 *
 * @author Simao
 */
public class LoginControllerTest {
    
    LoginController lc;
    RegisterUserController ruc;
    ClientRegistry cr;
    
    public LoginControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws InvalidInfoClientException {
        lc = new LoginController();
        ruc = new RegisterUserController();
        ClientDB cdb = mock(ClientDB.class);
        HashSet<Client> h = new HashSet<>();
        String name="João Leal";
        String email="joaolealmgs3@gmail.com";
        String pass="password";
        int age=19;
        double height=1.75;
        double weight=75;
        char gender='M';
        h.add(new Client(name, email, pass, age, height, weight, gender));
        when(cdb.getClients()).thenReturn(h);
        cr=new ClientRegistry();
        cr.setClientDB(cdb);
        lc.setClientDB(cdb);
        ruc.cr=cr;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loginUser method, of class LoginController for false.
     */
    @Test
    public void testLoginUserFalse() throws InvalidInfoClientException {
        System.out.println("loginUserFalse");
        String name="Simão Coimbra";
        String pass="pass";
        boolean result=lc.loginUser(name, pass);
        assertEquals(false,result);
    }
    
     /**
     * Test of loginUser method, of class LoginController for true. 
     */
    @Test
    public void testLoginUserTrue() throws InvalidInfoClientException {
        System.out.println("loginUserTrue");
        String name="João Leal";
        String pass="password";
        boolean result=lc.loginUser(name, pass);
        assertEquals(true,result);
    }
    

}
