package ro.danix.first.controller.transformer.user;

import org.springframework.stereotype.Component;
import ro.danix.first.controller.dto.user.UserDtoOut;
import ro.danix.first.controller.transformer.Transformer;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
public class UserToUserDtoOutTransformer implements Transformer<User, UserDtoOut> {

    @Override
    public UserDtoOut apply(User input) {
        UserDtoOut output = new UserDtoOut();
        output.setEmailAddress(input.getEmailAddress().getValue());
        output.setFirstname(input.getFirstname());
        output.setId(input.getId());
        output.setLastname(input.getLastname());
        output.setUsername(input.getUsername());
        return output;
    }
}
