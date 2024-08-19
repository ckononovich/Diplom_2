import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class TestRegistrationUser extends BaseTest {
    private final ResponseMessage responseMessage = new ResponseMessage();
    private final ResponseCode responseCode = new ResponseCode();
    private final Steps steps = new Steps();

    @Test
    @DisplayName("Registration of the new user")
    @Description("Testing the registration of the new user - positive test")
    public void testUserRegistration() {
        Response response = steps.registerNewUser();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Registration of already exist user")
    @Description("Testing registration of the user which is already exist")
    public void testExistUserRegistration() {
        Response response = steps.registerNewUser();
        Response registrationWithTheSameData = steps.registerNewUser();
        steps.printResponseBodyToConsole(registrationWithTheSameData);
        steps.checkResponseOtherData(registrationWithTheSameData, responseCode.getForbiddenCode(), responseMessage.getUserAlreadyExistMessage());
    }

    @Test
    @DisplayName("Registration of the user with missing data")
    @Description("Some fields are empty during registration")
    public void testRegistrationWithMissingData() {
        Response response = steps.wrongRegistration();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getForbiddenCode(), responseMessage.getNotEnoughData());
    }
}