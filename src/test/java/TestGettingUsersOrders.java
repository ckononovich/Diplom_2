import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class TestGettingUsersOrders extends BaseTest {
    private final ResponseCode responseCode = new ResponseCode();
    private final ResponseMessage responseMessage = new ResponseMessage();
    private final Steps steps = new Steps();

    @Test
    @DisplayName("Get orders by authorised user")
    @Description("Getting users orders - positive test")
    public void testGettingUsersOrders() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response createOrder = steps.createOrderWithAuthorisation(token);
        Response response = steps.getOrdersWithAuthorisation(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Get orders without authorisation")
    @Description("Getting users orders without authorisation - negative test")
    public void testGettingUsersOrdersNoAuthorisation() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response createOrder = steps.createOrderWithAuthorisation(token);
        Response response = steps.getOrdersWithoutAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getUnauthorizedCode(), responseMessage.getNoAuthorisation());
    }
}