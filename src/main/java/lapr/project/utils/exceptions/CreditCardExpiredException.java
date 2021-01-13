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
public class CreditCardExpiredException extends Exception {
    
    private static final long serialVersionUID = 78382892;
    
    /**
     *
     * @param errMsg
     */
    public CreditCardExpiredException(String errMsg) {
        super(errMsg);
    }
    
}
