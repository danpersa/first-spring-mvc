package ro.danix.first.controller.dto.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ro.danix.first.controller.validation.EmailExistsConstraint;

/**
 *
 * @author danix
 */
@EqualsAndHashCode
public class UserDtoIn {

    @NotEmpty
//    @Size(min = 4, max = 15)
    @Getter
    @Setter
    private String username;

//    @Size(max = 15)
    @Getter
    @Setter
    private String firstname;

    @NotEmpty
    @Getter
    @Setter
    private String lastname;

//    @EmailExistsConstraint
    @NotEmpty
    @Getter
    @Setter
    private String emailAddress;

}
