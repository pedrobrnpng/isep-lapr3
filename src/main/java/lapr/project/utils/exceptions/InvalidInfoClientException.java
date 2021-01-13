/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.exceptions;

/**
 *
 * @author joaol
 */
public class InvalidInfoClientException extends Exception {
    
    private static final long serialVersionUID = 78322892;
    
    /**
     *
     * @param errMsg
     */
    public InvalidInfoClientException(String errMsg) {
        super(errMsg);
    }
    
}
