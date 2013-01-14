package ro.danix.first.model.repository.user.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.repository.user.UserRepository;

/**
 *
 * @author danix
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoOperations operations;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations);
        this.operations = operations;
    }

    @Override
    public User findOne(Long id) {
        Query query = query(where("id").is(id));
        return operations.findOne(query, User.class);
    }

    @Override
    public User save(User user) {
        operations.save(user);
        return user;
    }

    @Override
    public User findByEmailAddress(EmailAddress emailAddress) {
        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, User.class);
    }
}
