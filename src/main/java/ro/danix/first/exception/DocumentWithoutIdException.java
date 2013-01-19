/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.danix.first.exception;

/**
 *
 * @author moro
 */
public class DocumentWithoutIdException extends RuntimeException {

    public DocumentWithoutIdException() {
        super("The document must have ID");
    }
}
