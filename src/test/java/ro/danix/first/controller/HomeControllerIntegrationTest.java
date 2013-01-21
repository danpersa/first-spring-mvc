package ro.danix.first.controller;

import org.junit.*;
import org.junit.experimental.categories.Category;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.danix.first.config.ApplicationConfig;
import ro.danix.first.config.WebConfig;
import ro.danix.first.model.config.MongoConfig;
import ro.danix.test.SlowRunningTests;

/**
 *
 * @author danix
 */
@Ignore
@Category({SlowRunningTests.class})
public class HomeControllerIntegrationTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
//        this.mockMvc = MockMvcBuilders.annotationConfigSetup(ApplicationConfig.class, WebConfig.class, MongoConfig.class)
//                .configureWebAppRootDir("src/main/webapp", false).build();
    }

    @Test
    public void tilesDefinitions() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/layouts/standard.jsp"));
    }
}