/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.utils.exceptions.InvalidInfoClientException;

/**
 *
 * @author joaol
 */
public class Client {
    
    private String email;
    private String name;
    private int pass;
    private int age;
    private double height;
    private double weight;
    private char gender;
    private double avgCyclingSpeed;
    private int points;
    
    /**
     * Constructor without average speed and points, unencrypted password
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @throws InvalidInfoClientException
     */
    public Client(String name, String email, String pass, int age, double height, double weight, char gender) throws InvalidInfoClientException {
        this.name=name;
        this.email=email;
        this.pass=encryptPass(pass);
        this.age=age;
        this.height=height;
        this.weight=weight;
        if (!(gender=='M' || gender=='F')) throw new InvalidInfoClientException("Gender does not exist.");
        this.gender=gender;
        avgCyclingSpeed=0;
        points=0;
    }
    
    /**
     * Constructor without average speed and points, encrypted password
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @throws InvalidInfoClientException
     */
    public Client(String name, String email, int pass, int age, double height, double weight, char gender) throws InvalidInfoClientException {
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.age=age;
        this.height=height;
        this.weight=weight;
        if (!(gender=='M' || gender=='F')) throw new InvalidInfoClientException("Gender does not exist.");
        this.gender=gender;
        avgCyclingSpeed=0;
        points=0;
    }
    
    /**
     * Full constructor
     * @param name
     * @param email
     * @param pass
     * @param age
     * @param height
     * @param weight
     * @param gender
     * @param avgCyclingSpeed
     * @param points
     * @throws InvalidInfoClientException
     */
    public Client(String name, String email, int pass, int age, double height, double weight, char gender, double avgCyclingSpeed, int points) throws InvalidInfoClientException {
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.age=age;
        this.height=height;
        this.weight=weight;
        if (!(gender=='M' || gender=='F')) throw new InvalidInfoClientException("Gender does not exist.");
        this.gender=gender;
        this.avgCyclingSpeed=avgCyclingSpeed;
        this.points=points;
    }

    /**
     * Return email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return encrypted pass
     * @return
     */
    public int getPass() {
        return pass;
    }

    /**
     * Set and encrypt pass
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = encryptPass(pass);
    }
    
    /**
     * Return age
     * @return
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Set age
     * @param age
     */
    public void setAge(int age) {
        this.age=age;
    }

    /**
     * Return height
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set height
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Return weight
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set weight
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Return gender
     * @return
     */
    public char getGender() {
        return gender;
    }

    /**
     * Set gender
     * @param gender
     * @throws InvalidInfoClientException
     */
    public void setGender(char gender) throws InvalidInfoClientException {
        if (!(gender=='M' || gender=='F')) throw new InvalidInfoClientException("Gender does not exist.");
        this.gender = gender;
    }
    
    /**
     * Return average cycling speed
     * @return
     */
    public double getAvgCyclingSpeed() {
        return avgCyclingSpeed;
    }
    
    /**
     * Set average cycling speed
     * @param avgCyclingSpeed
     */
    public void setAvgCyclingSpeed(double avgCyclingSpeed) {
        this.avgCyclingSpeed=avgCyclingSpeed;
    }

    /**
     * Return points
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set points
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    /**
     * Add points
     * @param points 
     */
    public void addPoints(int points) {
        this.points += points;
    }
    
    /**
     * Encrypt pass as hash code
     * @param pass
     * @return
     */
    public int encryptPass(String pass) {
        int hash = 2457;
        hash = 89 * hash + Objects.hashCode(pass);
        return hash;
    }

    /**
     * Hash code
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.gender);
        return hash;
    }

    /**
     * Equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!this.email.equals(other.email)) {
            return false;
        }
        return true;
    }
    
    /**
     * toString
     * @return 
     */
    @Override
    public String toString() {
        return name + ":\n    -Email = " + email + "\n    -Pass = " + pass + "\n    -Age = " + age + "\n    -Height = " + height + "\n    -Weight = " + weight + "\n    -Gender = " + gender + "\n    -Average Cycling Speed = " + avgCyclingSpeed + "\n    -Points = "+points;
    }
    
}
