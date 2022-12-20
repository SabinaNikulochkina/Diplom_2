package org.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserData extends Endpoints {
    @Step("Создание нового пользователя")
    public ValidatableResponse createUser(User user){
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(USER_CREATION)
                .then().log().all();

    }

    @Step("Создание пользователя, который уже зарегистрирован")
    public ValidatableResponse createSameUser(User user){
        given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(USER_CREATION)
                .then();
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(USER_CREATION)
                .then().log().all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse authorizationUser (User user){
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(AUTHORIZATION)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser (String token){
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .when()
                .delete(DELETE_USER)
                .then().log().all();
    }

    @Step("Изменение данных пользователя с авторизацией")
    public ValidatableResponse changeDataOfUser (User user, String token){
        return given()
                .spec(getSpec())
                .auth().oauth2(token)
                .body(user)
                .when()
                .patch(UPDATE_DATA_USER)
                .then().log().all();
    }

    @Step("Изменение данных пользователя без авторизации")
    public ValidatableResponse changeDataOfUserWithoutAuth (User user){
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .patch(UPDATE_DATA_USER)
                .then().log().all();
    }


}
