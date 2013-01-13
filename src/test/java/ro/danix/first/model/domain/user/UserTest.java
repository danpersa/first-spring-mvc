package ro.danix.first.model.domain.user;

import ro.danix.first.model.domain.user.User;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
        User instance = new User();
        instance.addFollower(follower);

        assertThat(instance.getFollowers(), hasItem(follower));
        assertThat(instance.getFollowersCount(), is(new Long(1)));
    }

    @Test
    public void addFollowingTest() {
        log.info("start addFollowingTest");
        User followimg = new User();
        User instance = new User();
        instance.addFollowing(followimg);

        assertThat(instance.getFollowing(), hasItem(followimg));
        assertThat(instance.getFollowingCount(), is(new Long(1)));
    }
}
