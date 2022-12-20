package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderData extends Endpoints {

    @Step("Получение списка ингридиентов")
    public ValidatableResponse getListOfIngredients(){
        return given()
                .spec(getSpec())
                .when()
                .get(GET_DATA_INGREDIENTS)
                .then().log().all();
    }

    @Step("Создание заказа с авторизацией")
    public ValidatableResponse createOrder(DataOfIngredients ingredientsMassiv, String token){
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .body(ingredientsMassiv)
                .when()
                .post(ORDER_CREATION)
                .then().log().all();

    }
    @Step("Создание заказа без авторизации")
    public ValidatableResponse createOrderWithoutAuth(DataOfIngredients ingredientsMassiv){
        return given()
                .spec(getSpec())
                .body(ingredientsMassiv)
                .when()
                .post(ORDER_CREATION)
                .then().log().all();

    }

    @Step("Получение списка всех заказов")
    public ValidatableResponse getListOfAllOrders(){
        return given()
                .spec(getSpec())
                .when()
                .get(GET_ALL_ORDERS)
                .then().log().all();
    }

    @Step("Получение списка заказов конкретного пользователя")
    public ValidatableResponse getListOfUsersOrders(String token){
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .when()
                .get(GET_USER_ORDERS)
                .then().log().all();
    }

    @Step("Получение списка заказов конкретного пользователя")
    public ValidatableResponse getListOfUsersWithoutOrders(){
        return given()
                .spec(getSpec())
                .when()
                .get(GET_USER_ORDERS)
                .then().log().all();
    }


}
