package ro.danix.first.model.domain;

import java.math.BigInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;

/**
 * Base class for document classes.
 *
 * @author Oliver Gierke
 */
@EqualsAndHashCode
public class AbstractDocument {

    @Id
    @Getter
    @Setter
    private BigInteger id;
}
