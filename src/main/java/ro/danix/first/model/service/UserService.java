package ro.danix.first.model.service;

import java.util.List;
import ro.danix.first.model.businessobject.User;

/**
 *
 * @author danix
 */
public interface UserService {

    List<User> all();

    User get(Long id);

    User save(User user);

    boolean destroy(User user);
}
