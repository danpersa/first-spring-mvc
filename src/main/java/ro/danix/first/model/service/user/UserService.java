package ro.danix.first.model.service.user;

import java.math.BigInteger;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.GenericService;

/**
 *
 * @author danix
 */
public interface UserService extends GenericService<User, BigInteger> {

    void follow(User user, User follower);
}
