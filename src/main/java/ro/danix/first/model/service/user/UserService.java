package ro.danix.first.model.service.user;

import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
public interface UserService extends MongoRepository<User, BigInteger> {

    void follow(User user, User follower);
}
