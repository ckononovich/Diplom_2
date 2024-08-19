import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class TestCreationOrder extends BaseTest {
    private final ResponseCode responseCode = new ResponseCode();
    private final ResponseMessage responseMessage = new ResponseMessage();
    private final Steps steps = new Steps();

    @Test
    @DisplayName("Create an order with authorisation")
    @Description("Testing creation of the order - positive test")
    public void testCreateOrderWithAuthorisation() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.createOrderWithAuthorisation(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Create an order without authorisation")
    @Description("Testing creation of the order without authorisation")
    public void testCreateOrderWithoutAuthorisation() {
        Response response = steps.createOrderWithoutAuthorisation();
        steps.printResponseBodyToConsole(response);
        steps.checkResponse(response, responseCode.getSuccessfulCode(), responseMessage.getTrueResponse());
    }

    @Test
    @DisplayName("Create an order without ingredients")
    @Description("Testing creation of the order without ingredients - negative test")
    public void testCreateOrderWithoutIngredients() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.createOrderWithoutIngredients(token);
        steps.printResponseBodyToConsole(response);
        steps.checkResponseOtherData(response, responseCode.getBadRequestCode(), responseMessage.getNoIngredients());
    }

    @Test
    @DisplayName("Create an order with wrong hash of ingredients")
    @Description("Testing creation of the order with wrong hash - negative test")
    public void testCreateOrderWrongHashIngredients() {
        Response registerUser = steps.registerNewUser();
        String token = registerUser.getBody().path("accessToken").toString();
        Response response = steps.createOrderWrongHashIngredients(token);
        steps.printResponseBodyToConsole(response);
        steps.checkStatusCode(response, responseCode.getInternalServerError());
    }
}