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

import java.util.List;

public class CreateProjectSteps {
    private final RequestClient apiClient;
    public ThreadLocalDriver driver;
    private ProjectPage projectPage;
    private Response response;
    private String projectName;
    private String taskName;
    private static final ThreadLocal<Integer> initialTaskCount = new ThreadLocal<>();
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
    @And("^user create a task in mobile name \"([^\"]*)\"$")
    public void user_create_a_task_in_mobile_name(String taskName) {
        Response response = apiClient.getTasks();
        List<String> taskContentsBefore = response.jsonPath().getList("id");
        System.out.println("Tasks in response: " + taskContentsBefore);
        int initialTaskCountValue = taskContentsBefore.size();
        initialTaskCount.set(initialTaskCountValue);
        projectPage = new ProjectPage(driver);
        projectPage.createTask(taskName);
    }

    @And("^user verified that the task is successfully created$")
    public void user_verified_that_the_task_is_successfully_created(){
        Response responseAfter = apiClient.getTasks();
        List<String> taskContentsAfter = responseAfter.jsonPath().getList("id");
        System.out.println("Tasks in response: " + taskContentsAfter);
        int newTaskCount = taskContentsAfter.size();
        Assert.assertEquals(newTaskCount, initialTaskCount.get() + 1, "Task count did not increase by 1");
    }
}
