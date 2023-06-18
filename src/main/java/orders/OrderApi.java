package orders;

import client.BurgerSpecification;
import client.ApiConstants;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class OrderApi extends ApiConstants {
    @Step("Создание заказа с авторизацией")
    public static ValidatableResponse createOrder(Order order, String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .body(order)
                .post(ApiConstants.CREATE_ORDER_API)
                .then();
    }
    @Step("Создание заказа без авторизации")
    public static ValidatableResponse createOrderWithoutAuth(Order order) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .body(order)
                .post(ApiConstants.CREATE_ORDER_API)
                .then();
    }
    @Step("Выбрать все ингредиенты")
    public static ValidatableResponse getAllIngredients() {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .get(ApiConstants.INGREDIENT_API)
                .then();
    }
    @Step("Запросить заказы пользователя с авторизацией")
    public ValidatableResponse getUserOrdersWithAuth(String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .get(ApiConstants.USER_ORDERS_API)
                .then();
    }
    @Step("Запросить заказы пользователя без авторизации")
    public ValidatableResponse getUserOrdersWithoutAuth() {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .get(ApiConstants.USER_ORDERS_API)
                .then();
    }
}