import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

public class TestGettingUsersOrders {
    ResponseCode responseCode = new ResponseCode();
    ResponseMessage responseMessage = new ResponseMessage();
    Steps steps = new Steps();

    @Test
    @DisplayName("Get orders by authorised user")
    @Description("Getting users orders - positive test")
    public void testGettingUsersOrders(){
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response createOrder = steps.createOrderWithAuthorisation(token);
        Response response = steps.getOrdersWithAuthorisation(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(),responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Get orders without authorisation")
    @Description("Getting users orders without authorisation - negative test")
    public void testGettingUsersOrdersNoAuthorisation(){
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response createOrder = steps.createOrderWithAuthorisation(token);
        Response response = steps.getOrdersWithoutAuthorisation();
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
