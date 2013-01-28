package ro.danix.first.model.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danix
 */
public class UserProfile {

    @Getter
    private String name;

    @Getter
    private String website;

    protected UserProfile() {
    }

    public UserProfile(String name, String website) {
        this.name = name;
        this.website = website;
    }
}
