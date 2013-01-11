package ro.danix.first.model.service;

import java.util.List;
import ro.danix.first.model.domain.User;

/**
 *
 * @author danix
 */
public interface UserService {

    List<User> all();

    User findOne(Long id);

    User save(User user);

    boolean destroy(User user);
    
    void follow(User user, User follower);
}
