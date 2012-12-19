package ro.danix.first.model.repository;

import org.springframework.data.repository.Repository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.User;

/**
 *
 * @author Dan Persa
 */
public interface UserRepository extends Repository<User, Long> {

    User findOne(Long id);

    User save(User user);

    User findByEmailAddress(EmailAddress emailAddress);
}
