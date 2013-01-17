package ro.danix.first.model.repository.user.mongo;

import static org.hamcrest.Matchers.*;
import static ro.danix.first.CoreMatchers.*;
import static org.junit.Assert.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("start saveTest");
        User follower = userFactory.build();
        follower = userRepository.save(follower);

        User user = userFactory.build();
        user.setUsername("danix1");
        user.setEmailAddress(new EmailAddress("danix1@yahoo.com"));
        user.setFirstname("dan1");
        user.addFollower(follower);
        user.addFollowing(follower);

        user = userRepository.save(user);

        assertThat(follower, is(notNullValue()));
        assertThat(follower, is(firstNamed(UserFactory.FIRST_NAME)));

        assertThat(user, is(notNullValue()));
        assertThat(user, is(firstNamed("dan1")));
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

}
