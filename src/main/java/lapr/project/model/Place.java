/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Utilizador
 */
public interface Place {

    /**
     *
     * @return
     */
    public double getLatitude();

    /**
     *
     * @return
     */
    public double getLongitude();

    /**
     *
     * @return
     */
    public double getElevation();

    /**
     *
     * @return
     */
    public int getId();
}
