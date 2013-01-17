package ro.danix.first.model.repository.user;

import java.math.BigInteger;
import java.util.List;
import org.springframework.data.domain.Pageable;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.GenericRepository;

/**
 *
 * @author danix
 */
public interface UserRepository extends GenericRepository<User, BigInteger> {

    User findByEmailAddress(EmailAddress emailAddress);
    
    List<User> findFollowers(User user, Pageable pageable);
    
    List<User> findFollowing(User user, Pageable pageable);
}
