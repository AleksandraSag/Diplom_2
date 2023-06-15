package orders;

import client.BurgerSpecification;
import client.ApiConstants;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class OrderApi extends ApiConstants {
    public static ValidatableResponse createOrder(Order order, String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .body(order)
                .post(ApiConstants.CREATE_ORDER_API)
                .then();
    }

    public static ValidatableResponse createOrderWithoutAuth(Order order) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .body(order)
                .post(ApiConstants.CREATE_ORDER_API)
                .then();
    }

    public static ValidatableResponse getAllIngredients() {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .get(ApiConstants.INGREDIENT_API)
                .then();
    }

    public ValidatableResponse getUserOrdersWithAuth(String bearerToken) {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .headers("Authorization", bearerToken)
                .get(ApiConstants.USER_ORDERS_API)
                .then();
    }

    public ValidatableResponse getUserOrdersWithoutAuth() {
        return given()
                .spec(BurgerSpecification.requestSpecification())
                .get(ApiConstants.USER_ORDERS_API)
                .then();
    }
}