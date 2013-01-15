package ro.danix.first.model.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
public interface BabyRepository extends MongoRepository<Baby, BigDecimal> {

    List<Baby> findByBabyParent(BabyParent babyParent);
}
