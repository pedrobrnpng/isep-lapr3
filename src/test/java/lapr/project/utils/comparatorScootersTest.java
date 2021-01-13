/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Scooter;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bruno
 */
public class comparatorScootersTest {
    
    public comparatorScootersTest() {
    }

    /**
     * Test of compare method, of class comparatorBicycles.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Scooter a = new Scooter();
        a.setID(3);
        Scooter b = a;
        comparatorScooters instance = new comparatorScooters();
        int expResult = 0;
        int result = instance.compare(a, b);
        assertEquals(expResult, result);
        b = new Scooter("b", 1, 1, 2, 1, 1,1,1,1);
        expResult = 1;
        result = instance.compare(a, b);
        assertEquals(expResult, result);
        a.setID(1);
        expResult = -1;
        result = instance.compare(a, b);
        assertEquals(expResult, result);
    }
    
}
