package ro.danix.first.model.eventlistener;

import com.mongodb.DBObject;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
@Slf4j
public class BeforeSaveEventListener extends AbstractMongoEventListener<User> {

    @Autowired
    private Validator validator;

    @Override
    public void onBeforeSave(User source, DBObject dbo) {
        Set<ConstraintViolation<User>> violations = validator.validate(source);

        if (violations.size() > 0) {
            log.info("Violations: ");
            for (ConstraintViolation<User> violation : violations) {
                log.info("Violation on {} with message {} for value {}", new Object[]{violation.getPropertyPath(),
                            violation.getMessage(),
                            violation.getInvalidValue()});
            }
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
