package ro.danix.first.controller.transformer.user;

import org.springframework.stereotype.Component;
import ro.danix.first.controller.resource.UserCreateOut;
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
        UserCreateOut userCreateResourceOut = new UserCreateOut();
        userCreateResourceOut.setEmailAddress(input.getEmailAddress().getValue());
        userCreateResourceOut.setFirstname(input.getFirstname());
        userCreateResourceOut.setId(input.getId());
        userCreateResourceOut.setLastname(input.getLastname());
        userCreateResourceOut.setUsername(input.getUsername());
        return userCreateResourceOut;
    }
}
