import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Steps {
    DataForTests dataForTests = new DataForTests();
    Path path = new Path();

    @Step("Successful registration")
    public Response registerNewUser(){
        Response response =given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.createUserWithCorrectData).when().post(path.getBasePathCreateUser());
        return response;
    }

    @Step("Unsuccessful registration due to missing data")
    public Response wrongRegistration(){
        Response response =given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.createUserWithWrongData).when().post(path.getBasePathCreateUser());
        return response;
    }

    @Step("Print response body to console")
    public void printResponseBodyToConsole(Response response) {
        System.out.println(response.body().asString());
    }

    @Step("Check the response from the server")
    public void checkResponse(Response response, int code, Boolean message) {
        response.then().statusCode(code).and().assertThat().body("success", equalTo(message));
    }

    @Step("Check response from the servet for already exist user")
    public void checkResponseWrongRegistration(Response response, int code, String message){
        response.then().statusCode(code).and().assertThat().body("message", equalTo(message));
    }

    @Step("Delete the created user")
    public Response deleteCreatedUser(String token){
        Response deleteCourier = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).when().delete(path.getBasePathUser());
        return deleteCourier;
    }
    @Step("Login to the system by registered user")
    public Response loginToTheSystem(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithCorrectData).when().post(path.getBasePathLoginUser());
        return response;
    }
}
