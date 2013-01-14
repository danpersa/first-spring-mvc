package ro.danix.first;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import ro.danix.first.model.domain.Baby;
import ro.danix.first.model.domain.user.User;

/**
 * Custom matchers to ease assertions on our domain classes.
 *
 * @author danix
 */
public class CoreMatchers {

    public static <T> Matcher<T> with(Matcher<T> matcher) {
        return matcher;
    }

    public static Matcher<User> firstNamed(String firstname) {
        return hasProperty("firstname", is(firstname));
    }
    
    public static Matcher<Baby> named(String firstname) {
        return hasProperty("name", is(firstname));
    }
}
