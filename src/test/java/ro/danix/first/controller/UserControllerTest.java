package ro.danix.first.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;
import java.math.BigInteger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.domain.user.factory.UserFactory;
import ro.danix.first.model.service.user.UserService;
import ro.danix.test.FastRunningTests;

/**
 *
 * @author danix
 */
@Category({FastRunningTests.class})
public class UserControllerTest {

    private UserService userService;

    private MockMvc mockMvc;

    private UserFactory userFactory = new UserFactory();

    @Before
    public void setup() {
        userService = mock(UserService.class);
        UserController userController = new UserController(userService);
        User user = userFactory.build();
        when(userService.findOne(BigInteger.ZERO)).thenReturn(user);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void printInfo() throws Exception {
        mockMvc
                .perform(get("/users/0"))
                .andDo(print());
    }

    @Test
    public void shouldGetTestUserAsJson() throws Exception {
        mockMvc
                .perform(get("/users/0")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("username", is(UserFactory.USERNAME)))
                .andExpect(jsonPath("lastname", is(UserFactory.LAST_NAME)));
    }
}