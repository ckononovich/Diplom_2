import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class TestChangingUserData {
    Steps steps = new Steps();
    ResponseMessage responseMessage = new ResponseMessage();
    ResponseCode responseCode = new ResponseCode();

    @Test
    @DisplayName("Change email")
    @Description("Changing email-positive test")
    public void testChangingEmail() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.changeUserEmail(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
        Response changeBack = steps.changeDataBack(token);
        steps.printResponseBodyToConsole(changeBack);
    }

    @Test
    @DisplayName("Change password")
    @Description("Changing password-positive test")
    public void testChangingPassword() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.changeUserPassword(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
        Response changeBack = steps.changeDataBack(token);
        steps.printResponseBodyToConsole(changeBack);
    }

    @Test
    @DisplayName("Change user name")
    @Description("Changing user name-positive test")
    public void testChangingName() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.changeUserName(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Change all data")
    @Description("Changing all data-positive test")
    public void testChangingAllData() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.changeUserAllData(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
        Response changeBack = steps.changeDataBack(token);
        steps.printResponseBodyToConsole(changeBack);
    }


    @Test
    @DisplayName("Change email without authorisation")
    @Description("Changing email without authorisation-negative test")
    public void testChangingEmailNoAuthorisation() {
        Response registerUser = steps.registerNewUser();
        Response response = steps.changeUserEmailNoAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getUnauthorizedCode(), responseMessage.getNoAuthorisation());
    }

    @Test
    @DisplayName("Change password without authorisation")
    @Description("Changing password without authorisation-negative test")
    public void testChangingPasswordNoAuthorisation() {
        Response registerUser = steps.registerNewUser();
        Response response = steps.changeUserPasswordNoAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getUnauthorizedCode(), responseMessage.getNoAuthorisation());
    }

    @Test
    @DisplayName("Change name without authorisation")
    @Description("Changing name without authorisation-negative test")
    public void testChangingNameNoAuthorisation() {
        Response registerUser = steps.registerNewUser();
        Response response = steps.changeUserNameNoAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getUnauthorizedCode(), responseMessage.getNoAuthorisation());
    }

    @Test
    @DisplayName("Change all data without authorisation")
    @Description("Changing all data without authorisation-negative test")
    public void testChangingAllDataNoAuthorisation() {
        Response registerUser = steps.registerNewUser();
        Response response = steps.changeAllDataNoAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getUnauthorizedCode(), responseMessage.getNoAuthorisation());
    }

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