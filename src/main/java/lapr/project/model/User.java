/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Objects;

/**
 *
 * @author joaol
 */
public class User {
    private String name;
    private String email;
    private int pass;
    
    /**
     *
     * @param name
     * @param email
     * @param pass
     */
    public User(String name, String email, String pass) {
        this.name=name;
        this.email=email;
        this.pass=encryptPass(pass);
    }
    
    /**
     *
     * @param name
     * @param email
     * @param pass
     */
    public User(String name, String email, int pass) {
        this.name=name;
        this.email=email;
        this.pass=pass;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getPass() {
        return pass;
    }

    /**
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = encryptPass(pass);
    }
    
    /**
     *
     * @param pass
     * @return
     */
    public int encryptPass(String pass) {
        int hash = 2457;
        hash = 89 * hash + Objects.hashCode(pass);
        return hash;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }

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
        final User other = (User) obj;
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return name + ":\n    -Email = " + email + "\n    -Pass = " + pass + '\n';
    }
}
