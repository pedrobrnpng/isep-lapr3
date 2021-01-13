/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lapr.project.data.InvoiceDB;
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
public class InvoiceRegistryTest {
    
    private InvoiceRegistry ir;
    
    public InvoiceRegistryTest() throws ParseException {
        ir=new InvoiceRegistry();
        InvoiceDB idb=mock(InvoiceDB.class);
        when(idb.updateInvoice(new Invoice(123, "2020-10-10", 1, 0, 0))).thenReturn(true);
        HashSet<Invoice> iList=new HashSet<>();
        iList.add(new Invoice(123, "2020-10-10", 1, 0, 0));
        iList.add(new Invoice(321, "2020-10-10", 1, 0, 0));
        iList.add(new Invoice(132, "2020-10-10", 1, 0, 0));
        when(idb.getAllInvoices()).thenReturn(iList);
        when(idb.getInvoice(123)).thenReturn(new Invoice(123, "2020-10-10", 1, 0, 0));
        when(idb.getInvoice("joaolealmgs3@gmail.com")).thenReturn(new Invoice(123, "2020-10-10", 1, 0, 0));
        when(idb.getAllInvoices("joaolealmgs3@gmail.com")).thenReturn(iList);
        when(idb.getInvoiceForMonth(1, "joaolealmgs3@gmail.com")).thenReturn(new Invoice(123, "2020-10-10", 1, 0, 0));
        when(idb.addInvoice(new Invoice(123, "2020-10-10", 1, 0, 0), "joaolealmgs3@gmail.com")).thenReturn(true);
        ir.setInvoiceDB(idb);
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
     * Test of setInvoiceList method, of class InvoiceRegistry.
     */
    @Test
    public void testSetInvoiceList() {
        System.out.println("setInvoiceList");
        HashSet<Invoice> invoiceList = new HashSet<>();
        InvoiceRegistry instance = new InvoiceRegistry();
        instance.setInvoiceList(invoiceList);
        assertEquals(invoiceList, instance.getInvoiceList());
    }

    /**
     * Test of setInvoiceDB method, of class InvoiceRegistry.
     */
    @Test
    public void testSetInvoiceDB() {
        System.out.println("setInvoiceDB");
        InvoiceDB idb = new InvoiceDB();
        InvoiceRegistry instance = new InvoiceRegistry();
        instance.setInvoiceDB(idb);
        assertEquals(idb, instance.getInvoiceDB());
    }

    /**
     * Test of getInvoiceList method, of class InvoiceRegistry.
     */
    @Test
    public void testGetInvoiceList() throws ParseException {
        System.out.println("getInvoiceList");
        HashSet<Invoice> expResult = new HashSet<>();
        InvoiceRegistry instance = new InvoiceRegistry();
        HashSet<Invoice> result = ir.getInvoiceList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoiceDB method, of class InvoiceRegistry.
     */
    @Test
    public void testGetInvoiceDB() {
        System.out.println("getInvoiceDB");
        InvoiceRegistry instance = new InvoiceRegistry();
        InvoiceDB expResult = new InvoiceDB();
        instance.setInvoiceDB(expResult);
        InvoiceDB result = instance.getInvoiceDB();
        assertEquals(expResult, result);
    }

    /**
     * Test of newInvoice method, of class InvoiceRegistry.
     */
    @Test
    public void testNewInvoice() throws Exception {
        System.out.println("newInvoice");
        int invId = 321;
        String date = "2020-10-10";
        int period = 1;
        int usedPoints = 0;
        double totalCost = 0.0;
        InvoiceRegistry instance = new InvoiceRegistry();
        Invoice expResult = new Invoice(invId, date, period, usedPoints, totalCost);
        Invoice result = instance.newInvoice(invId, date, period, usedPoints, totalCost);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateInvoice method, of class InvoiceRegistry.
     */
    @Test
    public void testUpdateInvoice() throws ParseException {
        System.out.println("updateInvoice");
        Invoice inv = new Invoice(123, "2020-10-10", 1, 0, 0);
        boolean expResult = true;
        boolean result = ir.updateInvoice(inv);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoice method, of class InvoiceRegistry.
     */
    @Test
    public void testGetInvoice() throws Exception {
        System.out.println("getInvoice");
        String email = "joaolealmgs3@gmail.com";
        Invoice expResult = new Invoice(123, "2020-10-10", 1, 0, 0);
        Invoice result = ir.getInvoice(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class InvoiceRegistry.
     */
    @Test
    public void testHashCode() throws ParseException {
        System.out.println("hashCode");
        InvoiceRegistry instance = new InvoiceRegistry();
        instance.newInvoice(123, "2020-10-10", 1, 0, 0);
        HashSet<Invoice> invoiceList=new HashSet<>();
        invoiceList.add(new Invoice(123, "2020-10-10", 1, 0, 0));
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(invoiceList);
        int expResult = hash;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class InvoiceRegistry.
     */
    @Test
    public void testEquals() throws ParseException {
        System.out.println("equals");
        Object obj = null;
        InvoiceRegistry instance = new InvoiceRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = instance;
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj = new Invoice(123, "2020-10-10", 1, 0, 0);
        expResult=false;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj=new InvoiceRegistry();
        instance.newInvoice(123, "2020-10-10", 1, 0, 0);
        expResult=false;
        result=instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoiceList method, of class InvoiceRegistry.
     */
    @Test
    public void testGetInvoiceList_String() throws Exception {
        System.out.println("getInvoiceList");
        String email = "joaolealmgs3@gmail.com";
        Set<Invoice> expResult = new HashSet<>();
        expResult.add(new Invoice(123, "2020-10-10", 1, 0, 0));
        expResult.add(new Invoice(321, "2020-10-10", 1, 0, 0));
        expResult.add(new Invoice(132, "2020-10-10", 1, 0, 0));
        Set<Invoice> result = ir.getInvoiceList(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvoiceMonthEmail method, of class InvoiceRegistry.
     */
    @Test
    public void testGetInvoiceMonthEmail() throws Exception {
        System.out.println("getInvoiceMonthEmail");
        int month = 1;
        String email = "joaolealmgs3@gmail.com";
        Invoice expResult = new Invoice(123, "2020-10-10", 1, 0, 0);
        Invoice result = ir.getInvoiceMonthEmail(month, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInvoice method, of class InvoiceRegistry.
     */
    @Test
    public void testAddInvoice() throws ParseException {
        System.out.println("addInvoice");
        Invoice invoice = new Invoice(123, "2020-10-10", 1, 0, 0);
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = ir.addInvoice(invoice, email);
        assertEquals(expResult, result);
    }
    
}
