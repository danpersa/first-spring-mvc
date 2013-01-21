package ro.danix.first.controller.exception;

import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author danix
 */
public class FormValidationError extends Exception {

    private List<FieldError> fieldErrors;

    public FormValidationError(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
