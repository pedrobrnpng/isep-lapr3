/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import java.util.HashSet;
import lapr.project.data.ClientDB;
import lapr.project.data.CreditCardDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.InvoiceLineDB;
import lapr.project.model.Client;
import lapr.project.model.ClientRegistry;
import lapr.project.model.CreditCard;
import lapr.project.model.CreditCardRegistry;
import lapr.project.model.Invoice;
import lapr.project.model.InvoiceLine;
import lapr.project.model.InvoiceLineRegistry;
import lapr.project.model.InvoiceRegistry;
import lapr.project.utils.exceptions.CreditCardExpiredException;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;
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
public class PayMonthlyFeeControllerTest {
    
    private PayMonthlyFeeController pmfc;
    
    public PayMonthlyFeeControllerTest() throws InvalidInfoClientException, ParseException, CreditCardExpiredException, InvalidInvoiceLineCostException {
        pmfc=new PayMonthlyFeeController();
        
        Client cli=new Client("João Leal", "joaolealmgs3@gmail.com", 123, 19, 1.75, 75, 'M', 0, 25);
        ClientDB cdb=mock(ClientDB.class);
        when(cdb.getClient("joaolealmgs3@gmail.com")).thenReturn(cli);
        when(cdb.updateClient(cli)).thenReturn(true);
        ClientRegistry cr=new ClientRegistry();
        cr.setClientDB(cdb);
        pmfc.setCr(cr);
        
        CreditCard cc=new CreditCard(12345678, "2020-10-10", 60);
        CreditCardDB ccdb=mock(CreditCardDB.class);
        when(ccdb.getCreditCard("joaolealmgs3@gmail.com")).thenReturn(cc);
        when(ccdb.deductCost(cc, Math.round(18.99 * 1e2) / 1e2)).thenReturn(true);
        CreditCardRegistry ccr=new CreditCardRegistry();
        ccr.setCreditCardDB(ccdb);
        pmfc.setCcr(ccr);
        
        Invoice inv=new Invoice(123, "0001-01-01", 12, 0, 14.99);
        InvoiceDB idb=mock(InvoiceDB.class);
        when(idb.getInvoice("joaolealmgs3@gmail.com")).thenReturn(inv);
        when(idb.updateInvoice(inv)).thenReturn(true);
        InvoiceRegistry ir=new InvoiceRegistry();
        ir.setInvoiceDB(idb);
        pmfc.setIr(ir);
        
        HashSet<InvoiceLine> ilList=new HashSet<>();
        ilList.add(new InvoiceLine(123, 123, 2));
        ilList.add(new InvoiceLine(123, 123, 4));
        ilList.add(new InvoiceLine(123, 123, 0));
        InvoiceLineDB ildb=mock(InvoiceLineDB.class);
        when(ildb.getAllInvoiceLinesOfInvoice(123)).thenReturn(ilList);
        InvoiceLineRegistry ilr=new InvoiceLineRegistry();
        ilr.setInvoiceLineDB(ildb);
        pmfc.setIlr(ilr);
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
     * Test of getIr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testGetIr() {
        System.out.println("getIr");
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        InvoiceRegistry expResult = new InvoiceRegistry();
        InvoiceRegistry result = instance.getIr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testSetIr() {
        System.out.println("setIr");
        InvoiceRegistry ir = new InvoiceRegistry();
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        instance.setIr(ir);
        assertEquals(ir, instance.getIr());
    }

    /**
     * Test of getIlr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testGetIlr() {
        System.out.println("getIlr");
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        InvoiceLineRegistry expResult = new InvoiceLineRegistry();
        InvoiceLineRegistry result = instance.getIlr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIlr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testSetIlr() {
        System.out.println("setIlr");
        InvoiceLineRegistry ilr = new InvoiceLineRegistry();
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        instance.setIlr(ilr);
        assertEquals(ilr, instance.getIlr());
    }

    /**
     * Test of getCcr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testGetCcr() {
        System.out.println("getCcr");
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        CreditCardRegistry expResult = new CreditCardRegistry();
        CreditCardRegistry result = instance.getCcr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCcr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testSetCcr() {
        System.out.println("setCcr");
        CreditCardRegistry ccr = new CreditCardRegistry();
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        instance.setCcr(ccr);
        assertEquals(ccr, instance.getCcr());
    }
    
    /**
     * Test of getCr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testGetCr() {
        System.out.println("getCr");
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        ClientRegistry expResult = new ClientRegistry();
        ClientRegistry result = instance.getCr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCr method, of class PayMonthlyFeeController.
     */
    @Test
    public void testSetCr() {
        System.out.println("setCr");
        ClientRegistry cr = new ClientRegistry();
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        instance.setCr(cr);
        assertEquals(cr, instance.getCr());
    }

    /**
     * Test of payMonthlyFee method, of class PayMonthlyFeeController.
     */
    @Test
    public void testPayMonthlyFee() throws Exception {
        System.out.println("payMonthlyFee");
        String email = "joaolealmgs3@gmail.com";
        boolean expResult = true;
        boolean result = pmfc.payMonthlyFee(email);
        assertEquals(expResult, result);
        email="s";
        expResult=false;
        result = pmfc.payMonthlyFee(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateTotalCost method, of class PayMonthlyFeeController.
     */
    @Test
    public void testCalculateTotalCost() {
        System.out.println("calculateTotalCost");
        double totalCost = 20.99;
        int points = 25;
        PayMonthlyFeeController instance = new PayMonthlyFeeController();
        int expResult = 2;
        int result = instance.calculateTotalCost(totalCost, points);
        assertEquals(expResult, result);
        totalCost=0.50;
        expResult=0;
        result = instance.calculateTotalCost(totalCost, points);
        assertEquals(expResult, result);
        totalCost=20.99;
        points=9;
        result = instance.calculateTotalCost(totalCost, points);
        assertEquals(expResult, result);
        totalCost=1;
        points=30;
        expResult=1;
        result = instance.calculateTotalCost(totalCost, points);
        assertEquals(expResult, result);
    }
    
}
