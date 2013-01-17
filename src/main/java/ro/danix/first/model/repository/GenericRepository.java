package ro.danix.first.model.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author danix
 */
public interface GenericRepository<T, R extends Serializable> extends MongoRepository<T, R> {
    
    @Override
    public List<T> findAll(Iterable<R> ids);
    
    List<T> findAll(Set<R> ids, Pageable pageable);
}
