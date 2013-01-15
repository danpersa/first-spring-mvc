package ro.danix.first.model.service.impl;

import java.math.BigDecimal;
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
public class BabyServiceImpl extends GenericMongoService<Baby, BigDecimal> implements BabyService {

    @Autowired(required = true)
    private ExceptionUtils exceptionUtils;

    @Autowired
    public BabyServiceImpl(BabyRepository babyRepository) {
        super(babyRepository);
    }
}
