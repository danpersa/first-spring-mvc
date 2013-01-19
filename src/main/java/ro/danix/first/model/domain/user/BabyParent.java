package ro.danix.first.model.domain.user;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.danix.first.exception.ExceptionUtils;
import ro.danix.first.model.domain.Baby;

/**
 *
 * @author danix
 */
@Document(collection = User.MONGO_COLLECTION)
public class BabyParent extends User {

    private final ExceptionUtils exceptionUtils = new ExceptionUtils();
        
    private Set<BigInteger> babiesIds = new HashSet<BigInteger>();
    
    @Getter
    private Long babiesCount = 0l;

    public void addBaby(Baby baby) {
        exceptionUtils.argumentShouldNotBeNull(baby);
        exceptionUtils.documentsShouldNotBeTheSame(this, baby);
        exceptionUtils.argumentShouldNotBeNull(baby.getId());
        babiesIds.add(baby.getId());
        babiesCount += 1;
    }

    public Set<BigInteger> getBabiesIds() {
        return Collections.unmodifiableSet(babiesIds);
    }
}
