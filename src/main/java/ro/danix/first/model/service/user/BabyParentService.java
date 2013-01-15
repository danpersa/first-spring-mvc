package ro.danix.first.model.service.user;

import java.math.BigDecimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyParentService extends MongoRepository<BabyParent, BigDecimal> {

    void follow(BabyParent babyParent, BabyParent follower);
}
