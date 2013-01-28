package ro.danix.first.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.FieldError;
import ro.danix.first.controller.dto.FormValidationErrorDTO;
import ro.danix.first.controller.exception.FormValidationError;
import ro.danix.first.controller.util.ValidationUtils;
import ro.danix.first.model.domain.user.User;
import ro.danix.first.model.domain.user.factory.UserFactory;
import ro.danix.first.model.service.user.UserService;
import ro.danix.test.FastRunningTests;
import ro.danix.test.IntegrationTestUtil;

/**
 *
 * @author danix
 */
@Category({FastRunningTests.class})
@Slf4j
public class UserControllerTest {

    private UserService userService;

    private MockMvc mockMvc;

    private ValidationUtils validationUtils;

    private UserFactory userFactory = new UserFactory();

    @Before
    public void setup() {
        userService = mock(UserService.class);
        validationUtils = mock(ValidationUtils.class);
        UserController userController = new UserController(userService);
        ReflectionTestUtils.setField(userController, "validationUtils", validationUtils);
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
    public void showTest() throws Exception {
        mockMvc
                .perform(get("/users/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("username", is(UserFactory.USERNAME)))
                .andExpect(jsonPath("lastname", is(UserFactory.LAST_NAME)));
    }

    @Test
    public void createSuccessTest() throws Exception {
        User user = new User(null, null);
        User savedUser = userFactory.build();
        savedUser.setId(BigInteger.ONE);
        when(userService.save(user)).thenReturn(savedUser);

        log.debug("Send: " + IntegrationTestUtil.convertObjectToJson(user));
        mockMvc.perform(post("/users")
                .contentType(IntegrationTestUtil.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON))
                .andExpect(content().string(startsWith("{\"id\":1")))
                .andExpect(jsonPath("id", is(new Integer(1))))
                .andExpect(jsonPath("username", is(UserFactory.USERNAME)))
                .andExpect(jsonPath("lastname", is(UserFactory.LAST_NAME)));
        verify(validationUtils).validate("user", user);
        verify(userService).save(user);
    }

    @Test
    public void createFailureTest() throws Exception {
        User user = new User(null, null);
        User savedUser = userFactory.build();
        savedUser.setId(BigInteger.ONE);
        
        when(userService.save(user)).thenReturn(savedUser);
        List<FieldError> fieldErrors = new ArrayList<FieldError>();
        FormValidationError formValidationError = new FormValidationError(fieldErrors);
        doThrow(formValidationError).when(validationUtils).validate(isA(String.class), isA(User.class));
        FormValidationErrorDTO formValidationErrorDTO = new FormValidationErrorDTO();
        formValidationErrorDTO.addFieldError("user.username", "should not be null");
        when(validationUtils.handleFormValidationError(formValidationError)).thenReturn(formValidationErrorDTO);

        log.debug("Send: " + IntegrationTestUtil.convertObjectToJson(user));
        mockMvc.perform(post("/users")
                .contentType(IntegrationTestUtil.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(content().contentType(IntegrationTestUtil.APPLICATION_JSON))
                .andExpect(content().string(startsWith("{\"fieldErrors\":")))
                .andExpect(jsonPath("$['fieldErrors'][0]['path']", is("user.username")))
                .andExpect(jsonPath("$['fieldErrors'][0]['message']", is("should not be null")));
        verify(validationUtils).validate("user", user);
        verifyZeroInteractions(userService);
        verify(validationUtils).handleFormValidationError(formValidationError);
    }
}