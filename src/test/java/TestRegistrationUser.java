import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class TestRegistrationUser {
    ResponseMessage responseMessage = new ResponseMessage();
    ResponseCode responseCode = new ResponseCode();
    Steps steps = new Steps();

    @Test
    @DisplayName("Registration of the new user")
    @Description("Testing the registration of the new user - smoke test")
    public void testUserRegistration() {
        Response response = steps.registerNewUser();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueUserRegistration());
    }

    @Test
    @DisplayName("Registration of already exist user")
    @Description("Testing registration of the user which is already exist")
    public void testExistUserRegistration(){
        Response response = steps.registerNewUser();
        Response registrationWithTheSameData = steps.registerNewUser();
        steps.printResponseBodyToConsole(registrationWithTheSameData);
        steps.checkResponseWrongRegistration(registrationWithTheSameData, responseCode.getForbiddenCode(), responseMessage.getUserAlreadyExistMessage());
    }

    @Test
    @DisplayName("Registration of the user with missing data")
    @Description("Some fields are empty during registration")
    public void testRegistrationWithMissingData(){
        Response response = steps.wrongRegistration();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseWrongRegistration(response, responseCode.getForbiddenCode(), responseMessage.getNotEnoughData());
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