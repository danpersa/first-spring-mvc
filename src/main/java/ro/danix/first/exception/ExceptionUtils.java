package ro.danix.first.exception;

import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.AbstractDocument;

/**
 *
 * @author dpersa
 */
@Component
public class ExceptionUtils {

    public void argumentShouldNotBeNull(Object object) {
        if (object == null) {
            throw new NullArgumentException();
        }
    }

    public void argumentsShouldNotBeNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new NullArgumentException();
            }
        }
    }

    public void documentsShouldNotBeTheSame(AbstractDocument abstractDocument1, AbstractDocument abstractDocument2) {
        argumentShouldNotBeNull(abstractDocument1);
        argumentShouldNotBeNull(abstractDocument2);
        if (abstractDocument1 == abstractDocument2 || (abstractDocument1.getId() != null && abstractDocument1.getId().equals(abstractDocument2.getId()))) {
            throw new SameDocumentException();
        }
    }
}
