package ro.danix.first.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.danix.first.controller.converter.UserConverter;
import ro.danix.first.controller.dto.FormValidationErrorDTO;
import ro.danix.first.controller.editor.UserEditor;
import ro.danix.first.controller.exception.FormValidationError;
import ro.danix.first.controller.exception.UserNotFoundException;
import ro.danix.first.controller.util.LocaleContextHolderWrapper;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    
    @Autowired
    private LocaleContextHolderWrapper localeHolderWrapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Validator validator;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> index() {
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    User create(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        userService.save(user);
        return user;
    }

    // The user is loaded from the "user" URI variable via {@link UserConverter}
    @RequestMapping(value = "/{user}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    User show(@PathVariable User user) {
        return user;
    }

    // The user is loaded from the "user" URI variable via {@link UserConverter}.
    // Data binding and validation are also applied.
    @RequestMapping(value = "/{user}", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        userService.save(user);
        return "redirect:../users";
    }

    // The user is loaded from the "user" URI variable via {@link UserConverter}.
    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    public String destroy(Model model, @PathVariable User user) {
        this.userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        binder.registerCustomEditor(User.class, new UserEditor(userService));
    }
    
    
    private void validate(String objectName, Object validated) throws FormValidationError {
        log.debug("Validating object: " + validated);

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(validated, objectName);
        validator.validate(validated, bindingResult);

        if (bindingResult.hasErrors()) {
            log.debug("Validation errors found:" + bindingResult.getFieldErrors());
            throw new FormValidationError(bindingResult.getFieldErrors());
        }
    }

    @ExceptionHandler(FormValidationError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FormValidationErrorDTO handleFormValidationError(FormValidationError validationError) {
        log.debug("Handling form validation error");

        Locale current = localeHolderWrapper.getCurrentLocale();

        List<FieldError> fieldErrors = validationError.getFieldErrors();

        FormValidationErrorDTO dto = new FormValidationErrorDTO();

        for (FieldError fieldError: fieldErrors) {
            String[] fieldErrorCodes = fieldError.getCodes();
            for (int index = 0; index < fieldErrorCodes.length; index++) {
                String fieldErrorCode = fieldErrorCodes[index];

                String localizedError = messageSource.getMessage(fieldErrorCode, fieldError.getArguments(), current);
                if (localizedError != null && !localizedError.equals(fieldErrorCode)) {
                    log.debug("Adding error message: {} to field: {}", localizedError, fieldError.getField());
                    dto.addFieldError(fieldError.getField(), localizedError);
                    break;
                }
                else {
                    if (isLastFieldErrorCode(index, fieldErrorCodes)) {
                        dto.addFieldError(fieldError.getField(), localizedError);
                    }
                }
            }
        }

        return dto;
    }

    private boolean isLastFieldErrorCode(int index, String[] fieldErrorCodes) {
        return index == fieldErrorCodes.length - 1;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFoundException(UserNotFoundException ex) {
        log.debug("handling 404 error on a todo entry");
    }
}
