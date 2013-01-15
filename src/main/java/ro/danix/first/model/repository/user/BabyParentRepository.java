package ro.danix.first.model.repository.user;

import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyParentRepository extends MongoRepository<BabyParent, BigInteger> {

    BabyParent findByEmailAddress(EmailAddress emailAddress);
}
