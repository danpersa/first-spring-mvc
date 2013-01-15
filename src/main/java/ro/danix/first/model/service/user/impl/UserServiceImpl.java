package ro.danix.first.model.service.user.impl;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.first.model.service.impl.GenericMongoService;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Service("userService")
public class UserServiceImpl extends GenericMongoService<User, BigInteger> implements UserService {

    @Autowired(required = true)
    private ExceptionUtils exceptionUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
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
}
