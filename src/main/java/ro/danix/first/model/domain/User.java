package ro.danix.first.model.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

/**
 * @author Dan Persa
 */
@Document
public class User extends AbstractDocument {

    @Size(max = 5)
    private String firstname;

    private String lastname;

    @Field("email")
    @Indexed(unique = true)
    private EmailAddress emailAddress;

    @Field("profile")
    private Profile profile;

    @DBRef
    private Set<User> followers = new HashSet<User>();

    private Set<Address> addresses = new HashSet<Address>();

    public User(String firstname, String lastname) {

        Assert.hasText(firstname);
        Assert.hasText(lastname);

        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected User() {
    }

    public void add(Address address) {

        Assert.notNull(address);
        this.addresses.add(address);
    }

    public void addFollower(User follower) {
        followers.add(follower);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }
}
