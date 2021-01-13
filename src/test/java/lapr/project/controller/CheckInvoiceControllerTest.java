/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.data.InvoiceDB;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceRegistry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author joaol
 */
public class CheckInvoiceControllerTest {

    private CheckInvoiceController cic;

    public CheckInvoiceControllerTest() throws ParseException {
        cic = new CheckInvoiceController();
        Invoice inv = new Invoice(1, "0001-01-01", 1, 0, 14.99);
        InvoiceDB idb = mock(InvoiceDB.class);
        when(idb.getInvoice("joaolealmgs3@gmail.com")).thenReturn(inv);
        when(idb.getInvoice(1)).thenReturn(inv);
        when(idb.getInvoiceForMonth(1, "joaolealmgs3@gmail.com")).thenReturn(inv);
        when(idb.addInvoice(inv, "joaolealmgs3@gmail.com")).thenReturn(true);
        InvoiceRegistry ir = new InvoiceRegistry();
        ir.setInvoiceDB(idb);
        cic.setInvoiceRegistry(ir);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of setInvoiceRegistry method, of class CheckInvoiceController.
     */
    @Test
    public void testSetInvoiceRegistry() {
        System.out.println("setInvoiceRegistry");
        InvoiceRegistry ir = new InvoiceRegistry();
        CheckInvoiceController instance = new CheckInvoiceController();
        instance.setInvoiceRegistry(ir);
        assertEquals(ir, instance.ir);
    }

    /**
     * Test of getInvoice method, of class CheckInvoiceController.
     */
    @Test
    public void testGetInvoice_int() throws Exception {
        System.out.println("getInvoice");
        int id = 1;
        boolean expResult = true;
        boolean result = cic.getInvoice(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoice method, of class CheckInvoiceController.
     */
    @Test
    public void testGetInvoice_0args() throws ParseException {
        System.out.println("getInvoice");
        CheckInvoiceController instance = new CheckInvoiceController();
        Invoice expResult = new Invoice(1, "0001-01-01", 1, 0, 14.99);
        instance.inv = expResult;
        Invoice result = instance.getInvoice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoiceEmail method, of class CheckInvoiceController.
     */
    @Test
    public void testGetInvoiceEmail() throws Exception {
        System.out.println("getInvoiceEmail");
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = cic.getInvoiceEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoiceMonthEmail method, of class CheckInvoiceController.
     */
    @Test
    public void testGetInvoiceMonthEmail() throws Exception {
        System.out.println("getInvoiceMonthEmail");
        int month = 1;
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = cic.getInvoiceMonthEmail(month, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInvoice method, of class CheckInvoiceController.
     */
    @Test
    public void testAddInvoice() throws Exception {
        System.out.println("addInvoice");
        int id = 1;
        String date = "0001-01-01";
        int period = 1;
        int usedPoints = 0;
        double totalCost = 14.99;
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = cic.addInvoice(id, date, period, usedPoints, totalCost, email);
        assertEquals(expResult, result);
    }

}
