package ro.danix.first.model.service.user;

import java.math.BigDecimal;
import java.util.List;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
public interface UserService {

    List<User> findAll();

    User findOne(BigDecimal id);

    User save(User user);

    boolean destroy(User user);
    
    void follow(User user, User follower);
}
