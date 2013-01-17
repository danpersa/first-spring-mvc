package ro.danix.first.model.domain;

import java.math.BigInteger;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
    private BigInteger id;
}
