package ro.danix.first.model.service.user;

import java.math.BigInteger;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.user.BabyParentRepository;
import ro.danix.first.model.service.GenericService;

/**
 *
 * @author danix
 */
public interface BabyParentService extends GenericService<BabyParent, BigInteger>, BabyParentRepository {

    void follow(BabyParent babyParent, BabyParent follower);
}
