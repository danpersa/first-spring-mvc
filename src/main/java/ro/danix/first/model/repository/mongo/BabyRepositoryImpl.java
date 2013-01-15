package ro.danix.first.model.repository.mongo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.BabyRepository;

/**
 *
 * @author danix
 */
@Repository
public class BabyRepositoryImpl extends GenericMongoRepository<Baby, BigDecimal> implements BabyRepository {

    @Autowired
    public BabyRepositoryImpl(MongoOperations operations) {
        super(operations , Baby.class);
    }

    @Override
    public List<Baby> findByBabyParent(BabyParent babyParent) {
        Query query = query(where("babyParent").is(babyParent));
        return operations.find(query, Baby.class);
    }
}
