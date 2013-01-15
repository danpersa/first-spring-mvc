package ro.danix.first.model.service.user;

import java.math.BigDecimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
public interface UserService extends MongoRepository<User, BigDecimal> {

    void follow(User user, User follower);
}
