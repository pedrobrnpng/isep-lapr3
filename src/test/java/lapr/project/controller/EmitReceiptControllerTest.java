/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import lapr.project.data.InvoiceDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Invoice;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pedro
 */
public class EmitReceiptControllerTest {
    
    public EmitReceiptControllerTest() {
    }

    /**
     * Test of emitReceipt method, of class EmitReceiptController.
     */
    @Test
    public void testEmitReceipt() throws Exception {
        System.out.println("emitReceipt");
        int invID = 123;
        InvoiceDB idb = mock(InvoiceDB.class);
        Invoice inv = new Invoice(123, "0001-01-01", 12, 0, 14.99);
        
        EmitReceiptController instance = new EmitReceiptController();
        when(idb.getInvoice(invID)).thenReturn(inv);
        instance.setDB(idb);
        instance.emitReceipt(invID);

    }
    
}
