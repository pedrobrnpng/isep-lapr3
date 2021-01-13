/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.text.ParseException;
import lapr.project.model.Invoice;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedro
 */
public class ReceiptWriterTest {
    
    public ReceiptWriterTest() {
    }

    /**
     * Test of emitReceipt method, of class ReceiptWriter.
     */
    @Test
    public void testEmitReceipt() throws ParseException {
        System.out.println("emitReceipt");
        
        Invoice inv = new Invoice(123, "0001-01-01", 12, 0, 14.99);
        
        ReceiptWriter instance = new ReceiptWriter();
        
        boolean expResult = true;
        boolean result = instance.emitReceipt(inv,"Receipt.txt");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.emitReceipt(inv,"");
        assertEquals(expResult, result);
    }
    
}
