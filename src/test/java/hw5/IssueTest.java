package hw5;

import hw5.Issue.*;
import hw5.Project.Project;
import hw5.Project.ProjectResponse;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class IssueTest {

    private Properties properties;
    private String baseUrl;
    private String token;
    private Header header;
    private Project project;
    private hw5.Project.Status projectStatus;
    private Files firstFile;
    private Files secondFile;
    private IssueField category;
    private LabeledIssueField severity;
    private LabeledIssueField priority;
    private Status status;
    private IssueWithFiles issueWithAttachments;

    @BeforeClass
    public void setUp() throws IOException {
        FileInputStream propertiesFile = new FileInputStream("src/test/resourses/hw5.properties");
        properties = new Properties();
        properties.load(propertiesFile);
        baseUrl = properties.getProperty("uri");
        token = properties.getProperty("token");
        header = new Header("Authorization", token);

        projectStatus = new hw5.Project.Status(0,
                properties.getProperty("project.status"),
                properties.getProperty("project.status"));

        project = new Project(0,
                properties.getProperty("project.name"),
                properties.getProperty("project.description"),
                projectStatus);

        firstFile =
                new Files(properties.getProperty("file.nameOne"),
                        properties.getProperty("file.content"));
        secondFile =
                new Files(properties.getProperty("file.nameTwo"),
                        properties.getProperty("file.content"));

        category =
                new IssueField(Integer.parseInt(properties.getProperty("category.id")),
                        properties.getProperty("category.name"));

        severity =
                new LabeledIssueField(Integer.parseInt(properties.getProperty("severity.id")),
                        properties.getProperty("severity.name"),
                        properties.getProperty("severity.name"));

        priority =
                new LabeledIssueField(Integer.parseInt(properties.getProperty("priority.id")),
                        properties.getProperty("priority.name"),
                        properties.getProperty("priority.name"));

        status =
                new Status(Integer.parseInt(properties.getProperty("status.id")),
                        properties.getProperty("status.name"),
                        properties.getProperty("status.name"),
                        properties.getProperty("status.color"));
    }

    @Test
    public void createUpdateDeleteIssue() {

        ProjectResponse projectResponce = given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .body(project)
                .when().
                        post("/projects/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().response().as(ProjectResponse.class);

        project = projectResponce.getProject();

        Issue issue =
                new Issue(
                        0,
                        properties.getProperty("issue.summary"),
                        properties.getProperty("issue.description"),
                        project,
                        category,
                        severity,
                        priority,
                        status
                );
        issueWithAttachments =
                new IssueWithFiles(
                        issue,
                        new Files[]{firstFile, secondFile}
                );


        IssueResponse issueResponse =
                given().
                        baseUri(baseUrl).
                        header(header).
                        contentType(ContentType.JSON).
                        body(issueWithAttachments)
                        .when().
                        post("/issues/")
                        .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                        .extract().response().as(IssueResponse.class);

        issue = issueResponse.getIssue();

        JSONObject issueJson = new JSONObject();
        issueJson.put("summary", properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/" + issue.getId())
                .then().
                contentType(ContentType.JSON).
                statusCode(200);

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON)
                .when().
                delete("/projects/" + project.getId())
                .then().
                statusCode(200);
    }

    @Test
    public void updateNonExistingIssue() {

        JSONObject issueJson = new JSONObject();
        issueJson.put("summary", properties.getProperty("issue.updated_summary"));

        given().
                baseUri(baseUrl).
                header(header).
                contentType(ContentType.JSON).
                body(issueJson.toString())
                .when().
                patch("/issues/90000")
                .then().
                contentType(ContentType.JSON).
                statusCode(404);
    }

}
