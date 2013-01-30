package ro.danix.first.model.service.user.impl;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.first.model.service.impl.GenericServiceImpl;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, BigInteger> implements UserService {

    @Autowired(required = true)
    private ExceptionUtils exceptionUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    protected UserRepository getRepository() {
        return (UserRepository) genericRepository;
    }

    @Override
    public void follow(User user, User follower) {
        exceptionUtils.argumentShouldNotBeNull(user);
        exceptionUtils.argumentShouldNotBeNull(follower);
        user.addFollower(follower);
        follower.addFollowing(user);
        save(user);
        save(follower);
    }

    @Override
    public User findByEmailAddress(EmailAddress emailAddress) {
        return getRepository().findByEmailAddress(emailAddress);
    }

    @Override
    public List<User> findFollowers(User user, Pageable pageable) {
        return getRepository().findFollowers(user, pageable);
    }

    @Override
    public List<User> findFollowing(User user, Pageable pageable) {
        return getRepository().findFollowing(user, pageable);
    }
}
