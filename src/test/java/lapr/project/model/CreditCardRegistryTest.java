/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Objects;
import lapr.project.data.CreditCardDB;
import lapr.project.utils.exceptions.CreditCardExpiredException;
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
public class CreditCardRegistryTest {
    
    CreditCardRegistry ccr;
    
    public CreditCardRegistryTest() throws ParseException, CreditCardExpiredException {
        CreditCard cc1=new CreditCard(12345678, "2020-10-10", 50);
        HashSet<CreditCard> creditCardList= new HashSet<>();
        creditCardList.add(new CreditCard(12345678, "2020-10-10", 50));
        creditCardList.add(new CreditCard(87654321, "2020-10-10", 50));
        ccr = new CreditCardRegistry();
        ccr.setCreditCardList(creditCardList);
        CreditCardDB ccdb=mock(CreditCardDB.class);
        when(ccdb.deductCost(cc1, 5)).thenReturn(true);
        when(ccdb.getCreditCard(12345678)).thenReturn(cc1);
        when(ccdb.getCreditCard("joaolealmgs3@gmail.com")).thenReturn(cc1);
        ccr.setCreditCardDB(ccdb);
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
     * Test of getCreditCards method, of class CreditCardRegistry.
     */
    @Test
    public void testGetCreditCards() throws ParseException, CreditCardExpiredException {
        System.out.println("getCreditCards");
        HashSet<CreditCard> expResult = new HashSet<>();
        expResult.add(new CreditCard(12345678, "2020-10-10", 50));
        expResult.add(new CreditCard(87654321, "2020-10-10", 50));
        HashSet<CreditCard> result = ccr.getCreditCards();
        assertEquals(expResult, result);
    }

    /**
     * Test of newCreditCard method, of class CreditCardRegistry.
     */
    @Test
    public void testNewCreditCard() throws Exception {
        System.out.println("newCreditCard");
        int tin=23456789;
        String expDate="2020-10-10";
        double credits=50;
        CreditCard expResult = new CreditCard(23456789, "2020-10-10", 50);
        CreditCard result = ccr.newCreditCard(tin, expDate, credits);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateCreditCard method, of class CreditCardRegistry.
     */
    @Test
    public void testValidateCreditCard() throws ParseException, CreditCardExpiredException {
        System.out.println("validateCreditCard");
        CreditCard creditCard = new CreditCard(12345678, "2029-10-10", 45);
        HashSet<CreditCard> ccList=new HashSet<>();
        CreditCardRegistry instance=new CreditCardRegistry();
        instance.setCreditCardList(ccList);
        boolean expResult = true;
        boolean result = instance.validateCreditCard(creditCard);
        assertEquals(expResult, result);
        ccList.add(creditCard);
        instance=new CreditCardRegistry();
        instance.setCreditCardList(ccList);
        expResult=false;
        result=instance.validateCreditCard(creditCard);
        assertEquals(expResult, result);
        creditCard=new CreditCard(87654321, "2020-10-10", 45);
        expResult=true;
        result=instance.validateCreditCard(creditCard);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setCreditCardList method, of class CreditCardRegistry.
     */
    @Test
    public void testSetCreditCardList() {
        System.out.println("setCreditCardList");
        HashSet<CreditCard> creditCardList = new HashSet<>();
        CreditCardRegistry instance = new CreditCardRegistry();
        instance.setCreditCardList(creditCardList);
        assertEquals(creditCardList, instance.getCreditCards());
    }

    /**
     * Test of setCreditCardDB method, of class CreditCardRegistry.
     */
    @Test
    public void testSetCreditCardDB() {
        System.out.println("setCreditCardDB");
        CreditCardDB ccdb = new CreditCardDB();
        CreditCardRegistry instance = new CreditCardRegistry();
        instance.setCreditCardDB(ccdb);
        assertEquals(ccdb, instance.getCreditCardDB());
    }

    /**
     * Test of getCreditCardDB method, of class CreditCardRegistry.
     */
    @Test
    public void testGetCreditCardDB() {
        System.out.println("getCreditCardDB");
        CreditCardRegistry instance = new CreditCardRegistry();
        CreditCardDB expResult = new CreditCardDB();
        instance.setCreditCardDB(expResult);
        CreditCardDB result = instance.getCreditCardDB();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditCard method, of class CreditCardRegistry.
     */
    @Test
    public void testGetCreditCard_int() throws Exception {
        System.out.println("getCreditCard");
        int tin = 12345678;
        CreditCard expResult = new CreditCard(12345678, "2020-10-10", 50);
        CreditCard result = ccr.getCreditCard(tin);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreditCard method, of class CreditCardRegistry.
     */
    @Test
    public void testGetCreditCard_String() throws Exception {
        System.out.println("getCreditCard");
        String email = "joaolealmgs3@gmail.com";
        CreditCard expResult = new CreditCard(12345678, "2020-10-10", 50);;
        CreditCard result = ccr.getCreditCard(email);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateCreditCard method, of class CreditCardRegistry.
     */
    @Test
    public void testUpdateCreditCard() throws ParseException, CreditCardExpiredException {
        System.out.println("updateCreditCard");
        CreditCard cc = new CreditCard(12345678, "2020-10-10", 50);
        double cost = 5;
        boolean expResult = true;
        boolean result = ccr.updateCreditCard(cc, cost);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class CreditCardRegistry.
     */
    @Test
    public void testHashCode() throws ParseException, CreditCardExpiredException {
        System.out.println("hashCode");
        HashSet<CreditCard> creditCardList=new HashSet<>();
        creditCardList.add(new CreditCard(123, "2020-10-10", 3));
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(creditCardList);
        int expResult = hash;
        CreditCardRegistry instance=new CreditCardRegistry();
        instance.newCreditCard(123, "2020-10-10", 3);
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class CreditCardRegistry.
     */
    @Test
    public void testEquals() throws Exception {
        System.out.println("equals");
        Object obj = null;
        CreditCardRegistry instance = new CreditCardRegistry();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj = new CreditCardRegistry();
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        obj = instance;
        expResult=true;
        result=instance.equals(obj);
        assertEquals(expResult, result);
        CreditCard obj2 = new CreditCard(12345678, "2020-10-10", 2);
        expResult=false;
        result=instance.equals(obj2);
        assertEquals(expResult, result);
    }
    
}
