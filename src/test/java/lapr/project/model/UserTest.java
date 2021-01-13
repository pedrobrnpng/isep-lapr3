/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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
public class UserTest {

    public UserTest() {
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
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        String expResult = "Veiga";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Bruno";
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        String expResult = "Veiga@ei.pt";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "bruno@oi.pt";
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getPass method, of class User.
     */
    @Test
    public void testGetPass() {
        System.out.println("getPass");
        User instance = new User("Veiga", "Veiga@ei.pt", 123);
        int expResult = 123;
        int result = instance.getPass();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPass method, of class User.
     */
    @Test
    public void testSetPass() {
        System.out.println("setPass");
        String pass = "123";
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        instance.setPass(pass);
    }

    /**
     * Test of encryptPass method, of class User.
     */
    @Test
    public void testEncryptPass() {
        System.out.println("encryptPass");
        String pass = "123";
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        int expResult = 267363;
        int result = instance.encryptPass(pass);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        int expResult = 2141887277;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object obj = null;
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object obj = new ClientRegistry();
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals4() {
        System.out.println("equals");
        Object obj = new User("Veiga", "Veiga@ei.pt", "123");;
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }



    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User("Veiga", "Veiga@ei.pt", "123");
        String expResult = "Veiga:\n" +
        "    -Email = Veiga@ei.pt\n" +
        "    -Pass = 267363\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
