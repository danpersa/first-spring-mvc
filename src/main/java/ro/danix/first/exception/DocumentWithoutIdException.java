
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
