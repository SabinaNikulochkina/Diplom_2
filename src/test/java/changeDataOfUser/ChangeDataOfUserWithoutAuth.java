package changeDataOfUser;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangeDataOfUserWithoutAuth {

    private UserData userData;
    private User user;
    private String token;
    private User userChangeData;

    private String correctAccessToken;

    @Before
    public void setUp() {
        userData = new UserData();
        user = new User("nik4", "12345678", "nik324@gmail.com");
        userChangeData = new User("nik4", "12345678", "nik3245@gmail.com");

    }

    @Test
    public void ChangeDataOfUserWithoutAuth() {

        token = userData.createUser(user)
                .extract().body().path("accessToken");

        userData.changeDataOfUserWithoutAuth(userChangeData)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("You should be authorised"))
                .and().statusCode(401);
    }

    @After
    public void cleanUp() {
        correctAccessToken = token.replace("Bearer ", "");
        userData.deleteUser(correctAccessToken);
    }
}
