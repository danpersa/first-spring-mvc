package ro.danix.first.model.repository.user;

import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;

/**
 *
 * @author danix
 */
public interface UserRepository extends MongoRepository<User, BigInteger> {

    User findByEmailAddress(EmailAddress emailAddress);
}
