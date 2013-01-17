package ro.danix.first.model.service.user.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.domain.user.factory.UserFactory;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category(FastRunningTests.class)
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExceptionUtils exceptionUtils;

    private UserFactory userFactory = new UserFactory();

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
        // when
        userService.follow(user, follower);
        //then
        assertThat(user.getFollowersCount(), is(new Long(1)));
        assertThat(user.getFollowerIds(), hasItem(follower.getId()));
        
        assertThat(follower.getFollowingCount(), is(new Long(1)));
        assertThat(follower.getFollowingIds(), hasItem(user.getId()));
       
        verify(exceptionUtils, times(2)).argumentShouldNotBeNull(isA(User.class));
    }
}
