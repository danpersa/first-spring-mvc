package ro.danix.first.model.repository;

import java.util.Set;
import org.springframework.data.repository.Repository;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyRepository extends Repository<Baby, Long> {

    Baby findOne(Long id);

    Baby save(Baby user);

    Set<Baby> findByBabyParent(BabyParent babyParent);
}
