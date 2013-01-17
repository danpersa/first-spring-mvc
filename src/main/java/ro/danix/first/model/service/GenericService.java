package ro.danix.first.model.service;

import java.io.Serializable;
import ro.danix.first.model.repository.GenericRepository;

/**
 *
 * @author danix
 */
public interface GenericService<T, R extends Serializable> extends GenericRepository<T, R> {
}
