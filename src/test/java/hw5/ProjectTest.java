package hw5;

import static io.restassured.RestAssured.given;

import hw5.Project.Project;
import hw5.Project.ProjectResponse;
import hw5.Project.Status;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectTest {

    private Properties properties;
    private String baseUri;
    private String token;
    private Header header;
    private Project project;
    private Project subproject;
    private Status status;


    @BeforeClass(alwaysRun = true)
    public void setUps() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resourses/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUri = properties.getProperty("uri");
        token = properties.getProperty("token");
        header = new Header("Authorization", token);
    }

    @Test
    public void projectTest() {
        status = new Status(0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        project = new Project(0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                status);

        subproject = new Project(0,
                properties.getProperty("subproject.name"),
                properties.getProperty("project.description"),
                status);

        ProjectResponse response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(project)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(ProjectResponse.class);

        project = response.getProject();

        response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(subproject)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(ProjectResponse.class);

        subproject = response.getProject();

        JSONObject subJson = new JSONObject();
        subJson.put("name", subproject.getName());

        JSONObject object = new JSONObject();
        object.put("project",subJson);
        object.put("inherit_parent", true);


        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON).
                body(object.toString())
        .when().
                post("/projects/" + project.getId() + "/subprojects")
        .then().
                statusCode(204);

        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);

        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + subproject.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void deleteProjectErrorTest() {
        given().
                baseUri(baseUri).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/12345")
                .then().
                statusCode(403);
    }
}
