package ro.danix.first.controller.transformer.user;

import org.springframework.stereotype.Component;
import ro.danix.first.controller.dto.user.UserCreateOut;
import ro.danix.first.controller.transformer.Transformer;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
public class UserToUserCreateOutTransformer implements Transformer<User, UserCreateOut> {

    @Override
    public UserCreateOut apply(User input) {
        UserCreateOut userCreateOut = new UserCreateOut();
        userCreateOut.setEmailAddress(input.getEmailAddress().getValue());
        userCreateOut.setFirstname(input.getFirstname());
        userCreateOut.setId(input.getId());
        userCreateOut.setLastname(input.getLastname());
        userCreateOut.setUsername(input.getUsername());
        return userCreateOut;
    }
}
