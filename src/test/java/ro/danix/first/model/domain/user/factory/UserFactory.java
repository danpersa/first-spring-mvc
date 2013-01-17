package ro.danix.first.model.domain.user.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.Address;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.UserProfile;
import ro.danix.first.model.domain.factory.AbstractFactory;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
@Component
@Profile(value = "factories")
public class UserFactory extends AbstractFactory<User> {

    public static final String USERNAME = "danix";
    public static final String FIRST_NAME = "danix";
    public static final String LAST_NAME = "danix";
    public static final String EMAIL = "danix@manhattan.com";

    @Override
    protected User init() {
        User user = new User(USERNAME, new EmailAddress(EMAIL));
        user.setFirstname(FIRST_NAME);
        user.setLastname(LAST_NAME);
        user.add(new Address("street", "city", "state", "contry", "zipCode"));
        user.setUserProfile(new UserProfile("name", "website"));
        return user;
    }
    
    public User build(String suffix) {
        User user = init();
        user.setUsername(USERNAME + "-" + suffix);
        user.setEmailAddress(new EmailAddress("danix-" + suffix + "@manhattan.com"));
        user.setFirstname(FIRST_NAME + "-" + suffix);
        return user;
    }
}
