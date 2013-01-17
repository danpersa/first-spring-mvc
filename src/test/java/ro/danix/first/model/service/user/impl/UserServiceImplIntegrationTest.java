package ro.danix.first.model.service.user.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
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
import ro.danix.first.model.service.user.UserService;
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
public class UserServiceImplIntegrationTest {
    public static final String FOLLOWER_EMAIL = "danix1@yahoo.com";

    @Autowired
    private Mongo mongo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;
    
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        DB database = mongo.getDB(MongoConfig.DATABASE_NAME);
        DBCollection users = database.getCollection("user");
        users.remove(new BasicDBObject());
    }

    @Test
    public void followTest() {
        // given
        log.info("start followTest");
        User user = userFactory.build();
        user = userRepository.save(user);
        
        User follower = userFactory.build();
        follower.setUsername("danix1");
        follower.setEmailAddress(new EmailAddress(FOLLOWER_EMAIL));
        follower = userRepository.save(follower);
        // when
        userService.follow(user, follower);
        // then
        user = userRepository.findByEmailAddress(new EmailAddress(UserFactory.EMAIL));
        follower = userRepository.findByEmailAddress(new EmailAddress(FOLLOWER_EMAIL));
        
        assertThat(user.getFollowersCount(), is(new Long(1)));
        assertThat(user.getFollowerIds(), hasItem(follower.getId()));
        
        assertThat(follower.getFollowingCount(), is(new Long(1)));
        assertThat(follower.getFollowingIds(), hasItem(user.getId()));       
    }
}
