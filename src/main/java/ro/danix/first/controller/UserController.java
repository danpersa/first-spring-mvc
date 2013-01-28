package ro.danix.first.controller;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ro.danix.first.controller.dto.FormValidationErrorDTO;
import ro.danix.first.controller.editor.UserEditor;
import ro.danix.first.controller.exception.FormValidationError;
import ro.danix.first.controller.exception.UserNotFoundException;
import ro.danix.first.controller.util.QueryUtils;
import ro.danix.first.controller.util.ValidationUtils;
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
    private ValidationUtils validationUtils;
    
    @Autowired
    private QueryUtils queryUtils;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8900/users/?page=0&size=10&sortBy=username&sortOrder=ASC
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Page<User> findAllPaginatedAndSorted(@RequestParam(value = QueryUtils.PAGE) final int page, @RequestParam(value = QueryUtils.SIZE) final int size,
            @RequestParam(value = QueryUtils.SORT_BY) final String sortBy, @RequestParam(value = QueryUtils.SORT_ORDER) final String sortOrder) {
        Pageable pageable = queryUtils.createPagination(page, size, sortBy, sortOrder);
        Page<User> users = userService.findAll(pageable);
        return users;
    }

    /**
     * curl --user admin@fake.com:adminpass -H "Content-Type: application/json"
     * -i --data '{ "username":"danix", "lastname":"lastname" }'
     * http://localhost:8900/users
     *
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public User create(@RequestBody User user) throws FormValidationError {
        validationUtils.validate("user", user);
        user = userService.save(user);
        return user;
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    User show(@PathVariable User user) {
        return user;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public User update(@PathVariable("userId") final BigInteger userId, @RequestBody User user) throws FormValidationError {
        validationUtils.validate("user", user);
        user = userService.update(userId, user);
        return user;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    public void delete(Model model, @PathVariable User user) {
        this.userService.delete(user);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
        binder.registerCustomEditor(User.class, new UserEditor(userService));
    }

    @ExceptionHandler(FormValidationError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FormValidationErrorDTO handleFormValidationError(FormValidationError validationError) {
        return validationUtils.handleFormValidationError(validationError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFoundException(UserNotFoundException ex) {
        log.debug("handling 404 error on a todo entry");
    }
}
