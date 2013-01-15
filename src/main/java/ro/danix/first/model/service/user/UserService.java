package ro.danix.first.model.service.user;

import java.util.List;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
public interface UserService {

    List<User> findAll();

    User findOne(Long id);

    User save(User user);

    boolean destroy(User user);
    
    void follow(User user, User follower);
}
