package ro.danix.first.model.repository.impl;

import static org.hamcrest.Matchers.*;
import static ro.danix.first.CoreMatchers.*;
import static org.junit.Assert.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.danix.first.model.config.MongoConfig;
import ro.danix.first.model.domain.Address;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.Profile;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.UserRepository;
import ro.danix.test.SlowRunningTests;

/**
 *
 * @author danix
 */
@Category(SlowRunningTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfig.class})
public class UserRepositoryImplIntegrationTest {

    private static final String FIRST_NAME = "danix";

    private static final String LAST_NAME = "danix";

    private static final String EMAIL = "dan@yahoo.com";

    @Autowired
    private Mongo mongo;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        DB database = mongo.getDB(MongoConfig.DATABASE_NAME);
        DBCollection users = database.getCollection("user");
        users.remove(new BasicDBObject());
    }

    @Test
    public void saveTest() {
        User user = buildUser();

        userRepository.save(user);
        User savedUser = userRepository.findByEmailAddress(new EmailAddress((EMAIL)));

        User user1 = new User("danix1", new EmailAddress("danix1@yahoo.com"));
        user1.setFirstname("dan1");
        user1.setLastname("ix1");
        user1.add(new Address("street", "city", "state", "", ""));
        user1.setProfile(new Profile("name", "website"));
        user1.addFollower(savedUser);
        user1.addFollowing(savedUser);

        userRepository.save(user1);

        User userWithFollower = userRepository.findByEmailAddress(new EmailAddress(("danix1@yahoo.com")));
        assertThat(savedUser, is(notNullValue()));
        assertThat(savedUser, is(named(FIRST_NAME)));

        assertThat(userWithFollower, is(notNullValue()));
        assertThat(userWithFollower, is(named("dan1")));
        assertThat(userWithFollower.getFollowers(), hasItem(named(FIRST_NAME)));
        assertThat(userWithFollower.getFollowing(), hasItem(named(FIRST_NAME)));
    }

    @Test
    public void findByNameTest() {
        User user = buildUser();
        userRepository.save(user);

        EmailAddress emailAddress = new EmailAddress(EMAIL);
        user = userRepository.findByEmailAddress(emailAddress);
        assertThat(user, is(notNullValue()));
        assertThat(user, is(named(FIRST_NAME)));
    }

    private User buildUser() {
        User user = new User("danix", new EmailAddress(EMAIL));
        user.setFirstname(FIRST_NAME);
        user.setLastname(LAST_NAME);
        user.add(new Address("street", "city", "state", "contry", "zipCode"));
        user.setProfile(new Profile("name", "website"));
        return user;
    }
}
