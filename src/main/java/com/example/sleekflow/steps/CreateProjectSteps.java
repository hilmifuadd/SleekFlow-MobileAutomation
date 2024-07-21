package com.example.sleekflow.steps;

import com.example.sleekflow.api.RequestClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class CreateProjectSteps {
    private RequestClient apiClient = new RequestClient("4ae4cf80fe3c6483ad8b0fe100283850f4df80ca");
    private String projectName;
    @Given("a new project named {string}")
    public void aNewProjectNamed(String projectName) {
        this.projectName = projectName;
    }

    @When("I create the project")
    public void iCreateTheProject() {
        apiClient.createProject(projectName);
    }

    @Then("the project should be created")
    public void theProjectShouldBeCreated() {
        // Verify project creation with appropriate API or UI checks
    }
}
