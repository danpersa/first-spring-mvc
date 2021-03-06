package ro.danix.first.model.repository.user.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.mongo.GenericMongoRepository;
import ro.danix.first.model.repository.user.UserRepository;

/**
 *
 * @author danix
 */
@Repository("userRepository")
public class UserRepositoryImpl extends GenericMongoRepository<User, BigInteger> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        super(operations, User.class, BigInteger.class);
    }

    @Override
    public User findByEmailAddress(EmailAddress emailAddress) {
        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, User.class);
    }

    @Override
    public List<User> findFollowers(User user, Pageable pageable) {
        return findAll(user.getFollowerIds(), pageable);
    }

    @Override
    public List<User> findFollowing(User user, Pageable pageable) {
        return findAll(user.getFollowingIds(), pageable);
    }
}
