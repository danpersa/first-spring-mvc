package ro.danix.first.model.eventlistener;

import com.mongodb.DBObject;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
public class BeforeSaveEventListener extends AbstractMongoEventListener<User> {

    @Autowired
    private Validator validator;

    @Override
    public void onBeforeSave(User source, DBObject dbo) {
        Set<ConstraintViolation<User>> violations = validator.validate(source);

        if (violations.size() > 0) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
