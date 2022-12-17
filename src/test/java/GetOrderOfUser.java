import org.example.DataOfIngredients;
import org.example.OrderData;
import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderOfUser {
    private OrderData orderData;
    private User user;
    private DataOfIngredients orderWithIngredient;
    private UserData userData;
    private String token;
    private String correctAccessToken;

    @Before
    public void setUp() {
        user = new User("Sabina1", "123456", "123test@mail.ru");
        orderData = new OrderData();
        userData = new UserData();
        orderWithIngredient = DataOfIngredients.getOrder();
    }

    @Test
    public void GetOrderAuthUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.authorizationUser(user);

        orderData.getListOfUsersOrders(correctAccessToken)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("orders", notNullValue())
                .and().statusCode(200);
    }

    @Test
    public void GetOrderUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");

        orderData.getListOfUsersWithoutOrders()
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"))
                .and().statusCode(401);
    }


    @After
    public void cleanUp(){
        userData.deleteUser(correctAccessToken);
    }
}
