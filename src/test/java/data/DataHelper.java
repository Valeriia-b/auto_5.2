package data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataHelper {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void Registration (RegistrationDto registrationDto) {
        given()
                .spec(requestSpec)
                .body(registrationDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);}

    public static RegistrationDto generateValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto validUser = new RegistrationDto(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "active");
        Registration(validUser);
        return validUser;
    }

    public static RegistrationDto generateValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto invalidUser = new RegistrationDto(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "blocked");
        Registration(invalidUser);
        return invalidUser;
        }

    public static RegistrationDto generateInvalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto invalidLogin = new RegistrationDto(
                "user",
                faker.internet().password(),
                "active");
        Registration(invalidLogin);
        return new RegistrationDto("user2", faker.internet().password(), "active");
    }

    public static RegistrationDto generateInvalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto invalidLogin = new RegistrationDto(
                faker.name().username().toLowerCase(),
                "password",
                "active");
        Registration(invalidLogin);
        return new RegistrationDto(faker.name().username().toLowerCase(), "password2", "active");
    }

}