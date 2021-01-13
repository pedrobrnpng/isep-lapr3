/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.Objects;
import lapr.project.utils.exceptions.CreditCardExpiredException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author bruno
 */
public class VehiclesHistoryTest {

    public VehiclesHistoryTest() {
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
     * Test of toString method, of class VehiclesHistory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        LinkedList<VehiclesHistoryLine> l = new LinkedList<>();
        VehiclesHistory instance = new VehiclesHistory("", l);
        String expResult = "VEHICLE HISTORY of " + "" + ", by ride-sharing\n\n"
                + "You don't have any vehicles requests.";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        l.add(new VehiclesHistoryLine('1', "", "", ""));
        expResult = "VEHICLE HISTORY of " + "" + ", by ride-sharing\n\n"
                + "" + " was " + "LOCKED" + " at " + "" + ", " + "\n";
        result = instance.toString();
    }

    /**
     * Test of hashCode method, of class VehiclesHistory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        LinkedList<VehiclesHistoryLine> list=new LinkedList<>();
        VehiclesHistory instance = new VehiclesHistory("joao", list);
        int expResult = 3;
        expResult = 11 * expResult + Objects.hashCode("joao");
        expResult = 11 * expResult + Objects.hashCode(list);
        int result=instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class VehiclesHistory.
     */
    @Test
    public void testEquals() throws ParseException, CreditCardExpiredException {
        System.out.println("equals");
        Object obj = null;
        LinkedList<VehiclesHistoryLine> list=new LinkedList<>();
        VehiclesHistory instance = new VehiclesHistory("joao", list);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new VehiclesHistory("joao", list);
        expResult=true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new CreditCard(12345678, "2020-12-12", 50);
        expResult=false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=instance;
        expResult=true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

}
