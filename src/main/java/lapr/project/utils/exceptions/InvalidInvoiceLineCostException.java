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
public class InvalidInvoiceLineCostException extends Exception {
    
    private static final long serialVersionUID = 78322592;
    
    /**
     *
     * @param errMsg
     */
    public InvalidInvoiceLineCostException(String errMsg) {
        super(errMsg);
    }
    
}
