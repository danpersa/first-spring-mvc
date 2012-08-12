package ro.danix.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author danix
 */
@Controller
public class StaticPagesController {

    public final static String INDEX_VIEW = "index";
    public final static String LOGIN_VIEW = "login";
    public final static String ROLE_USER_VIEW = "secured-user";
    public final static String ROLE_ANONYMOUS_VIEW = "secured-anonymous";
    public final static String ACCESS_DENIED_VIEW = "access-denied";

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return INDEX_VIEW;
    }
    
    @RequestMapping(value = "/secured-user.html", method = RequestMethod.GET)
    public String securedUser() {
        return ROLE_USER_VIEW;
    }
    
    @RequestMapping(value = "/secured-anonymous.html", method = RequestMethod.GET)
    public String securedAnonymous() {
        return ROLE_ANONYMOUS_VIEW;
    }
    
    @RequestMapping(value = "/access-denied.html", method = RequestMethod.GET)
    public String accessDenied() {
        return ACCESS_DENIED_VIEW;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        return LOGIN_VIEW;
    }
}
