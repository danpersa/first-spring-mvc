package ro.danix.first.model.repository.user;

import java.math.BigInteger;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.GenericRepository;

/**
 *
 * @author danix
 */
public interface UserRepository extends GenericRepository<User, BigInteger> {

    User findByEmailAddress(EmailAddress emailAddress);
}
