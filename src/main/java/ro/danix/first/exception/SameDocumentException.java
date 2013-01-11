package ro.danix.first.exception;

/**
 *
 * @author dpersa
 */
public class SameDocumentException extends RuntimeException {

    public SameDocumentException() {
        super("Documents should not be the same");
    }
}
