/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.Objects;
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
public class InvoiceTest {
    
    private Invoice inv;
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
    
    public InvoiceTest() throws ParseException {
        inv=new Invoice(123, "0001-01-01", 12, 2, 14.99);
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
     * Test of getInvId method, of class Invoice.
     */
    @Test
    public void testGetInvId() {
        System.out.println("getInvId");
        int expResult = 123;
        int result = inv.getInvId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setInvId method, of class Invoice.
     */
    @Test
    public void testSetInvId() {
        System.out.println("setInvId");
        int invId = 321;
        inv.setInvId(invId);
        assertEquals(invId, inv.getInvId());
    }

    /**
     * Test of getDate method, of class Invoice.
     */
    @Test
    public void testGetDate() throws ParseException {
        System.out.println("getDate");
        Date expResult = dt.parse("0001-01-01");
        Date result = inv.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Invoice.
     */
    @Test
    public void testSetDate() throws ParseException {
        System.out.println("setDate");
        Date date = dt.parse("2020-10-10");
        inv.setDate(date);
        assertEquals(date, inv.getDate());
    }

    /**
     * Test of getPeriod method, of class Invoice.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        Month expResult = Month.of(12);
        Month result = inv.getPeriod();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPeriod method, of class Invoice.
     */
    @Test
    public void testSetPeriod() {
        System.out.println("setPeriod");
        Month period = Month.of(3);
        inv.setPeriod(period);
        assertEquals(period, inv.getPeriod());
    }

    /**
     * Test of getUsedPoints method, of class Invoice.
     */
    @Test
    public void testGetUsedPoints() {
        System.out.println("getUsedPoints");
        int expResult = 2;
        int result = inv.getUsedPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsedPoints method, of class Invoice.
     */
    @Test
    public void testSetUsedPoints() {
        System.out.println("setUsedPoints");
        int usedPoints = 30;
        inv.setUsedPoints(usedPoints);
        assertEquals(usedPoints, inv.getUsedPoints());
    }

    /**
     * Test of getTotalCost method, of class Invoice.
     */
    @Test
    public void testGetTotalCost() {
        System.out.println("getTotalCost");
        double expResult = 14.99;
        double result = inv.getTotalCost();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotalCost method, of class Invoice.
     */
    @Test
    public void testSetTotalCost() {
        System.out.println("setTotalCost");
        double totalCost = 19.99;
        inv.setTotalCost(totalCost);
        assertEquals(totalCost, inv.getTotalCost(), 0.0);
    }
    
    /**
     * Test of getDateString method, of class Invoice.
     */
    @Test
    public void testGetDateString() throws ParseException {
        System.out.println("getDateString");
        Invoice instance = new Invoice(123, "2020-10-10", 1, 0, 0);
        String expResult = "2020-10-10";
        String result = instance.getDateString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Invoice.
     */
    @Test
    public void testHashCode() throws ParseException {
        System.out.println("hashCode");
        int invId=123;
        String dates="0001-01-01";
        int periodi=12;
        int usedPoints=2;
        double totalCost=14.99;
        Invoice instance=new Invoice(invId, dates, periodi, usedPoints, totalCost);
        Date date=dt.parse(dates);
        Month period=Month.of(periodi);
        int hash = 7;
        hash = 37 * hash + invId;
        hash = 37 * hash + Objects.hashCode(date.toString());
        hash = 37 * hash + period.getValue();
        hash = 37 * hash + usedPoints;
        hash = 37 * hash + (int) (Double.doubleToLongBits(totalCost) ^ (Double.doubleToLongBits(totalCost) >>> 32));
        int expResult = hash;
        int result = inv.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEquals() throws ParseException {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = inv.equals(obj);
        assertEquals(expResult, result);
        obj = new Invoice(123, "0001-01-01", 12, 0, 14.99);
        expResult = true;
        result = inv.equals(obj);
        assertEquals(expResult, result);
        obj = new InvoiceLineRegistry();
        expResult = false;
        result = inv.equals(obj);
        assertEquals(expResult, result);
        obj = new Invoice(87654321, "0001-01-01", 12, 0, 14.99);
        expResult = false;
        result = inv.equals(obj);
        assertEquals(expResult, result);
        obj = inv;
        expResult = true;
        result = inv.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Invoice.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Invoice ID: 123\n" +
"    -Date of payment = 0001-01-01\n" +
"    -Period (Month of) = DECEMBER\n" +
"    Total of points used = 2\n" +
"    Total cost = 14.99";
        String result = inv.toString();
        assertEquals(expResult, result);
    }
    
}
