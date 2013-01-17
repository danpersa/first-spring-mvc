package ro.danix.first.model.domain.user;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ro.danix.first.exception.ExceptionUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;
import ro.danix.first.model.domain.AbstractDocument;
import ro.danix.first.model.domain.Address;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.UserProfile;

/**
 * @author danix
 */
@Document
@Slf4j
public class User extends AbstractDocument {

    private final ExceptionUtils exceptionUtils = new ExceptionUtils();

    @Getter
    @Setter
    @Indexed(unique = true)
    private String username;

    @Size(max = 15)
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
    private UserProfile userProfile;

    private Set<BigInteger> followerIds = new HashSet<BigInteger>();

    @Getter
    private Long followersCount = 0l;

    private Set<BigInteger> followingIds = new HashSet<BigInteger>();

    @Getter
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
        followerIds.add(follower.getId());
        followersCount += 1;
    }

    public void addFollowing(User followingUser) {
        exceptionUtils.argumentShouldNotBeNull(followingUser);
        exceptionUtils.documentsShouldNotBeTheSame(this, followingUser);
        followingIds.add(followingUser.getId());
        followingCount += 1;
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    public Set<BigInteger> getFollowerIds() {
        return Collections.unmodifiableSet(followerIds);
    }

    public Set<BigInteger> getFollowingIds() {
        return Collections.unmodifiableSet(followingIds);
    }
}
