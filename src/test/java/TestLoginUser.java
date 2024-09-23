import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class TestLoginUser extends BaseTest {
    private final Steps steps = new Steps();
    private final ResponseCode responseCode = new ResponseCode();
    private final ResponseMessage responseMessage = new ResponseMessage();

    @Test
    @DisplayName("Successful login")
    @Description("Login with correct credentials")
    public void testSuccessfulLogin() {
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystem();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponse(loginResponse, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong email")
    public void testLoginWithWrongEmail() {
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongEmail();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseOtherData(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong password")
    public void testLoginWithWrongPassword() {
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongPassword();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseOtherData(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong credentials")
    public void testLoginWithWrongCredentials() {
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongData();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseOtherData(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }
}