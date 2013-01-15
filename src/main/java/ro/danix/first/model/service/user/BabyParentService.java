package ro.danix.first.model.service.user;

import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyParentService extends MongoRepository<BabyParent, BigInteger> {

    void follow(BabyParent babyParent, BabyParent follower);
}
