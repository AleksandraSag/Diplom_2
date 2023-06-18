package user;

import client.BurgerSpecification;
import client.ApiConstants;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.is;

public class UserApi extends ApiConstants {
    @Step("Авторизация пользователя")
    public static ValidatableResponse userLogin(User user) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(ApiConstants.LOGIN_API)
                .then();
    }
    @Step("Создание пользователя")
    public ValidatableResponse userReg(User user) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(user)
                .when()
                .post(ApiConstants.CREATE_USER_API)
                .then();
    }
    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .delete(ApiConstants.DELETE_USER_API)
                .then()
                .statusCode(SC_ACCEPTED)
                .and().body("message", is("User successfully removed"));
    }
    @Step("Изменение пользовательских данных с авторизацией")
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
    @Step("Изменение пользовательских данных без авторизации")
    public ValidatableResponse updateDataUserWithoutAuth(UserNewData userNewData) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .and()
                .body(userNewData)
                .patch(ApiConstants.PATCH_USER_API)
                .then();
    }
}