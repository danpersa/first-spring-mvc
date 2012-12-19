package ro.danix.first;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import ro.danix.first.model.domain.User;

/**
 * Custom matchers to ease assertions on our domain classes.
 *
 * @author Oliver Gierke
 */
public class CoreMatchers {

    /**
     * Syntactic sugar to make Matchers more readable.
     *
     * @param matcher must not be {@literal null}.
     * @return
     */
    public static <T> Matcher<T> with(Matcher<T> matcher) {
        return matcher;
    }

    /**
     * Matches if the {@link Product} has the given name.
     *
     * @param firstname must not be {@literal null}.
     * @return
     */
    public static Matcher<User> named(String firstname) {
        return hasProperty("firstname", is(firstname));
    }
}
