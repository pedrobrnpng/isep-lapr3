/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import lapr.project.utils.exceptions.InvalidInfoClientException;
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
public class ClientTest {
    
    private final Client client=new Client("João Leal", "joaolealmgs3@gmail.com", "pass123", 19, 1.77, 75, 'M');
    private final Client client2=new Client("João Leal", "joaolealmgs3@gmail.com", 32, 19, 1.77, 75, 'M');
    private final Client client3=new Client("João Leal", "joaolealmgs3@gmail.com", 32, 19, 1.77, 75, 'M', 0, 0);
    
    public ClientTest() throws InvalidInfoClientException {
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
     * Test of getHeight method, of class Client.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        double expResult = 1.77;
        double result = client.getHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setHeight method, of class Client.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        double height = 1.80;
        client.setHeight(height);
        double expResult = 1.80;
        double result = client.getHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWeight method, of class Client.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        double expResult = 75;
        double result = client.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setWeight method, of class Client.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double weight = 70;
        client.setWeight(weight);
        double expResult = 70;
        double result = client.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGender method, of class Client.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        char expResult = 'M';
        char result = client.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class Client.
     */
    @Test
    public void testSetGender() throws InvalidInfoClientException {
        System.out.println("setGender");
        try {
            Client client4=new Client("João Leal", "joaolealmgs3@gmail.com", "pass123", 19, 1.77, 75, 's');
            assertTrue(false);
        } catch (InvalidInfoClientException iice) {
            assertTrue(true);
        }
        try {
            Client client5=new Client("João Leal", "joaolealmgs3@gmail.com", 123, 19, 1.77, 75, 's');
            assertTrue(false);
        } catch (InvalidInfoClientException iice) {
            assertTrue(true);
        }
        try {
            Client client6=new Client("João Leal", "joaolealmgs3@gmail.com", 123, 19, 1.77, 75, 's', 0, 0);
            assertTrue(false);
        } catch (InvalidInfoClientException iice) {
            assertTrue(true);
        }
        char gender = 'F';
        client.setGender(gender);
        char expResult='F';
        char result=client.getGender();
        assertEquals(expResult, result);
        try {
            client.setGender('D');
            assertTrue(false);
        } catch (InvalidInfoClientException iice) {
            assertTrue(true);
        }
    }
    
    /**
     * Test of getAvgCyclingSpeed method, of class Client.
     */
    @Test
    public void testGetAvgCyclingSpeed() {
        System.out.println("getAvgCyclingSpeed");
        double expResult = 0;
        double result = client.getAvgCyclingSpeed();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setAvgCyclingSpeed method, of class Client.
     */
    @Test
    public void testSetAvgCyclingSpeed() {
        System.out.println("setAvgCyclingSpeed");
        double speed = 3;
        client.setAvgCyclingSpeed(speed);
        double expResult=3;
        double result=client.getAvgCyclingSpeed();
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getAge method, of class Client.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        int expResult = 19;
        int result = client.getAge();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAge method, of class Client.
     */
    @Test
    public void testSetAge() {
        System.out.println("setAge");
        int age = 20;
        client.setAge(age);
        int expResult=age;
        int result=client.getAge();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getEmail method, of class Client.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "joaolealmgs3@gmail.com";
        String result = client.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Client.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "joaolealp51@gmail.com";
        client.setEmail(email);
        String expResult=email;
        String result=client.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "João Leal";
        String result = client.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Joana";
        client.setName(name);
        String expResult=name;
        String result=client.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPass method, of class Client.
     */
    @Test
    public void testGetPass() {
        System.out.println("getPass");
        int expResult = -791876942;
        int result = client.getPass();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPass method, of class Client.
     */
    @Test
    public void testSetPass() {
        System.out.println("setPass");
        String pass = "passs";
        client.setPass(pass);
        int expResult=106656947;
        int result=client.getPass();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPoints method, of class Client.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        int expResult = 0;
        int result = client.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPoints method, of class Client.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        int points = 50;
        client.setPoints(points);
        assertEquals(points, client.getPoints());
    }

    /**
     * Test of encryptPass method, of class Client.
     */
    @Test
    public void testEncryptPass() {
        System.out.println("encryptPass");
        String pass = "pass";
        int expResult = 3652162;
        int result = client.encryptPass(pass);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Client.
     */
    @Test
    public void testHashCode() throws InvalidInfoClientException {
        System.out.println("hashCode");
        int expResult = 1930106678;
        Client instance=new Client("w", "s", "w", 1, 1, 1, 'M');
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() throws InvalidInfoClientException {
        System.out.println("equals");
        Object obj = new Client("jj", "jj@gmail.com", "123", 20, 1.80, 70, 'M');
        Client instance = new Client("jj", "jj@gmail.com", "123", 20, 1.80, 70, 'M');
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new Client("jj", "js@gmail.com", "123", 20, 1.80, 70, 'M');
        expResult=false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=null;
        expResult=false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=new ClientRegistry();
        expResult=false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        obj=instance;
        expResult=true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() throws InvalidInfoClientException {
        System.out.println("toString");
        Client instance=new Client("João Leal", "joaolealmgs3@gmail.com", "pass123", 19, 1.77, 75.0, 'M');
        String expResult = "João Leal:\n    -Email = joaolealmgs3@gmail.com\n    -Pass = -791876942\n    -Age = 19\n    -Height = 1.77\n    -Weight = 75.0\n    -Gender = M\n    -Average Cycling Speed = 0.0\n    -Points = 0";
        String result = instance.toString();
        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of addPoints method, of class Client.
     */
    @org.junit.Test
    public void testAddPoints() throws InvalidInfoClientException {
        System.out.println("addPoints");
        int points = 10;
        Client instance=new Client("João Leal", "joaolealmgs3@gmail.com", "pass123", 19, 1.77, 75.0, 'M');
        instance.setPoints(points);
        points += 15;
        instance.addPoints(points);
        int expResult = 25;
        int result = instance.getPoints();
        assertEquals(expResult, result);
    }

}
