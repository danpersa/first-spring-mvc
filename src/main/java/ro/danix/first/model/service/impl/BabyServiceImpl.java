package ro.danix.first.model.service.impl;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.BabyRepository;
import ro.danix.first.model.service.BabyService;

/**
 *
 * @author danix
 */
@Service("babyService")
public class BabyServiceImpl extends GenericServiceImpl<Baby, BigInteger> implements BabyService {

    @Autowired(required = true)
    private ExceptionUtils exceptionUtils;

    @Autowired
    public BabyServiceImpl(BabyRepository babyRepository) {
        super(babyRepository);
    }

    protected BabyRepository getRepository() {
        return (BabyRepository) genericRepository;
    }

    @Override
    public List<Baby> findByBabyParent(BabyParent babyParent) {
        return getRepository().findByBabyParent(babyParent);
    }
}
