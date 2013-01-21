package ro.danix.first.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

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
}
