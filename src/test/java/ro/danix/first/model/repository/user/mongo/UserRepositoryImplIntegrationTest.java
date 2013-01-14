package ro.danix.first.model.repository.user.mongo;

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
        User user = userFactory.build();

        userRepository.save(user);
        User savedUser = userRepository.findByEmailAddress(new EmailAddress((UserFactory.EMAIL)));

        User user1 = userFactory.build();
        user1.setUsername("danix1");
        user1.setEmailAddress(new EmailAddress("danix1@yahoo.com"));
        user1.setFirstname("dan1");
        user1.addFollower(savedUser);
        user1.addFollowing(savedUser);

        userRepository.save(user1);

        User userWithFollower = userRepository.findByEmailAddress(new EmailAddress(("danix1@yahoo.com")));
        assertThat(savedUser, is(notNullValue()));
        assertThat(savedUser, is(firstNamed(UserFactory.FIRST_NAME)));

        assertThat(userWithFollower, is(notNullValue()));
        assertThat(userWithFollower, is(firstNamed("dan1")));
        assertThat(userWithFollower.getFollowers(), hasItem(firstNamed(UserFactory.FIRST_NAME)));
        assertThat(userWithFollower.getFollowing(), hasItem(firstNamed(UserFactory.FIRST_NAME)));
    }

    @Test
    public void findByEmailAddressTest() {
        User user = userFactory.build();
        userRepository.save(user);

        EmailAddress emailAddress = new EmailAddress(UserFactory.EMAIL);
        user = userRepository.findByEmailAddress(emailAddress);
        assertThat(user, is(notNullValue()));
        assertThat(user, is(firstNamed(UserFactory.FIRST_NAME)));
    }

}
