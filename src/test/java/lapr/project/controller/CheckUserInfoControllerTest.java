/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.HashSet;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceRegistry;
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
public class CheckUserInfoControllerTest {

    private CheckUserInfoController cuic;
    private ClientRegistry cr;
    private InvoiceRegistry ir;

    public CheckUserInfoControllerTest() throws InvalidInfoClientException, ParseException {
        cuic = new CheckUserInfoController();
        cr = new ClientRegistry();
        ir = new InvoiceRegistry();
        ClientDB cdb = mock(ClientDB.class);
        InvoiceDB idb = mock(InvoiceDB.class);
        cr.setClientDB(cdb);
        ir.setInvoiceDB(idb);
        Client cli = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        cuic.setClientRegistry(cr);
        HashSet<Invoice> invList = new HashSet<>();
        invList.add(new Invoice(1, "0001-01-01", 1, 0, 14));
        invList.add(new Invoice(2, "0001-01-01", 1, 0, 2));
        invList.add(new Invoice(3, "0001-01-01", 1, 0, 5));
        when(idb.getAllInvoices("joaolealmgs3@gmail.com")).thenReturn(invList);
        cuic.ir = ir;
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
     * Test of getClient method, of class CheckUserInfoController.
     */
    @Test
    public void testGetClient() throws InvalidInfoClientException {
        System.out.println("getClient");
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = cuic.getClient(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of showUserInfo method, of class CheckUserInfoController.
     */
    @Test
    public void testShowUserInfo() throws InvalidInfoClientException {
        System.out.println("showUserInfo");
        String expResult = "João Leal:\n"
                + "    -Email = joaolealmgs3@gmail.com\n"
                + "    -Pass = 1217204428\n"
                + "    -Age = 19\n"
                + "    -Height = 1.77\n"
                + "    -Weight = 75.0\n"
                + "    -Gender = M\n"
                + "    -Average Cycling Speed = 0.0\n"
                + "    -Points = 0";
        cuic.getClient("joaolealmgs3@gmail.com");
        String result = cuic.showUserInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserInfo method, of class CheckUserInfoController.
     */
    @Test
    public void testGetUserInfo() throws InvalidInfoClientException {
        System.out.println("getUserInfo");
        String email = "joaolealmgs3@gmail.com";
        String result = cuic.getUserInfo(email);
        String expResult = "João Leal:\n"
                + "    -Email = joaolealmgs3@gmail.com\n"
                + "    -Pass = 1217204428\n"
                + "    -Age = 19\n"
                + "    -Height = 1.77\n"
                + "    -Weight = 75.0\n"
                + "    -Gender = M\n"
                + "    -Average Cycling Speed = 0.0\n"
                + "    -Points = 0";
        assertEquals(expResult, result);
        result = cuic.getUserInfo("");
        expResult = "Client not found.";
        assertEquals(expResult, result);
    }

    /**
     * Test of setClientRegistry method, of class CheckUserInfoController.
     */
    @Test
    public void testSetClientRegistry() {
        System.out.println("setClientRegistry");
        ClientRegistry cr = new ClientRegistry();
        cuic.setClientRegistry(cr);
        assertEquals(cr, cuic.cr);
    }

    /**
     * Test of getClient method, of class CheckUserInfoController.
     */
    @Test
    public void testGetClient_0args() throws InvalidInfoClientException {
        System.out.println("getClient");
        cuic.getClient("joaolealmgs3@gmail.com");
        Client expResult = new Client("João Leal", "joaolealmgs3@gmail.com", "password", 19, 1.77, 75, 'M');
        Client result = cuic.getClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserDebt method, of class CheckUserInfoController.
     */
    @Test
    public void testGetUserDebt() throws Exception {
        System.out.println("getUserDebt");
        String email = "joaolealmgs3@gmail.com";
        double expResult = 21;
        double result = cuic.getUserDebt(email);
        assertEquals(expResult, result, 0.0);
    }

}
