package ro.danix.first.model.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.danix.first.model.repository.mongo.GenericMongoRepository;

/**
 *
 * @author dpersa
 */
public class GenericMongoService<T, R extends Serializable> implements MongoRepository<T, R> {

    protected final MongoRepository<T, R> mongoRepository;

    public GenericMongoService(MongoRepository<T, R> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entites) {
        return mongoRepository.save(entites);
    }

    @Override
    public <S extends T> S save(S entity) {
        return mongoRepository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return mongoRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return mongoRepository.findAll(pageable);
    }

    @Override
    public Iterable<T> findAll(Iterable<R> ids) {
        return findAll(ids);
    }

    @Override
    public T findOne(R id) {
        return mongoRepository.findOne(id);
    }

    @Override
    public boolean exists(R id) {
        return mongoRepository.exists(id);
    }

    @Override
    public long count() {
        return mongoRepository.count();
    }

    @Override
    public void delete(R id) {
        mongoRepository.delete(id);
    }

    @Override
    public void delete(T entity) {
        mongoRepository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        mongoRepository.delete(entities);
    }

    @Override
    public void deleteAll() {
        mongoRepository.deleteAll();
    }
}
