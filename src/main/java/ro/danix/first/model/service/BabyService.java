package ro.danix.first.model.service;

import java.math.BigInteger;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.domain.Baby;

/**
 *
 * @author danix
 */
public interface BabyService extends MongoRepository<Baby, BigInteger> {
}
