package ro.danix.first.model.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author danix
 */
public interface GenericRepository<T, R extends Serializable> extends MongoRepository<T, R> {

}
