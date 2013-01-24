package ro.danix.first.controller.util;

import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import ro.danix.first.controller.dto.FormValidationErrorDTO;
import ro.danix.first.controller.exception.FormValidationError;

/**
 *
 * @author dpersa
 */
@Slf4j
@Component
public class ValidationUtils {

    @Autowired
    private LocaleContextHolderWrapper localeHolderWrapper;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private Validator validator;

    public FormValidationErrorDTO handleFormValidationError(FormValidationError validationError) {
        log.debug("Handling form validation error");

        Locale current = localeHolderWrapper.getCurrentLocale();

        List<FieldError> fieldErrors = validationError.getFieldErrors();

        FormValidationErrorDTO dto = new FormValidationErrorDTO();

        for (FieldError fieldError : fieldErrors) {
            String[] fieldErrorCodes = fieldError.getCodes();
            for (int index = 0; index < fieldErrorCodes.length; index++) {
                String fieldErrorCode = fieldErrorCodes[index];

                log.debug("Get localized message for fieldErrorCode: {} with argument: {}", fieldErrorCode, fieldError.getArguments());
                String localizedError = messageSource.getMessage(fieldErrorCode, fieldError.getArguments(), current);
                if (localizedError != null && !localizedError.equals(fieldErrorCode)) {
                    log.debug("Adding error message: {} to field: {}", localizedError, fieldError.getField());
                    dto.addFieldError(fieldError.getField(), localizedError);
                    break;
                } else {
                    if (isLastFieldErrorCode(index, fieldErrorCodes)) {
                        dto.addFieldError(fieldError.getField(), localizedError);
                    }
                }
            }
        }

        return dto;
    }
    
    public void validate(String objectName, Object validated) throws FormValidationError {
        log.debug("Validating object: " + validated);

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(validated, objectName);
        validator.validate(validated, bindingResult);

        if (bindingResult.hasErrors()) {
            log.debug("Validation errors found:" + bindingResult.getFieldErrors());
            throw new FormValidationError(bindingResult.getFieldErrors());
        }
    }

    private boolean isLastFieldErrorCode(int index, String[] fieldErrorCodes) {
        return index == fieldErrorCodes.length - 1;
    }
}
