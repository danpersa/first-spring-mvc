package ro.danix.first.exception;

/**
 *
 * @author dpersa
 */
public class NullArgumentException extends RuntimeException {

    public NullArgumentException() {
        super("The method arguemnt should not be null");
    }

    public NullArgumentException(Class clazz) {
        super("The method argument with class: " + clazz + " should not be null");
    }
}
