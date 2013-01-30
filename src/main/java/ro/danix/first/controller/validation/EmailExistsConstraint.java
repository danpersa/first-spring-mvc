package ro.danix.first.controller.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

/**
 *
 * @author danix
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailExistsConstraintValidator.class)
public @interface EmailExistsConstraint {

    String message() default "Email already exists!";

    Class<String>[] groups() default {};

    Class<String>[] payload() default {};
}
