package ro.danix.first.model.repository.user.mongo;

import static org.hamcrest.Matchers.*;
import static ro.danix.first.CoreMatchers.*;
import static org.junit.Assert.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.danix.first.model.config.FactoriesConfig;
import ro.danix.first.model.config.MongoConfig;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.domain.user.factory.UserFactory;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.test.SlowRunningTests;

/**
 *
 * @author danix
 */
@Category(SlowRunningTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class, FactoriesConfig.class})
@ActiveProfiles(profiles = "factories")
@Slf4j
public class UserRepositoryImplIntegrationTest {

    @Autowired
    private Mongo mongo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Before
    public void setUp() {
        DB database = mongo.getDB(MongoConfig.DATABASE_NAME);
        DBCollection users = database.getCollection("user");
        users.remove(new BasicDBObject());
    }

    @Test
    public void saveTest() {
        // given
        log.info("start saveTest");
        User follower = userFactory.build();
        follower = userRepository.save(follower);

        User user = userFactory.build("1");
        user.addFollower(follower);
        user.addFollowing(follower);
        // when
        user = userRepository.save(user);
        // then
        assertThat(follower, is(notNullValue()));
        assertThat(follower, is(firstNamed(UserFactory.FIRST_NAME)));

        assertThat(user, is(notNullValue()));
        assertThat(user, is(firstNamed(UserFactory.FIRST_NAME + "-1")));
        assertThat(user.getFollowerIds(), hasItem(follower.getId()));
        assertThat(user.getFollowingIds(), hasItem(follower.getId()));
    }

    @Test
    public void findByEmailAddressTest() {
        log.info("start findByEmailAddressTest");
        User user = userFactory.build();
        userRepository.save(user);

        EmailAddress emailAddress = new EmailAddress(UserFactory.EMAIL);
        user = userRepository.findByEmailAddress(emailAddress);
        assertThat(user, is(notNullValue()));
        assertThat(user, is(firstNamed(UserFactory.FIRST_NAME)));
    }
    
    @Test
    public void findFollowersTest() {
        log.info("start findFollowersTest");
        User user = userFactory.build();
       
        List<BigInteger> followerIds = new ArrayList<BigInteger>();
        for (int i = 0; i < 5; ++i) {
            User follower = userFactory.build(new Integer(i).toString());
            follower = userRepository.save(follower);
            followerIds.add(follower.getId());
            user.addFollower(follower);
        }
        user = userRepository.save(user);
        // when
        List<User> followers = userRepository.findFollowers(user, new PageRequest(0, UserRepository.NUMBER_OF_USERS_PER_PAGE));
        // 
        assertThat(followers, is(notNullValue()));
        assertThat(followers.size(), is(5));
        for (BigInteger followerId : followerIds) {
            User follower = userRepository.findOne(followerId);
            assertThat(followers, hasItem(follower));
        }
    }
}
