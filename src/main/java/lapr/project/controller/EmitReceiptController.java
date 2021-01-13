/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.text.ParseException;
import lapr.project.data.InvoiceDB;
import lapr.project.model.Invoice;
import lapr.project.utils.ReceiptWriter;

/**
 *
 * @author pedro
 */
public class EmitReceiptController {
    
    private final static String URLDB = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private final static String USERDB = "LAPR3_2019_G022";
    private final static String PASSDB = "qwerty";

    private InvoiceDB invDB;
    
    public EmitReceiptController(){
        invDB = new InvoiceDB(URLDB,USERDB,PASSDB);
    }
    
    public void setDB(InvoiceDB invDB){
        this.invDB = invDB;
    }
    
    public void emitReceipt(int invID) throws ParseException{
        Invoice inv = invDB.getInvoice(invID);
        
        ReceiptWriter rw = new ReceiptWriter();
        rw.emitReceipt(inv, "Receipt.txt");
        
    }
    
}
