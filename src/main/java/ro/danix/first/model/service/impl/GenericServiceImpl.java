package ro.danix.first.model.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ro.danix.first.model.repository.GenericRepository;
import ro.danix.first.model.service.GenericService;

/**
 *
 * @author dpersa
 */
public class GenericServiceImpl<T, R extends Serializable> implements GenericService<T, R> {

    protected final GenericRepository<T, R> genericRepository;

    public GenericServiceImpl(GenericRepository<T, R> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entites) {
        return genericRepository.save(entites);
    }

    @Override
    public <S extends T> S save(S entity) {
        return genericRepository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return genericRepository.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return genericRepository.findAll(pageable);
    }

    @Override
    public Iterable<T> findAll(Iterable<R> ids) {
        return findAll(ids);
    }

    @Override
    public T findOne(R id) {
        return genericRepository.findOne(id);
    }

    @Override
    public boolean exists(R id) {
        return genericRepository.exists(id);
    }

    @Override
    public long count() {
        return genericRepository.count();
    }

    @Override
    public void delete(R id) {
        genericRepository.delete(id);
    }

    @Override
    public void delete(T entity) {
        genericRepository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        genericRepository.delete(entities);
    }

    @Override
    public void deleteAll() {
        genericRepository.deleteAll();
    }
}
