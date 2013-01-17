package ro.danix.first.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.danix.first.model.domain.user.BabyParent;

/**
 *
 * @author danix
 */
@Document
@EqualsAndHashCode(callSuper=true)
public class Baby extends AbstractDocument {
    
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private DateTime birthDate;
    @Getter
    @Setter
    @DBRef
    private BabyParent babyParent;

    public Baby(String name, DateTime birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    
    protected Baby() {
        
    }
}
