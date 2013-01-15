package ro.danix.first.model.service.user.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.user.UserRepository;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author dpersa
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private ExceptionUtils exceptionUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findOne(BigDecimal id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean destroy(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
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
