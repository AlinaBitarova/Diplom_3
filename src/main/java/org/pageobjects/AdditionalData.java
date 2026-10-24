package org.pageobjects;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class AdditionalData {

    public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru"; // вместо официального стенда https://stellarburgers.education-services.ru"

    public static final String CREATE_USER_PATH = "/api/auth/register";
    public static final String USER_LOGIN_PATH = "/api/auth/login";
    public static final String USER_PATH = "/api/auth/user";

    private static final Faker faker = new Faker();

    @Step("Requesting Base Url")
    public RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Sending POST request to create a user")
    public Response createUser(User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .post(CREATE_USER_PATH);
    }

    @Step("Sending POST request to login")
    public Response loginUser(User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .post(USER_LOGIN_PATH);
    }

    @Step("Creating a valid user via Faker")
    public User getRandomUser() {
        return new User(
                faker.internet().emailAddress(),
                faker.internet().password(6,10),
                faker.name().name()
        );
    }

    @Step("Deleting a user (with logging in)")
    public void deleteUser(User user) {
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return;
        }

        Response loginResponse = loginUser(user);

        if (loginResponse.getStatusCode() == SC_ACCEPTED || loginResponse.getStatusCode() == SC_OK) {
            String accessToken = loginResponse.path("accessToken");

            if (accessToken != null) {
                given()
                        .spec(getRequestSpec())
                        .header("Authorization", accessToken)
                        .when()
                        .delete(USER_PATH)
                        .then()
                        .statusCode(anyOf(equalTo(SC_OK), equalTo(SC_ACCEPTED)));
            }
        }
    }
}
