package ro.danix.first.model.domain;

/**
 *
 * @author dpersa
 */
public class Profile extends AbstractDocument {

    private String name, website;

    public Profile(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }
}
