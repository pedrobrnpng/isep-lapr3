/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import lapr.project.data.CreditCardDB;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.utils.exceptions.InvalidInfoClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author joaol
 */
public class InsertCreditCardControllerTest {
    
    InsertCreditCardController iccc;
    
    public InsertCreditCardControllerTest() {
        iccc=new InsertCreditCardController();
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
     * Test of newCreditCard method, of class InsertCreditCardController.
     */
    @Test
    public void testNewCreditCard() throws Exception {
        System.out.println("newCreditCard");
        int tin=12345678;
        String expDate="2020-10-10";
        double credits=50;
        iccc.newCreditCard(tin, expDate, credits);
        CreditCard expResult=new CreditCard(tin, expDate, credits);
        CreditCard result=iccc.getCreditCard();
        assertEquals(expResult,result);
    }

    /**
     * Test of setClient method, of class InsertCreditCardController.
     */
    @Test
    public void testSetClient() throws InvalidInfoClientException {
        System.out.println("setClient");
        Client cli = new Client("Maria Albertina", "mariaalb@gmail.com", "321", 19, 1.60, 55, 'F');
        iccc.setClient(cli);
        Client expResult=cli;
        Client result=iccc.getClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of registerCreditCard method, of class InsertCreditCardController.
     */
    @Test
    public void testRegisterCreditCard() throws Exception{
        int tin=12345678;
        String expDate="2020-10-10";
        double credits=50;
        iccc.newCreditCard(tin, expDate, credits);
        iccc.setCreditCardDB(mock(CreditCardDB.class));
        HashSet<CreditCard> creditCardList=new HashSet<>();
        creditCardList.add(new CreditCard(tin, expDate, credits));
        iccc.setCreditCardList(creditCardList);
        boolean result= iccc.registerCreditCard();
        assertEquals(false,result);
        tin=87654321;
        iccc.newCreditCard(tin, expDate, credits);
        result=iccc.registerCreditCard();
        assertEquals(false, result);
    }
    
     /**
     * Test of registerCreditCard method, of class InsertCreditCardController.
     */
    @Test
    public void testRegisterCreditCard2() throws Exception{
        int tin=12345678;
        String expDate="2020-10-10";
        double credits=50;
        iccc.newCreditCard(tin, expDate, credits);
        iccc.setCreditCardDB(mock(CreditCardDB.class));
        HashSet<CreditCard> creditCardList=new HashSet<>();
        creditCardList.add(new CreditCard(tin, expDate, credits));
        iccc.setCreditCardList(creditCardList);
        tin=87654321;
        CreditCardDB ccdb= mock(CreditCardDB.class);
        iccc.setCreditCardDB(ccdb);
        when(ccdb.registerCreditCard(new CreditCard(tin, expDate, credits), null)).thenReturn(true);
        iccc.newCreditCard(tin, expDate, credits);
        boolean result=iccc.registerCreditCard();
        assertEquals(true, result);
    }
}
