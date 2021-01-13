/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lapr.project.data.ClientDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
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
public class ReceiveEmailControllerTest {
    
    private ReceiveEmailController rec;
    
    public ReceiveEmailControllerTest() throws InvalidInfoClientException {
        rec=new ReceiveEmailController();
        ClientDB cbd=mock(ClientDB.class);
        Client cli=new Client("João Leal", "g22ridesharing@gmail.com", "pass", 19, 1.75, 75, 'M');
        when(cbd.getClient("g22ridesharing@gmail.com")).thenReturn(cli);
        ClientRegistry cr=new ClientRegistry();
        cr.setClientDB(cbd);
        rec.setClientRegistry(cr);
        VehicleRequestsDB vrdb=mock(VehicleRequestsDB.class);
        VehicleRequest vr=new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString());
        when(vrdb.getVehicleRequest(123)).thenReturn(vr);
        VehicleRequestRegistry vrr=new VehicleRequestRegistry(vrdb);
        rec.setVehicleRequestRegistry(vrr);
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
     * Test of setClient method, of class ReceiveEmailController.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        Client cli = null;
        ReceiveEmailController instance = new ReceiveEmailController();
        instance.setClient(cli);
        assertEquals(cli, instance.getClient());
    }

    /**
     * Test of getClient method, of class ReceiveEmailController.
     */
    @Test
    public void testGetClient() throws Exception {
        System.out.println("getClient");
        String email = "g22ridesharing@gmail.com";
        Client expResult = new Client("João Leal", "g22ridesharing@gmail.com", "pass", 19, 1.75, 75, 'M');
        Client result = rec.getClient(email);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getClient method, of class ReceiveEmailController.
     */
    @Test
    public void testGetClient_0args() throws InvalidInfoClientException {
        System.out.println("getClient");
        ReceiveEmailController instance = new ReceiveEmailController();
        Client expResult = new Client("João Leal", "g22ridesharing@gmail.com", "pass", 19, 1.75, 75, 'M');
        instance.setClient(expResult);
        Client result = instance.getClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicleRequest method, of class ReceiveEmailController.
     */
    @Test
    public void testSetVehicleRequest() {
        System.out.println("setVehicleRequest");
        VehicleRequest vr = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString());
        ReceiveEmailController instance = new ReceiveEmailController();
        instance.setVehicleRequest(vr);
        assertEquals(vr, instance.getVehicleRequest());
    }

    /**
     * Test of getVehicleRequest method, of class ReceiveEmailController.
     */
    @Test
    public void testGetVehicleRequest() {
        System.out.println("getVehicleRequest");
        int vrId = 123;
        VehicleRequest expResult = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString());
        VehicleRequest result = rec.getVehicleRequest(vrId);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getVehicleRequest method, of class ReceiveEmailController.
     */
    @Test
    public void testGetVehicleRequest_0args() {
        System.out.println("getVehicleRequest");
        ReceiveEmailController instance = new ReceiveEmailController();
        VehicleRequest expResult = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString());
        instance.setVehicleRequest(expResult);
        VehicleRequest result = instance.getVehicleRequest();
        assertEquals(expResult, result);
    }

    /**
     * Test of isAvailable method, of class ReceiveEmailController.
     */
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        ReceiveEmailController instance = new ReceiveEmailController();
        VehicleRequest vr = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString());
        instance.setVehicleRequest(vr);
        boolean expResult = true;
        boolean result = instance.isAvailable();
        assertEquals(expResult, result);
        vr = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), "");
        instance.setVehicleRequest(vr);
        expResult=false;
        result = instance.isAvailable();
        assertEquals(expResult, result);
        vr = new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), null);
        instance.setVehicleRequest(vr);
        expResult=false;
        result = instance.isAvailable();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientRegistry method, of class ReceiveEmailController.
     */
    @Test
    public void testGetClientRegistry() {
        System.out.println("getClientRegistry");
        ReceiveEmailController instance = new ReceiveEmailController();
        ClientRegistry expResult = new ClientRegistry();
        ClientRegistry result = instance.getClientRegistry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClientRegistry method, of class ReceiveEmailController.
     */
    @Test
    public void testSetClientRegistry() {
        System.out.println("setClientRegistry");
        ClientRegistry cr = new ClientRegistry();
        ReceiveEmailController instance = new ReceiveEmailController();
        instance.setClientRegistry(cr);
        assertEquals(cr, instance.getClientRegistry());
    }

    /**
     * Test of getVehicleRequestRegistry method, of class ReceiveEmailController.
     */
    @Test
    public void testGetVehicleRequestRegistry() {
        System.out.println("getVehicleRequestRegistry");
        ReceiveEmailController instance = new ReceiveEmailController();
        VehicleRequestRegistry expResult = new VehicleRequestRegistry();
        VehicleRequestRegistry result = instance.getVehicleRequestRegistry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicleRequestRegistry method, of class ReceiveEmailController.
     */
    @Test
    public void testSetVehicleRequestRegistry() {
        System.out.println("setVehicleRequestRegistry");
        VehicleRequestRegistry vrr = new VehicleRequestRegistry();
        ReceiveEmailController instance = new ReceiveEmailController();
        instance.setVehicleRequestRegistry(vrr);
        assertEquals(vrr, instance.getVehicleRequestRegistry());
    }

    /**
     * Test of writeEmail method, of class ReceiveEmailController.
     */
    @Test
    public void testWriteEmail() throws Exception {
        System.out.println("writeEmail");
        rec.setClient(new Client("João Leal", "g22ridesharing@gmail.com", "pass", 19, 1.75, 75, 'M'));
        rec.setVehicleRequest(new VehicleRequest(123, "g22ridesharing@gmail.com", 123, 14, 17, Instant.now().minus(30, ChronoUnit.MINUTES).toString(), Instant.now().toString()));
        boolean expResult = true;
        boolean result = rec.writeEmail();
        assertEquals(expResult, result);
        rec.setClient(new Client("João Leal", "", "pass", 19, 1.75, 75, 'M'));
        expResult=false;
        result = rec.writeEmail();
        assertEquals(expResult, result);
    }
    
}
