package steps.localization;

import api.RequestClient;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.ThreadLocalDriver;
import io.restassured.response.Response;
import org.testng.Assert;
import pages.localization.ProjectPage;

public class CreateProjectSteps {
    private final RequestClient apiClient;
    public ThreadLocalDriver driver;
    private ProjectPage projectPage;
    private Response response;
    private String projectName;

    public CreateProjectSteps() {
        this.apiClient = new RequestClient("4ae4cf80fe3c6483ad8b0fe100283850f4df80ca");
    }
    @Given("^user on the login page Todoist and want to log in with email$")
    public void user_on_the_login_page_Todoist_and_want_to_log_in_with_email()  {
        projectPage = new ProjectPage(driver);
        projectPage.userChooseToLogin();
    }

    @When("^user enter email \"([^\"]*)\" and password \"([^\"]*)\" and Tap Login$")
    public void user_enter_email_and_password_and_Tap_Login(String email, String password) {
        projectPage = new ProjectPage(driver);
        projectPage.userDoingLogin(email, password);
    }


    @And("^user got successfully logged in and enters to menu browse$")
    public void user_got_successfully_logged_in_and_enters_to_menu_browse() {
        projectPage = new ProjectPage(driver);
        projectPage.clickProject();
    }
    @And("^user create the project on API name \"([^\"]*)\"$")
    public void user_create_the_project_on_api_name(String projectName) {
        this.projectName = projectName;
        response = apiClient.createProject(projectName);
        Assert.assertEquals(response.getStatusCode(), 200, "Unexpected status code: " + response.getStatusCode());
    }
    @Then("^user verified that the project is successfully created$")
    public void user_verified_that_the_project_is_successfully_created(){
        projectPage = new ProjectPage(driver);
        projectPage.testValueIncreasing();
    }
}
