package ro.danix.first.exception;

import java.math.BigInteger;
import lombok.Getter;

/**
 *
 * @author danix
 */
public class DocumentWithIdNotFoundException extends RuntimeException {

    @Getter
    private BigInteger id;

    public DocumentWithIdNotFoundException(BigInteger id) {
        super("The document with id " + id + " could not be found!");
    }
}
