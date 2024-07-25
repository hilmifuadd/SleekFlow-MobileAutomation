package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.UUID;
public class RequestClient {
    private static final String BASE_URL = "https://api.todoist.com/rest/v2/";
    private String apiToken;
    public RequestClient(String apiToken) {
        this.apiToken = apiToken;
    }
    public Response createProject(String projectName) {
        String requestId = UUID.randomUUID().toString();
        return (Response) (Response) RestAssured
                .given()
                .header("Authorization", "Bearer " + apiToken)
                .header("Content-Type", "application/json")
                .header("X-Request-Id", requestId)
                .body("{\"name\":\"" + projectName + "\"}")
                .post(BASE_URL + "projects")
                .then()
                .extract()
                .response();
    }

    public Response getTasks() {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + apiToken)
                .header("Content-Type", "application/json")
                .get(BASE_URL + "tasks")
                .then()
                .extract()
                .response();
    }

    public Response getTaskById(String taskId) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + apiToken)
                .header("Content-Type", "application/json")
                .get(BASE_URL + "tasks/" + taskId)
                .then()
                .extract()
                .response();
    }


}
