package ro.danix.first.model.domain;

import lombok.Getter;

/**
 *
 * @author danix
 */
public class Profile extends AbstractDocument {

    @Getter
    private String name;

    @Getter
    private String website;

    public Profile(String name, String website) {
        this.name = name;
        this.website = website;
    }
}
