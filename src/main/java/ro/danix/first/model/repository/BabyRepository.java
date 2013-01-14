package ro.danix.first.model.repository;

import java.util.List;
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

    List<Baby> findByBabyParent(BabyParent babyParent);
}
