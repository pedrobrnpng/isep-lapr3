/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import lapr.project.model.Bicycle;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bruno
 */
public class comparatorBicyclesTest {
    
    public comparatorBicyclesTest() {
    }

    /**
     * Test of compare method, of class comparatorBicycles.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Bicycle a = new Bicycle();
        a.setWheelSize("a");
        a.setID(3);
        Bicycle b = a;
        comparatorBicycles instance = new comparatorBicycles();
        int expResult = 0;
        int result = instance.compare(a, b);
        assertEquals(expResult, result);
        b = new Bicycle("b", 2, 1, 1, 1, 1);
        expResult = 1;
        result = instance.compare(a, b);
        assertEquals(expResult, result);
        a.setID(1);
        expResult = -1;
        result = instance.compare(a, b);
        assertEquals(expResult, result);
    }
    
}
