package ro.danix.first.model.service;

import java.math.BigInteger;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.repository.BabyRepository;

/**
 *
 * @author danix
 */
public interface BabyService extends GenericService<Baby, BigInteger>, BabyRepository {
}
