package user;

import client.BurgerSpecification;
import client.ApiConstants;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.is;

public class UserApi extends ApiConstants {
    public static ValidatableResponse userLogin(User user) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(ApiConstants.LOGIN_API)
                .then();
    }

    public ValidatableResponse userReg(User user) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(ApiConstants.CREATE_USER_API)
                .then();
    }

    public ValidatableResponse deleteUser(String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .delete(ApiConstants.DELETE_USER_API)
                .then()
                .statusCode(SC_ACCEPTED)
                .and().body("message", is("User successfully removed"));
    }

    public ValidatableResponse updateDataUserWithAuth(UserNewData userNewData, String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .and()
                .body(userNewData)
                .when()
                .patch(ApiConstants.PATCH_USER_API)
                .then();
    }

    public ValidatableResponse updateDataUserWithoutAuth(UserNewData userNewData) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(userNewData)
                .patch(ApiConstants.PATCH_USER_API)
                .then();
    }
}