package ro.danix.first.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.businessobject.User;

/**
 *
 * @author Dan Persa
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByUsername(String username);
}
