package org.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    // Создание пользователя POST
    public static final String USER_CREATION = "/api/auth/register";

    // Восстановление и сброс пароля POST
    public static final String RESET_PASSWORD = "/api/password-reset";

    // Авторизация пользователя POST
    public static final String AUTHORIZATION = "/api/auth/login";

    // Выход из системы POST
    public static final String LOGOUT = "/api/auth/logout";

    // Обновление токена POST
    public static final String UPDATE_TOKEN = "/api/auth/token";

    // Получение данных о пользователе GET
    public static final String GET_DATA_USER = "/api/auth/user";

    // Обновление данных о пользователе PATCH
    public static final String UPDATE_DATA_USER = "/api/auth/user";

    // Удаление пользователя DELETE
    public static final String DELETE_USER = "/api/auth/user";

    // Получение данных об ингредиентах GET
    public static final String GET_DATA_INGREDIENTS = "/api/ingredients";

    // Создание заказа POST
    public static final String ORDER_CREATION = "/api/orders";

    // Получить все заказы GET
    public static final String GET_ALL_ORDERS = "/api/orders/all";

    // Получить заказы конкретного пользователя GET
    public static final String GET_USER_ORDERS = "/api/orders";

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .build();
    }



}
