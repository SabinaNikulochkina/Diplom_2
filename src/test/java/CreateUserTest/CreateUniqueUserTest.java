package CreateUserTest;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUniqueUserTest {

    private UserData userData;
    private User user;
    private String token;

    @Before
    public void setUp() {
        userData = new UserData();
        user = new User("nik3", "12345678", "nik325@gmail.com");
    }
    @Test
    public void UserCanBeCreatedValidData() {
        token = userData.createUser(user)
                .assertThat().body("success", equalTo(true))
                .assertThat().body("accessToken", notNullValue())
                .and().statusCode(200)
                .extract().body().path("accessToken");
    }
    @After
    public void cleanUp() {
        String correctAccessToken = token.replace("Bearer ", "");
        userData.deleteUser(correctAccessToken);
    }
}
