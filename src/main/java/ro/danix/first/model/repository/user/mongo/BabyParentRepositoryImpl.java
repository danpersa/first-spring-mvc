package ro.danix.first.model.repository.user.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.user.BabyParentRepository;

/**
 *
 * @author danix
 */
@Repository
public class BabyParentRepositoryImpl implements BabyParentRepository {

    private final MongoOperations operations;

    @Autowired
    public BabyParentRepositoryImpl(MongoOperations operations) {
        Assert.notNull(operations);
        this.operations = operations;
    }

    @Override
    public BabyParent findOne(Long id) {
        Query query = query(where("id").is(id));
        return operations.findOne(query, BabyParent.class);
    }

    @Override
    public BabyParent save(BabyParent babyParent) {
        operations.save(babyParent);
        return babyParent;
    }

    @Override
    public BabyParent findByEmailAddress(EmailAddress emailAddress) {
        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, BabyParent.class);
    }
}
