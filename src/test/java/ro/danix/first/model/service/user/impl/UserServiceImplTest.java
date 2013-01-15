package ro.danix.first.model.service.user.impl;

import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.domain.user.factory.UserFactory;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.first.model.service.user.UserService;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category(FastRunningTests.class)
@Slf4j
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserFactory userFactory = new UserFactory();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void followTest() {
        // given
        log.info("start followTest");
        System.out.println("follow");
        User user = userFactory.build();
        ReflectionTestUtils.setField(user, "id", new BigInteger("1"));
        User follower = userFactory.build();
        ReflectionTestUtils.setField(follower, "id", new BigInteger("2"));
        UserService instance = new UserServiceImpl(userRepository);
        // when
        instance.follow(user, follower);
        //then

    }
}
