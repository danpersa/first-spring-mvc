package ro.danix.first.controller.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author danix
 */
public class UserCreateIn {

    @NotEmpty
    @Size(min = 4, max = 15)
    @Getter
    @Setter
    private String username;

    @Size(max = 15)
    @Getter
    @Setter
    private String firstname;

    @NotEmpty
    @Getter
    @Setter
    private String lastname;

    @Valid
    @NotNull
    @Getter
    @Setter
    private String emailAddress;

}
