package ro.danix.first.model.repository;

import java.math.BigInteger;
import java.util.List;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyRepository extends GenericRepository<Baby, BigInteger> {

    List<Baby> findByBabyParent(BabyParent babyParent);
}
