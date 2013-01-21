package ro.danix.first.controller.converter;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.user.UserService;

@Slf4j
public class UserConverter implements Converter<String, User> {

    private UserService userService;

    @Autowired
    public UserConverter(UserService userService) {
        log.info("Creating UserConverter");
        this.userService = userService;
    }

    @Override
    public User convert(String source) {
        log.info("Start converting string into user: " + source);
        return this.userService.findOne(new BigInteger(source));
    }
}
