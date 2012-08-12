package ro.danix.first.security;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dan Persa
 */
@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    @Qualifier("userProperties")
    private Properties userProperties;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        String userPropsValue = userProperties.getProperty(username);
        if (userPropsValue == null) {
            throw new UsernameNotFoundException(username
                    + "User does not exist");
        }

        UserAttributeEditor configAttribEd = new UserAttributeEditor();
        configAttribEd.setAsText(userPropsValue);

        UserAttribute userAttributes = (UserAttribute) configAttribEd.getValue();

        return new User(username, userAttributes.getPassword(), userAttributes.isEnabled(), true, true, true, userAttributes.getAuthorities());
    }
    
    public void setUserProperties(Properties userProperties) {
        this.userProperties = userProperties;
    }

    public Properties getUserProperties() {
        return userProperties;
    }
}