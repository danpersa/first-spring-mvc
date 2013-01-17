package ro.danix.first.model.repository.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.repository.GenericRepository;

/**
 *
 * @author danix
 */
public class GenericMongoRepository<T, R extends Serializable> implements GenericRepository<T, R> {

    protected final MongoOperations operations;

    @Getter
    protected final Class<T> clazz;

    protected ExceptionUtils exceptionUtils = new ExceptionUtils();

    public GenericMongoRepository(MongoOperations operations, Class<T> clazz) {
        this.operations = operations;
        this.clazz = clazz;
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entites) {
        exceptionUtils.argumentShouldNotBeNull(entites);
        List<S> result = new ArrayList<S>();
        for (S entity : entites) {
            save(entity);
            result.add(entity);
        }
        return result;
    }

    @Override
    public <S extends T> S save(S entity) {
        exceptionUtils.argumentShouldNotBeNull(entity);
        operations.save(entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return operations.findAll(clazz);
    }

    @Override
    public List<T> findAll(Sort sort) {
        exceptionUtils.argumentShouldNotBeNull(sort);
        Query query = new Query().with(sort);
        return operations.find(query, clazz);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        exceptionUtils.argumentShouldNotBeNull(pageable);
        Query query = new Query().with(pageable);
        List<T> result = operations.find(query, clazz);
        long count = operations.count(query, clazz);
        return new PageImpl<T>(result, pageable, count);
    }

    @Override
    public List<T> findAll(Iterable<R> ids) {
        exceptionUtils.argumentShouldNotBeNull(ids);
        Criteria criteria = new Criteria();
        for (R id : ids) {
            criteria = criteria.orOperator(where("id").is(id));
        }
        Query query = new Query(criteria);
        return operations.find(query, clazz);
    }
    
    @Override
    public List<T> findAll(Iterable<R> ids, Pageable pageable) {
        exceptionUtils.argumentShouldNotBeNull(ids);
        Criteria criteria = new Criteria();
        for (R id : ids) {
            criteria = criteria.orOperator(where("id").is(id));
        }
        Query query = new Query(criteria);
        return operations.find(query.with(pageable), clazz);
    }

    @Override
    public T findOne(R id) {
        exceptionUtils.argumentShouldNotBeNull(id);
        Query query = query(where("id").is(id));
        return operations.findOne(query, clazz);
    }

    @Override
    public boolean exists(R id) {
        exceptionUtils.argumentShouldNotBeNull(id);
        Query query = query(where("id").is(id));
        long count = operations.count(query, clazz);
        return count != 0l;
    }

    @Override
    public long count() {
        return operations.count(new Query(), clazz);
    }

    @Override
    public void delete(R id) {
        exceptionUtils.argumentShouldNotBeNull(id);
        operations.remove(query(where("id").is(id)), clazz);
    }

    @Override
    public void delete(T entity) {
        exceptionUtils.argumentShouldNotBeNull(entity);
        operations.remove(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        exceptionUtils.argumentShouldNotBeNull(entities);
        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        operations.remove(new Query(), clazz);
    }
}
