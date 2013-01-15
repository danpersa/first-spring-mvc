package ro.danix.first.model.repository.user.mongo;

import java.math.BigDecimal;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.mongo.GenericMongoRepository;
import ro.danix.first.model.repository.user.BabyParentRepository;

/**
 *
 * @author danix
 */
@Repository
public class BabyParentRepositoryImpl extends GenericMongoRepository<BabyParent, BigDecimal> implements BabyParentRepository {

    @Autowired
    public BabyParentRepositoryImpl(MongoOperations operations) {
        super(operations, BabyParent.class);
    }

    @Override
    public BabyParent findByEmailAddress(EmailAddress emailAddress) {
        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, BabyParent.class);
    }
}
