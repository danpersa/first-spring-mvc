package ro.danix.first.model.repository.mongo;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.BabyRepository;

/**
 *
 * @author danix
 */
@Repository("babyRepository")
public class BabyRepositoryImpl extends GenericMongoRepository<Baby, BigInteger> implements BabyRepository {

    @Autowired
    public BabyRepositoryImpl(MongoOperations operations) {
        super(operations , Baby.class, BigInteger.class);
    }

    @Override
    public List<Baby> findByBabyParent(BabyParent babyParent) {
        Query query = query(where("babyParent").is(babyParent));
        return operations.find(query, Baby.class);
    }
}
