package changeDataOfUser;

import org.example.User;
import org.example.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class ChangeDataOfUser {
    private final String name;
    private final String email;
    private final String password;
    private UserData userData;
    private User user;
    private User userChangeData;
    private String token;
    private String correctAccessToken;

    public ChangeDataOfUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] Data() {
        return new Object[][]{
                {"nik45", "12345678", "nik324@gmail.com" }, //меняем только имя
                {"nik4", "12345678", "nik32445@gmail.com" }, //меняем только почту
                {"nik423", "1234567890", "nik32423@gmail.com" }, //меняем все поля
                {"nik423", "1234567890gfgfg", "nik32423@gmail.com" }, //меняем только пароль
        };
    }

    @Before
    public void setUp() {
        userData = new UserData();
        user = new User("nik4", "12345678", "nik324@gmail.com");
        userChangeData = new User(name, password, email);
    }

    @Test
    public void ChangeDataOfUser() {

        userData.createUser(user);
        token = userData.authorizationUser(user)
                .extract().body().path("accessToken");
        correctAccessToken = token.replace("Bearer ", "");
        userData.changeDataOfUser(userChangeData, correctAccessToken)
                .assertThat().body("success", equalTo(true))
                .and().statusCode(200);
    }

    @After
    public void cleanUp() {
        userData.deleteUser(correctAccessToken);
    }
}
