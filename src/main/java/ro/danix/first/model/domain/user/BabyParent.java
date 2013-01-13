package ro.danix.first.model.domain.user;

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
@Document
public class BabyParent extends User {

    private final ExceptionUtils exceptionUtils = new ExceptionUtils();
    @DBRef
    private Set<Baby> babies = new HashSet<Baby>();
    @Getter
    private Long babiesCount = 0l;

    public void addBaby(Baby baby) {
        exceptionUtils.argumentShouldNotBeNull(baby);
        exceptionUtils.documentsShouldNotBeTheSame(this, baby);
        babies.add(baby);
        babiesCount += 1;
    }

    public Set<Baby> getBabies() {
        return Collections.unmodifiableSet(babies);
    }
}
