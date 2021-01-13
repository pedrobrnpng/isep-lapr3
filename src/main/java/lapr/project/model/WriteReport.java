/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
public class WriteReport {

    /**
     * Writes a report related to a park
     *
     * @param write - text to write
     * @param outputFileName
     * @return boolean that indicates if it sucessufully wrote on the file
     */
    public static boolean writeParkReport(List<String> write, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String s : write) {
                writer.append(s);
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(WriteReport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     *
     * @param write
     * @return
     */
    public boolean writeUnlockingReport(String write, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.append(write);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(WriteReport.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
