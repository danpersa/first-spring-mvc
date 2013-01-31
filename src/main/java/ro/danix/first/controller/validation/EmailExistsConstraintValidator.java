package ro.danix.first.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.danix.first.controller.dto.user.UserCreateIn;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Slf4j
@Component
public class EmailExistsConstraintValidator implements ConstraintValidator<EmailExistsConstraint, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailExistsConstraint constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByEmailAddress(new EmailAddress(value));
        if (user != null) {
            return false;
        }
        return true;
    }
}
