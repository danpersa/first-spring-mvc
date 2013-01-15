package ro.danix.first.model.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.repository.BabyRepository;
import ro.danix.first.model.service.BabyService;

/**
 *
 * @author danix
 */
@Service("babyService")
public class BabyServiceImpl implements BabyService {

    @Autowired
    private ExceptionUtils exceptionUtils;

    @Autowired
    private BabyRepository babyRepository;

    @Override
    public List<Baby> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Baby findOne(BigDecimal id) {
        return babyRepository.findOne(id);
    }

    @Override
    public Baby save(Baby baby) {
        return babyRepository.save(baby);
    }

    @Override
    public boolean delete(Baby baby) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
