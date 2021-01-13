/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class WriteReportTest {

    public WriteReportTest() {
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
     * Test of writeParkReport method, of class WriteReport.
     */
    @Test
    public void testWriteParkReport() {
        System.out.println("writeParkReport");
        List<String> write = new ArrayList<>();
        write.add("ePT03;100;0\n");
        write.add("ePT02;20;3846\n");
        boolean expResult = true;
        boolean result = WriteReport.writeParkReport(write, "parkReport.txt");
        assertEquals(expResult, result);
        expResult = false;
        result = WriteReport.writeParkReport(write, "");
        assertEquals(expResult, result);
        
    }

    @Test
    public void testWriteUnlockingReport(){
        System.out.println("writeUnlockingReport");
        String write = "Vehicle ID: 1\n"
                + "Time of Unlocking: 13:24:51 16/07/2019\n"
                + "Unlocker=soimbra@gmail.com\n";
        WriteReport instance = new WriteReport();
        boolean expResult = true;
        boolean result = instance.writeUnlockingReport(write, "UnlockingReport.txt");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.writeUnlockingReport(write, "");
        assertEquals(expResult, result);
    }    
}
