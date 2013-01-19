package ro.danix.first.model.domain.user;

import java.math.BigInteger;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import ro.danix.first.model.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.test.util.ReflectionTestUtils;
import ro.danix.first.model.domain.Address;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category(FastRunningTests.class)
@Slf4j
public class UserTest {

    @Test
    public void addTest() {
        log.info("start addTest");
        Address address = new Address("street", "city", "state", "country", "zipCode");
        User instance = new User();
        instance.add(address);
        assertThat(instance.getAddresses(), hasItem(address));
    }

    @Test
    public void addFollowerTest() {
        log.info("start addFollowerTest");
        User follower = new User();
        ReflectionTestUtils.setField(follower, "id", new BigInteger("1"));
        User instance = new User();
        ReflectionTestUtils.setField(instance, "id", new BigInteger("2"));
        instance.addFollower(follower);

        assertThat(instance.getFollowerIds(), hasItem(follower.getId()));
        assertThat(instance.getFollowersCount(), is(new Long(1)));
    }

    @Test
    public void addFollowingTest() {
        log.info("start addFollowingTest");
        User following = new User();
        ReflectionTestUtils.setField(following, "id", new BigInteger("1"));
        User instance = new User();
        ReflectionTestUtils.setField(instance, "id", new BigInteger("2"));
        instance.addFollowing(following);

        assertThat(instance.getFollowingIds(), hasItem(following.getId()));
        assertThat(instance.getFollowingCount(), is(new Long(1)));
    }
}
