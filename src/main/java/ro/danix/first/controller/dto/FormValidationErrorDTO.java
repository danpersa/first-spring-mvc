package ro.danix.first.controller.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danix
 */
public class FormValidationErrorDTO {

    private List<FieldValidationErrorDTO> fieldErrors = new ArrayList<FieldValidationErrorDTO>();

    public FormValidationErrorDTO() {

    }

    public void addFieldError(String path, String message) {
        FieldValidationErrorDTO fieldError = new FieldValidationErrorDTO(path, message);
        fieldErrors.add(fieldError);
    }

    public List<FieldValidationErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
}
