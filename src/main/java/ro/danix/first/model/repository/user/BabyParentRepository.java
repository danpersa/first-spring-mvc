package ro.danix.first.model.repository.user;

import java.math.BigDecimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyParentRepository extends MongoRepository<BabyParent, BigDecimal> {

    BabyParent findByEmailAddress(EmailAddress emailAddress);
}
