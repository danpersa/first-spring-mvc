package ro.danix.first.controller;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author danix
 */
public class HomeControllerUnitTest {

    /**
     * Test of home method, of class HomeController.
     */
    @Test
    public void home() {
        System.out.println("home");
        HomeController instance = new HomeController();
        String expResult = "home";
        String result = instance.home();
        assertEquals(expResult, result);
    }
}