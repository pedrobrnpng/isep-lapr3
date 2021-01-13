/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaol
 */
public class EmailWriterTest {

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
     * Test of writeEmail method, of class EmailWriter.
     */
    @Test
    public void testWriteEmail() throws Exception {
        System.out.println("writeEmail");
        String name = "João Leal";
        boolean parkedWell = true;
        String time = "2m 30s";
        EmailWriter instance = new EmailWriter();
        String expResult = "Greetings, João Leal\nYour vehicle has been parked correctly, and your trip lasted 2m 30s. We hope you had a great ride!";
        String result = instance.writeEmail(name, parkedWell, time);
        System.out.println(result);
        assertEquals(expResult, result);
        parkedWell=false;
        expResult = "Greetings, João Leal\nYour vehicle has NOT been parked correctly, and your trip lasted 2m 30s. Please be more mindful of where you park next time.";
        result = instance.writeEmail(name, parkedWell, time);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendEmail method, of class EmailWriter.
     */
    @Test
    public void testSendEmail() throws Exception {
        System.out.println("sendEmail");
        String to = "g22ridesharing@gmail.com";
        String name = "João Leal";
        boolean parkedWell = true;
        String time = "2m 30s";
        EmailWriter instance = new EmailWriter();
        String expResult = "Greetings, João Leal\nYour vehicle has been parked correctly, and your trip lasted 2m 30s. We hope you had a great ride!";
        String result = instance.sendEmail(to, name, parkedWell, time);
        assertEquals(expResult, result);
        to="321";
        result = instance.sendEmail(to, name, parkedWell, time);
        assertEquals("", result);
    }
    
}
