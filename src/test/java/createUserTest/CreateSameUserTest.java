package createUserTest;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateSameUserTest {
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
                .extract().body().path("accessToken");
        userData.createUser(user)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("User already exists"))
                .and().statusCode(403);
    }

    @After
    public void cleanUp() {
        System.out.println(token);
        String correctAccessToken = token.replace("Bearer ", "");
        userData.deleteUser(correctAccessToken);
    }
}
