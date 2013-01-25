package ro.danix.first.model.domain;

import lombok.Getter;

/**
 *
 * @author danix
 */
public class UserProfile {

    @Getter
    private String name;

    @Getter
    private String website;

    public UserProfile(String name, String website) {
        this.name = name;
        this.website = website;
    }
}
