package createUserTest;

import org.example.User;
import org.example.UserData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CreateUserEmptyDataTest {

    private final String name;
    private final String password;
    private final String email;
    private UserData userData;
    private User user;

    public CreateUserEmptyDataTest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] Data() {
        return new Object[][]{
                {"Sabina123", "", "123mail1234@mail.ru" },
                {"Sabina123", "1234567", "" },
                {"", "1234567", "123mail1234@mail.ru" },
        };
    }
    @Before
    public void setUp() {
        userData = new UserData();
        user = new User(name, password, email);
    }
    @Test
    public void UserCanBeCreatedValidData() {
        userData.createUser(user)
                .assertThat().body("success", equalTo(false))
                .assertThat().body("message", equalTo("Email, password and name are required fields"))
                .and().statusCode(403);
    }
}
