import io.restassured.response.Response;
import org.junit.After;

public abstract class BaseTest {
    private final Steps steps = new Steps();

    @After
    public void deleteCreatedUser() {
        try {
            Response response = steps.loginToTheSystem();
            System.out.println(response);
            String token = response.getBody().path("accessToken").toString();
            System.out.println(token);
            Response deleteUser = steps.deleteUser(token);
        } catch (Exception exception) {
            System.out.println("Nothing to delete");
        }
    }
}