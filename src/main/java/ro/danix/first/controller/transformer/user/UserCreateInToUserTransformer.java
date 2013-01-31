package ro.danix.first.controller.transformer.user;

import org.springframework.stereotype.Component;
import ro.danix.first.controller.dto.user.UserCreateIn;
import ro.danix.first.controller.transformer.Transformer;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
public class UserCreateInToUserTransformer implements Transformer<UserCreateIn, User> {

    @Override
    public User apply(UserCreateIn input) {
        User user = new User(input.getUsername(), new EmailAddress(input.getEmailAddress()));
        user.setFirstname(input.getFirstname());
        user.setLastname(input.getLastname());
        return user;
    }
}
