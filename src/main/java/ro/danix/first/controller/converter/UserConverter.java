package ro.danix.first.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.UserService;

public class UserConverter implements Converter<String, User> {

    private UserService userService;

    @Autowired
    public UserConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(String source) {
        return this.userService.findOne(new Long(source));
    }
}
