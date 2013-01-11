package cucumber.features;

import cucumber.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@Ignore
@RunWith(Cucumber.class)
@Cucumber.Options(format = "html:target/cukes")
public class RunCukesTest {
    
    @BeforeClass
    public static void before() {
        System.setProperty("webdriver.chrome.driver", "/home/dix/pf/sdk/chromedriver");
    }    
}
