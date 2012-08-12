package cucumber.features;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class LoginStepdefs {

    private final WebDriver webDriver;

    public LoginStepdefs(SharedDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Given("^I am on the login page$")
    public void I_am_on_the_login_page() {
        webDriver.get("http://localhost:" + ServerHooks.PORT + ServerHooks.CONTEXT + "/login.html");
    }

    @When("^I enter (.+) as username$")
    public void I_enter_danix_as_username(String username) {
        webDriver.findElement(By.id("j_username")).sendKeys(username);
    }

    @When("^I enter (.+) as password$")
    public void I_enter_danix_as_password(String password) {
        webDriver.findElement(By.id("j_password")).sendKeys(password);
    }

    @When("^I press the Login to Web Example button$")
    public void I_press_the_Login_to_Login_button() {
        webDriver.findElement(By.id("login-button")).click();
    }

    @Then("^I should see Logout$")
    public void I_should_see_Logout() {
        assertEquals("Logout", webDriver.findElement(By.id("logout-link")).getText());
    }
}
