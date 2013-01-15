package ro.danix.first.model.service;

import java.util.List;
import ro.danix.first.model.domain.Baby;

/**
 *
 * @author danix
 */
public interface BabyService {

    List<Baby> findAll();

    Baby findOne(Long id);

    Baby save(Baby user);

    boolean delete(Baby user);
}
