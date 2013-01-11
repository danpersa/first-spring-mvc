package ro.danix.first.security;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import java.util.Properties;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category({FastRunningTests.class})
@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailServiceTest {

    @Mock
    private Properties userProperties;

    @InjectMocks
    private MyUserDetailService myUserDetailService;

    @Test
    public void loadUserByUsernameTest() {
        String username = "danix";
        String password = "password";
        String roleOne = "ROLE_ONE";
        String roleTwo = "ROLE_TWO";

        when(userProperties.getProperty(username)).thenReturn(password + "," + roleOne + "," + roleTwo);

        UserDetails result = myUserDetailService.loadUserByUsername(username);
        assertThat(username, is(result.getUsername()));
        assertThat(password, is(result.getPassword()));
        assertThat(roleOne, is(result.getAuthorities().toArray(new GrantedAuthority[]{})[0].getAuthority()));
        assertThat(roleTwo, is(result.getAuthorities().toArray(new GrantedAuthority[]{})[1].getAuthority()));
        
        verify(userProperties).getProperty(username);
    }
}
