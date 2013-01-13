package ro.danix.first.model.domain;

import java.util.Calendar;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danix
 */
@EqualsAndHashCode(callSuper=true)
public class Baby extends AbstractDocument {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Calendar birthDate;
}
