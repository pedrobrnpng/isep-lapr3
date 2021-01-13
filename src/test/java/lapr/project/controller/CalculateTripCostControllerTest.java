/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lapr.project.data.InvoiceDB;
import lapr.project.data.InvoiceLineDB;
import lapr.project.data.VehicleRequestsDB;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceLineRegistry;
import lapr.project.model.InvoiceRegistry;
import lapr.project.model.VehicleRequest;
import lapr.project.model.VehicleRequestRegistry;
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
public class CalculateTripCostControllerTest {
    
    private CalculateTripCostController ctcc;
    private VehicleRequestRegistry vrr;
    private InvoiceRegistry ir;
    private InvoiceLineRegistry ilr;
    private VehicleRequest vr;
    
    public CalculateTripCostControllerTest() throws ParseException {
        ctcc=new CalculateTripCostController();
        VehicleRequestsDB vrdb=mock(VehicleRequestsDB.class);
        Instant inst=Instant.parse("2020-10-10T10:35:00.00Z");
        String time= inst.toString();
        String time2 = inst.plus(2, ChronoUnit.HOURS).toString();
        vr= new VehicleRequest(1, "joaolealmgs3@gmail.com", 1, 50, 50, time, time2);
        when(vrdb.getVehicleRequest(1)).thenReturn(new VehicleRequest(1, "joaolealmgs3@gmail.com", 1, 50, 50,  time, time2));
        vrr=new VehicleRequestRegistry(vrdb);
        InvoiceDB idb=mock(InvoiceDB.class);
        when(idb.getInvoice("joaolealmgs3@gmail.com")).thenReturn(new Invoice(1,"0001-01-01", 1, 0, 14.99));
        ir=new InvoiceRegistry();
        ir.setInvoiceDB(idb);
        InvoiceLineDB ildb=mock(InvoiceLineDB.class);
        when(ildb.addInvoiceLine(1, 1, 3)).thenReturn(true);
        ilr=new InvoiceLineRegistry();
        ilr.setInvoiceLineDB(ildb);
        ctcc.setVehicleRequestRegistry(vrr);
        ctcc.setInvoiceRegistry(ir);
        ctcc.setInvoiceLineRegistry(ilr);
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
     * Test of setVehicleRequestRegistry method, of class CalculateTripCostController.
     */
    @Test
    public void testSetVehicleRequestRegistry() {
        System.out.println("setVehicleRequestRegistry");
        VehicleRequestRegistry vrr1 = new VehicleRequestRegistry();
        ctcc.setVehicleRequestRegistry(vrr1);
        assertEquals(vrr1, ctcc.vrr);
    }
    
    /**
     * Test of getVehicleRequest method, of class CalculateTripCostController.
     */
    @Test
    public void testGetVehicleRequest() {
        System.out.println("getVehicleRequest");
        int vrId = 1;
        ctcc.getVehicleRequest(vrId);
        assertEquals(vr, ctcc.vr);
    }

    /**
     * Test of setInvoiceRegistry method, of class CalculateTripCostController.
     */
    @Test
    public void testSetInvoiceRegistry() {
        System.out.println("setInvoiceRegistry");
        InvoiceRegistry ir1 = new InvoiceRegistry();
        ctcc.setInvoiceRegistry(ir1);
        assertEquals(ir1, ctcc.ir);
    }

    /**
     * Test of setInvoiceLineRegistry method, of class CalculateTripCostController.
     */
    @Test
    public void testSetInvoiceLineRegistry() {
        System.out.println("setInvoiceLineRegistry");
        InvoiceLineRegistry ilr1 = new InvoiceLineRegistry();
        ctcc.setInvoiceLineRegistry(ilr1);
        assertEquals(ilr1, ctcc.ilr);
    }

    /**
     * Test of calculateCost method, of class CalculateTripCostController.
     */
    @Test
    public void testCalculateCost() throws Exception {
        System.out.println("calculateCost");
        double expResult = 3;
        double result = ctcc.calculateCost(1);
        assertEquals(expResult, result, 0.0);
    }
    
}
