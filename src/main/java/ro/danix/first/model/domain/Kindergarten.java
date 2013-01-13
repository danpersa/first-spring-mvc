package ro.danix.first.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danix
 */
@EqualsAndHashCode(callSuper = true)
public class Kindergarten extends AbstractDocument {

    @Getter
    @Setter
    String name;
}
