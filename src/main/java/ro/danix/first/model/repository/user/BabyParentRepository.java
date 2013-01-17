package ro.danix.first.model.repository.user;

import java.math.BigInteger;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.GenericRepository;

/**
 *
 * @author danix
 */
public interface BabyParentRepository extends GenericRepository<BabyParent, BigInteger> {

    BabyParent findByEmailAddress(EmailAddress emailAddress);
}
