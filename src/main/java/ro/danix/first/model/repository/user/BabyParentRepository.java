package ro.danix.first.model.repository.user;

import org.springframework.data.repository.Repository;
import ro.danix.first.model.domain.EmailAddress;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyParentRepository extends Repository<BabyParent, Long> {

    BabyParent findOne(Long id);

    BabyParent save(BabyParent user);

    BabyParent findByEmailAddress(EmailAddress emailAddress);
}
