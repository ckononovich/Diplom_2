import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class TestLoginUser {
    Steps steps = new Steps();
    ResponseCode responseCode = new ResponseCode();
    ResponseMessage responseMessage = new ResponseMessage();

    @Test
    @DisplayName("Successful login")
    @Description("Login with correct credentials")
    public void testSuccessfulLogin(){
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystem();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponse(loginResponse,responseCode.getSuccessfulCode(), responseMessage.getTrueUserRegistration());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong email")
    public void testLoginWithWrongEmail(){
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongEmail();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseWrongRegistration(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong password")
    public void testLoginWithWrongPassword(){
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongPassword();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseWrongRegistration(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }

    @Test
    @DisplayName("Unsuccessful login")
    @Description("Login with wrong credentials")
    public void testLoginWithWrongCredentials(){
        Response response = steps.registerNewUser();
        Response loginResponse = steps.loginToTheSystemWithWrongData();
        steps.printResponseBodyToConsole(loginResponse);
        steps.checkResponseWrongRegistration(loginResponse, responseCode.getUnauthorizedCode(), responseMessage.getEmailPasswordIncorrect());
    }

    @After
    public void deleteCreatedUser() {
        try {
            Response response = steps.loginToTheSystem();
            System.out.println(response);
            String token = response.getBody().path("accessToken").toString();
            System.out.println(token);
            Response deleteUser = steps.deleteCreatedUser(token);
        } catch (Exception exception) {
            System.out.println("Nothing to delete");
        }
    }
}
