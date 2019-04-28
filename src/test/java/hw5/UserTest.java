package hw5;

import static io.restassured.RestAssured.given;

import hw5.User.User;
import hw5.User.UserResponse;
import io.restassured.http.Header;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserTest {

    private Properties properties;
    private String baseUri;
    private String token;
    private User user;
    private Header header;

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
    public void userApiTest() {
        user = new User(
                0,
                properties.getProperty("username"),
                properties.getProperty("realname"),
                properties.getProperty("email")
        );

        UserResponse response =
                given().
                        baseUri(baseUri).
                        header(header).
                        contentType(ContentType.JSON).
                        body(user)
                .when().
                        post("/users/")
                .then().
                        contentType(ContentType.JSON).
                        statusCode(201)
                .extract().
                        response().
                        as(UserResponse.class);

        user = response.getUser();

        given()
                .baseUri(baseUri)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + user.getId())
                .then()
                .statusCode(204);
    }

    @Test
    public void deleteUserErrorTest() {
        given()
                .baseUri(baseUri)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/12345")
                .then()
                .statusCode(404);
    }
}
