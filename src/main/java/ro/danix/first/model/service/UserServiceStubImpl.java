package ro.danix.first.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import ro.danix.first.model.businessobject.User;

/**
 *
 * @author danix
 */
@Service
public class UserServiceStubImpl implements UserService {

    static final Map<Long, User> map = new HashMap<Long, User>();
    static Long idGenerator = 3l;

    static {
        User user1 = new User();
        user1.setId(1l);
        user1.setName("first user");
        User user2 = new User();
        user2.setId(2l);
        user2.setName("second user");
        map.put(1l, user1);
        map.put(2l, user2);
    }

    @Override
    public List<User> all() {
        return new ArrayList<User>(map.values());
    }

    @Override
    public User get(Long id) {
        return map.get(id);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator++);
        }
        map.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean destroy(User user) {
        if (map.containsKey(user.getId())) {
            map.remove(user.getId());
            return true;
        }
        return false;
    }
}
