package ro.danix.first.controller.dto.user;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author danix
 */
public class UserDtoOut extends UserDtoIn {

    @Getter
    @Setter
    private BigInteger id;

}
