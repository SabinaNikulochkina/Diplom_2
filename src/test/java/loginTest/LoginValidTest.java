package loginTest;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginValidTest {
    private UserData userData;
    private User user;
    private String token;

    @Before
    public void setUp() {
        userData = new UserData();
        user = new User("nik4", "12345678", "nik324@gmail.com");
    }
    @Test
    public void LoginValidData() {
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        userData.authorizationUser(user)
                .assertThat().body("success", equalTo(true))
                .and().statusCode(200);
    }
    @After
    public void cleanUp() {
        String correctAccessToken = token.replace("Bearer ", "");
        userData.deleteUser(correctAccessToken);
    }

}
