/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Objects;
import lapr.project.data.InvoiceLineDB;
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
public class InvoiceLineRegistryTest {
    
    private InvoiceLineRegistry ilr;
    
    public InvoiceLineRegistryTest() throws InvalidInvoiceLineCostException {
        ilr=new InvoiceLineRegistry();
        InvoiceLineDB ildb=mock(InvoiceLineDB.class);
        when(ildb.addInvoiceLine(1, 1, 50)).thenReturn(true);
        HashSet<InvoiceLine> ilList=new HashSet<>();
        ilList.add(new InvoiceLine(1,1,50));
        ilList.add(new InvoiceLine(1,2,50));
        ilList.add(new InvoiceLine(1,3,50));
        when(ildb.getAllInvoiceLinesOfInvoice(1)).thenReturn(ilList);
        when(ildb.getInvoiceLine(1, 1)).thenReturn(new InvoiceLine(1, 1, 50));
        ilr.setInvoiceLineDB(ildb);
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
     * Test of setInvLlist method, of class InvoiceLineRegistry.
     */
    @Test
    public void testSetInvLlist() {
        System.out.println("setInvLlist");
        HashSet<InvoiceLine> invLlist = new HashSet<>();
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        instance.setInvLlist(invLlist);
        assertEquals(invLlist, instance.getInvLlist());
    }

    /**
     * Test of setInvoiceLineDB method, of class InvoiceLineRegistry.
     */
    @Test
    public void testSetInvoiceLineDB() {
        System.out.println("setInvoiceLineDB");
        InvoiceLineDB invLdb = new InvoiceLineDB();
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        instance.setInvoiceLineDB(invLdb);
        assertEquals(invLdb, instance.getInvoiceLineDB());
    }

    /**
     * Test of getInvoiceLineDB method, of class InvoiceLineRegistry.
     */
    @Test
    public void testGetInvoiceLineDB() {
        System.out.println("getInvoiceLineDB");
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        InvoiceLineDB expResult = new InvoiceLineDB();
        instance.setInvoiceLineDB(expResult);
        InvoiceLineDB result = instance.getInvoiceLineDB();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInvLlist method, of class InvoiceLineRegistry.
     */
    @Test
    public void testGetInvLlist() {
        System.out.println("getInvLlist");
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        HashSet<InvoiceLine> expResult = new HashSet<>();
        HashSet<InvoiceLine> result = instance.getInvLlist();
        assertEquals(expResult, result);
    }

    /**
     * Test of newInvoiceLine method, of class InvoiceLineRegistry.
     */
    @Test
    public void testNewInvoiceLine() throws Exception {
        System.out.println("newInvoiceLine");
        int invId = 0;
        int vrId = 0;
        double cost = 0.0;
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        InvoiceLine expResult = new InvoiceLine(invId, vrId, cost);
        InvoiceLine result = instance.newInvoiceLine(invId, vrId, cost);
        assertEquals(expResult, result);
    }

    /**
     * Test of addInvoiceLine method, of class InvoiceLineRegistry.
     */
    @Test
    public void testAddInvoiceLine() throws InvalidInvoiceLineCostException {
        System.out.println("addInvoiceLine");
        InvoiceLine invL = new InvoiceLine(1, 1, 50);
        boolean expResult = true;
        boolean result = ilr.addInvoiceLine(invL);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAllInvLinesOfInv method, of class InvoiceLineRegistry.
     */
    @Test
    public void testGetAllInvLinesOfInv() throws Exception {
        System.out.println("getAllInvLinesOfInv");
        int invId = 1;
        HashSet<InvoiceLine> expResult = new HashSet<>();
        expResult.add(new InvoiceLine(1,1,50));
        expResult.add(new InvoiceLine(1,2,50));
        expResult.add(new InvoiceLine(1,3,50));
        HashSet<InvoiceLine> result = ilr.getAllInvLinesOfInv(invId);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class InvoiceLineRegistry.
     */
    @Test
    public void testHashCode() throws InvalidInvoiceLineCostException {
        System.out.println("hashCode");
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        instance.newInvoiceLine(1, 1, 50);
        HashSet<InvoiceLine> invLlist=new HashSet<>();
        invLlist.add(new InvoiceLine(1, 1, 50));
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(invLlist);
        int expResult = hash;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class InvoiceLineRegistry.
     */
    @Test
    public void testEquals() throws InvalidInvoiceLineCostException {
        System.out.println("equals");
        Object obj = null;
        InvoiceLineRegistry instance = new InvoiceLineRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new InvoiceLine(1, 1, 50);
        expResult=false;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj=instance;
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        instance.newInvoiceLine(1, 1, 50);
        InvoiceLineRegistry obj2=new InvoiceLineRegistry();
        expResult=false;
        result=instance.equals(obj2);
        assertEquals(expResult, result);
    }
    
}
