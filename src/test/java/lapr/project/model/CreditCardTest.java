/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.exceptions.CreditCardExpiredException;
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
public class CreditCardTest {

    private CreditCard cc;
    private final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

    public CreditCardTest() throws ParseException, CreditCardExpiredException {
        cc = new CreditCard(12345678, "2020-10-10", 50);
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
     * Test of getTin method, of class CreditCard.
     */
    @Test
    public void testGetTin() {
        System.out.println("getTin");
        int expResult = 12345678;
        int result = cc.getTin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTin method, of class CreditCard.
     */
    @Test
    public void testSetTin() {
        System.out.println("setTin");
        int tin = 87654321;
        cc.setTin(tin);
        int expResult = tin;
        int result = cc.getTin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExpDate method, of class CreditCard.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testGetExpDate() throws ParseException {
        System.out.println("getExpDate");
        Date expResult = dt.parse("2020-10-10");
        Date result = cc.getExpDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExpDate method, of class CreditCard.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetExpDate_Date() throws ParseException {
        System.out.println("setExpDate");
        Date expDate = dt.parse("2020-10-11");
        cc.setExpDate(expDate);
        Date result = cc.getExpDate();
        assertEquals(expDate, result);
    }

    /**
     * Test of getExpDateString method, of class CreditCard.
     */
    @Test
    public void testGetExpDateString() {
        System.out.println("getExpDateString");
        String expResult = "2020-10-10";
        String result = cc.getExpDateString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExpDate method, of class CreditCard.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetExpDate_String() throws ParseException {
        System.out.println("setExpDate");
        String expDates = "2020-10-11";
        cc.setExpDate(expDates);
        Date expDate = dt.parse("2020-10-11");
        Date result = cc.getExpDate();
        assertEquals(expDate, result);
    }

    /**
     * Test of getCredits method, of class CreditCard.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        double expResult = 50;
        double result = cc.getCredits();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setCredits method, of class CreditCard.
     */
    @Test
    public void testSetCredits() {
        System.out.println("setCredits");
        double credits = 55;
        cc.setCredits(credits);
    }

    /**
     * Test of deductCredits method, of class CreditCard.
     */
    @Test
    public void testDeductCredits() {
        System.out.println("deductCredits");
        double cost = 5;
        boolean expResult = true;
        boolean result = cc.deductCredits(cost);
        assertEquals(expResult, result);
    }

    /**
     * Test of deductCredits method, of class CreditCard.
     */
    @Test
    public void testDeductCredits2() {
        try {
            System.out.println("deductCredits");
            double cost = 40;
            boolean expResult = true;
            boolean result = new CreditCard(11111111, "2023-10-10", 40).deductCredits(cost);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            Logger.getLogger(CreditCardTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CreditCardExpiredException ex) {
            Logger.getLogger(CreditCardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deductCredits method, of class CreditCard.
     */
    @Test
    public void testDeductCredits3() {
        try {
            System.out.println("deductCredits");
            double cost = 50;
            boolean expResult = false;
            boolean result = new CreditCard(11111111, "2023-10-10", 40).deductCredits(cost);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            Logger.getLogger(CreditCardTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CreditCardExpiredException ex) {
            Logger.getLogger(CreditCardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCredits method, of class CreditCard.
     */
    @Test
    public void testAddCredits() {
        System.out.println("addCredits");
        double dp = 10;
        cc.addCredits(dp);
        double expResult = 60;
        double result = cc.getCredits();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of isExpired method, of class CreditCard.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testIsExpired() throws ParseException {
        System.out.println("isExpired");
        boolean expResult = false;
        boolean result = cc.isExpired();
        assertEquals(expResult, result);
        cc.setExpDate(dt.parse("2018-10-10"));
        expResult = true;
        result = cc.isExpired();
        assertEquals(expResult, result);
        cc.setExpDate(new Date());
        expResult = false;
        result = cc.isExpired();
        assertEquals(expResult, result);
        CreditCard cc2;
        try {
            cc2 = new CreditCard(12345678, "2018-10-10", 50);
        } catch (CreditCardExpiredException ccee) {
            assertTrue(true);
        }
    }

    /**
     * Test of hashCode method, of class CreditCard.
     */
    @Test
    public void testHashCode() throws Exception {
        System.out.println("hashCode");
        int tin=12345678;
        String expDates="2020-10-10";
        CreditCard x = new CreditCard(tin, expDates, 50);
        Date expDate=dt.parse(expDates);
        int hash = 7;
        hash = 71 * hash + tin ^ tin >>> 14;
        hash = 71 * hash + (Objects.hashCode(expDate.toString()) ^ Objects.hashCode(expDate.toString()) >>> 14);
        int expResult=hash;
        int result=x.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CreditCard.
     */
    @Test
    public void testEquals() throws Exception {
        System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = cc.equals(obj);
        assertEquals(expResult, result);
        obj = new CreditCard(12345678, "2020-10-10", 50);
        expResult = true;
        result = cc.equals(obj);
        assertEquals(expResult, result);
        obj = new CreditCardRegistry();
        expResult = false;
        result = cc.equals(obj);
        assertEquals(expResult, result);
        obj = new CreditCard(87654321, "2020-10-10", 50);
        expResult = false;
        result = cc.equals(obj);
        assertEquals(expResult, result);
        obj = cc;
        expResult = true;
        result = cc.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CreditCard.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "TIN =12345678\nExpiration date = 2020-10-10\nCredits = 50.0";
        String result = cc.toString();
        System.out.println(result);
        assertEquals(expResult, result);
    }

}
