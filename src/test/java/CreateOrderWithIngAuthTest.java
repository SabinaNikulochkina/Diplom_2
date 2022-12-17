import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderWithIngAuthTest {

    private OrderData orderData;
    private User user;
    private DataOfIngredients orderWithIngredient;
    private DataOfIngredients orderWithoutIngredient;
    private DataOfIngredients orderWithInvalidIngredient;
    private UserData userData;
    private String token;
    private String correctAccessToken;


    @Before
    public void setUp() {
        user = new User("Sabina1", "123456", "123test@mail.ru");
        orderData = new OrderData();
        userData = new UserData();
        orderWithIngredient = DataOfIngredients.getOrder();
        orderWithInvalidIngredient = DataOfIngredients.invalidOrder();
        orderWithoutIngredient = DataOfIngredients.emptyOrder();
    }

    @Test
    public void OrderCanBeCreatedWithIngAuthUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.authorizationUser(user);

         orderData.createOrder(orderWithIngredient, correctAccessToken)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("order", notNullValue())
                .and().statusCode(200);
        }

    @Test
    public void OrderCantBeCreatedWithoutIngAuthUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.authorizationUser(user);


        orderData.createOrder(orderWithoutIngredient, correctAccessToken)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("Ingredient ids must be provided"))
                .and().statusCode(400);
    }

    @Test
    public void OrderCantBeCreatedWithInvalidIngAuthUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.authorizationUser(user);


        orderData.createOrder(orderWithInvalidIngredient, correctAccessToken)
                .and().statusCode(500);
    }

    @Test
    public void OrderCantBeCreatedWithoutAuthUser(){
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.authorizationUser(user);


        orderData.createOrderWithoutAuth(orderWithIngredient)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("order", notNullValue())
                .and().statusCode(200);
    }

    @After
    public void cleanUp(){
        userData.deleteUser(correctAccessToken);
    }
}
