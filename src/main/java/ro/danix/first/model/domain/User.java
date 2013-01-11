package ro.danix.first.model.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ro.danix.first.exception.ExceptionUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

/**
 * @author danix
 */
@Document
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class User extends AbstractDocument {

    private final ExceptionUtils exceptionUtils = new ExceptionUtils();

    @Getter
    @Setter
    @Indexed(unique = true)
    private String username;

    @Size(max = 5)
    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Field("email")
    @Indexed(unique = true)
    @Getter
    @Setter
    private EmailAddress emailAddress;

    @Field("profile")
    @Getter
    @Setter
    private Profile profile;

    @DBRef
    private Set<User> followers = new HashSet<User>();

    @Getter
    @Setter
    private Long followersCount = 0l;

    @DBRef
    private Set<User> following = new HashSet<User>();

    @Getter
    @Setter
    private Long followingCount = 0l;

    private Set<Address> addresses = new HashSet<Address>();

    public User(String username, EmailAddress emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    protected User() {
    }

    public void add(Address address) {
        Assert.notNull(address);

        this.addresses.add(address);
    }

    public void addFollower(User follower) {
        exceptionUtils.argumentShouldNotBeNull(follower);
        exceptionUtils.documentsShouldNotBeTheSame(this, follower);
        followers.add(follower);
        followersCount += 1;
    }

    public void addFollowing(User followingUser) {
        exceptionUtils.argumentShouldNotBeNull(followingUser);
        exceptionUtils.documentsShouldNotBeTheSame(this, followingUser);
        following.add(followingUser);
        followingCount += 1;
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    public Set<User> getFollowers() {
        return Collections.unmodifiableSet(followers);
    }

    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }
}
