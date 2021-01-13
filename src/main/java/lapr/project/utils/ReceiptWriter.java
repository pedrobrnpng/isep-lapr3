/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.model.Invoice;

/**
 *
 * @author pedro
 */
public class ReceiptWriter {
    
    public boolean emitReceipt(Invoice inv, String file) {
        
        StringBuilder sb = new StringBuilder(inv.toString() + "\n\n");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(sb);
            return true;
        } catch(IOException ex) {
            Logger.getLogger(ReceiptWriter.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
    }
    
}
