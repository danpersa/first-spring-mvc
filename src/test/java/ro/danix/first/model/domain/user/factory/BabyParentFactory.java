package ro.danix.first.model.domain.user.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.danix.first.model.domain.Address;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.UserProfile;
import ro.danix.first.model.domain.factory.AbstractFactory;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
@Component
@Profile(value = "factories")
public class BabyParentFactory extends AbstractFactory<BabyParent> {

    public static final String USERNAME = "babyParent";
    public static final String FIRST_NAME = "baby";
    public static final String LAST_NAME = "parent";
    public static final String EMAIL = "baby.parent@manhattan.com";

    @Override
    protected BabyParent init() {
        BabyParent babyParent = new BabyParent();
        babyParent.setUsername(USERNAME);
        babyParent.setFirstname(FIRST_NAME);
        babyParent.setLastname(LAST_NAME);
        babyParent.setEmailAddress(new EmailAddress(EMAIL));
        babyParent.add(new Address("street", "city", "state", "contry", "zipCode"));
        babyParent.setUserProfile(new UserProfile("name", "website"));
        return babyParent;
    }
}
