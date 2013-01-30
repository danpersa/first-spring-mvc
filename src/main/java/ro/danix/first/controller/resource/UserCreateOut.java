package ro.danix.first.controller.resource;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danix
 */
public class UserCreateOut extends UserCreateIn {

    @Getter
    @Setter
    private BigInteger id;

}
