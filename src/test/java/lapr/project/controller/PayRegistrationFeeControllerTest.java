/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.data.CreditCardDB;
import lapr.project.model.CreditCard;
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
public class PayRegistrationFeeControllerTest {
    
    PayRegistrationFeeController prfc;
    
    public PayRegistrationFeeControllerTest() throws ParseException, CreditCardExpiredException {
        prfc=new PayRegistrationFeeController();
        CreditCardDB cddb=mock(CreditCardDB.class);
        when(cddb.getCreditCard(12345678)).thenReturn(new CreditCard(12345678, "2020-10-10", 50));
        prfc.setCreditCardDB(cddb);
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
        
    @Test
    public void testGetCreditCard() throws Exception {
        System.out.println("getCreditCard");
        CreditCard cc=new CreditCard(12345678, "2020-10-10", 50);   
        prfc.getCreditCard(12345678);
        boolean expResult=true;
        prfc.setCreditCard(new CreditCard(12345678, "2020-10-10", 50));   
        boolean result=prfc.getThisCreditCard().equals(cc);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetCreditCard() throws Exception {
        System.out.println("setCreditCard");
        CreditCard cc=new CreditCard(12345678, "2020-10-10", 50);
        prfc.setCreditCard(cc);
        CreditCard expResult=cc;
        CreditCard result=prfc.getThisCreditCard();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deductCost method, of class PayRegistrationFeeController.
     */
    @Test
    public void testDeductCost() throws ParseException, CreditCardExpiredException {
        System.out.println("deductCost");
        int tin = 123456789;
        double cost=10;
        boolean expResult = false;
        prfc.setCreditCardDB(mock(CreditCardDB.class));
        boolean result = prfc.deductCost(cost);
        assertEquals(expResult, result);
        cost=-1;
        prfc.setCreditCardDB(mock(CreditCardDB.class));
        result = prfc.deductCost(cost);
        assertEquals(expResult, result);
    }
    
}
