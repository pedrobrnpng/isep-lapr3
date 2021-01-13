/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.exceptions.InvalidInvoiceLineCostException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaol
 */
public class InvoiceLineTest {
    
    private InvoiceLine invL;
    
    public InvoiceLineTest() throws InvalidInvoiceLineCostException {
        invL=new InvoiceLine(1, 1, 50);
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
     * Test of getCost method, of class InvoiceLine.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        double expResult = 50;
        double result = invL.getCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCost method, of class InvoiceLine.
     */
    @Test
    public void testSetCost() throws InvalidInvoiceLineCostException {
        System.out.println("setCost");
        double cost = 0.0;
        invL.setCost(cost);
        assertEquals(cost, invL.getCost(), 0.0);
        try {
            invL.setCost(-1);
        } catch (InvalidInvoiceLineCostException iilce) {
            assertTrue(true);
        }
        try {
            InvoiceLine invL2= new InvoiceLine(1, 1, -1);
        } catch (InvalidInvoiceLineCostException iilce) {
            assertTrue(true);
        }
    }
    
    /**
     * Test of getInvId method, of class InvoiceLine.
     */
    @Test
    public void testGetInvId() {
        System.out.println("getInvId");
        int expResult = 1;
        int result = invL.getInvId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvId method, of class InvoiceLine.
     */
    @Test
    public void testSetInvId() {
        System.out.println("setInvId");
        int invId = 0;
        invL.setInvId(invId);
        assertEquals(invId, invL.getInvId());
    }

    /**
     * Test of getVrId method, of class InvoiceLine.
     */
    @Test
    public void testGetVrId() {
        System.out.println("getVrId");
        int expResult = 1;
        int result = invL.getVrId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVrId method, of class InvoiceLine.
     */
    @Test
    public void testSetVrId() {
        System.out.println("setVrId");
        int vrId = 0;
        invL.setVrId(vrId);
        assertEquals(vrId, invL.getVrId());
    }
    
    /**
     * Test of hashCode method, of class InvoiceLine.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 1079967145;
        int result = invL.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class InvoiceLine.
     */
    @Test
    public void testEquals() throws InvalidInvoiceLineCostException {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = invL.equals(obj);
        assertEquals(expResult, result);
        obj = new InvoiceLine(1,1,50);
        expResult = true;
        result = invL.equals(obj);
        assertEquals(expResult, result);
        obj = new InvoiceLineRegistry();
        expResult = false;
        result = invL.equals(obj);
        assertEquals(expResult, result);
        obj = new InvoiceLine(1,2,50);
        expResult = false;
        result = invL.equals(obj);
        assertEquals(expResult, result);
        obj = new InvoiceLine(2,1,50);
        expResult = false;
        result = invL.equals(obj);
        assertEquals(expResult, result);
        obj = invL;
        expResult = true;
        result = invL.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class InvoiceLine.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Invoice ID: 1\nVehicle request ID: 1\nCost of invoice line = 50.0";
        String result = invL.toString();
        assertEquals(expResult, result);
    }
    
}
