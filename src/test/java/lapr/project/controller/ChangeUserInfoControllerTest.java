/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author joaol
 */
public class ChangeUserInfoControllerTest {
    
    private ChangeUserInfoController cuic;
    private ClientRegistry cr;
    private Client cli;
    
    public ChangeUserInfoControllerTest() throws InvalidInfoClientException {
        cuic=new ChangeUserInfoController();
        cr=new ClientRegistry();
        ClientDB cdb=mock(ClientDB.class);
        cr.setClientDB(cdb);
        cli=new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(cdb.updateClient(cli)).thenReturn(true);
        cuic.setClientRegistry(cr);
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
     * Test of setClientRegistry method, of class ChangeUserInfoController.
     */
    @Test
    public void testSetClientRegistry() {
        System.out.println("setClientRegistry");
        ClientRegistry cr = new ClientRegistry();
        cuic.setClientRegistry(cr);
        assertEquals(cr, cuic.cr);
    }

    /**
     * Test of getClient method, of class ChangeUserInfoController.
     */
    @Test
    public void testGetClient() throws Exception {
        System.out.println("getClient");
        System.out.println("getClient");
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = cuic.getClient(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeInfo method, of class ChangeUserInfoController.
     */
    @Test
    public void testChangeInfo() throws Exception {
        System.out.println("changeInfo");
        boolean expResult = true;
        boolean result = cuic.changeInfo(cli.getEmail(), cli.getName(), cli.getAge(), cli.getHeight(), cli.getWeight(), cli.getGender(), cli.getAvgCyclingSpeed(), cli.getPoints());
        assertEquals(expResult, result);
        expResult=false;
        result=cuic.changeInfo("j", cli.getName(), cli.getAge(), cli.getHeight(), cli.getWeight(), cli.getGender(), cli.getAvgCyclingSpeed(), cli.getPoints());
        assertEquals(expResult, result);
    }
    
}
