package ro.danix.first.model.service.user.impl;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.user.BabyParent;
import ro.danix.first.model.repository.user.BabyParentRepository;
import ro.danix.first.model.service.impl.GenericServiceImpl;
import ro.danix.first.model.service.user.BabyParentService;

/**
 *
 * @author danix
 */
@Service("babyParentService")
public class BabyParentServiceImpl extends GenericServiceImpl<BabyParent, BigInteger> implements BabyParentService {

    @Autowired(required = true)
    private ExceptionUtils exceptionUtils;

    @Autowired
    public BabyParentServiceImpl(BabyParentRepository babyParentRepository) {
        super(babyParentRepository);
    }

    @Override
    public void follow(BabyParent user, BabyParent follower) {        
        user.addFollower(follower);
        follower.addFollowing(user);
        save(user);
        save(follower);
    }
}
