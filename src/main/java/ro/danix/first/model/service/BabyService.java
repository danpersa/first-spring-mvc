package ro.danix.first.model.service;

import java.math.BigDecimal;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.Baby;

/**
 *
 * @author danix
 */
public interface BabyService extends MongoRepository<Baby, BigDecimal> {
}
