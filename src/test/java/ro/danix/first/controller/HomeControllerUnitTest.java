package ro.danix.first.controller;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category({FastRunningTests.class})
public class HomeControllerUnitTest {

    @Test
    public void home() {
        HomeController instance = new HomeController();
        String expResult = "login";
        String result = instance.home();
        assertEquals(expResult, result);
    }
}