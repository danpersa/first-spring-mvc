package ro.danix.first.controller.exception;

/**
 * @author danix
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}
