package ro.danix.first.controller.editor;

import java.beans.PropertyEditorSupport;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.service.user.UserService;

/**
 *
 * @author danix
 */
@Slf4j
public class UserEditor extends PropertyEditorSupport {
    
    private UserService userService;

    @Autowired
    public UserEditor(UserService userService) {
        log.debug("Creating UserEditor");
        this.userService = userService;
    }

    @Override
    public String getAsText() {
        User user = (User) getValue();
        return user.getId().toString();
    }

    @Override
    public void setAsText(String string) throws IllegalArgumentException {
        log.info("Start converting string into user: " + string);
        User user = userService.findOne(new BigInteger(string));
        setValue(user);
    }
}
