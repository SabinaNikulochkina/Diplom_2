package LoginTest;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginInvalidTest {
    private final String name;
    private final String email;
    private final String password;
    private UserData userData;
    private User user;
    private User userIncorrect;
    private String token;

    public LoginInvalidTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] Data() {
        return new Object[][]{
                {"nik45", "12345678", "nik324@gmail.com" },
                {"nik4", "12345678", "nik32445@gmail.com" },
                {"nik423", "12345678", "nik32423@gmail.com" },
        };
    }
    @Before
    public void setUp() {
        userData = new UserData();
        user = new User("nik4", "12345678", "nik324@gmail.com");
        userIncorrect = new User(name, password, email);
    }
    @Test
    public void LoginInvalidData() {
        token = userData.createUser(user)
                .extract().body().path("accessToken");
        userData.authorizationUser(userIncorrect)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("email or password are incorrect"))
                .and().statusCode(401);
    }
    @After
    public void cleanUp() {
        String correctAccessToken = token.replace("Bearer ", "");
        userData.deleteUser(correctAccessToken);
    }
}
