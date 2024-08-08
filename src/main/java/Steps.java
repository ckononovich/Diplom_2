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
    public Response deleteUser(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).when().delete(path.getBasePathUser());
        return response;
    }

    @Step("Open user details")
    public Response userDetails(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).get(path.getBasePathUser());
        return response;
    }

    @Step("Login to the system by registered user")
    public Response loginToTheSystem(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithCorrectData).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong email")
    public Response loginToTheSystemWithWrongEmail(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongEmail).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong password")
    public Response loginToTheSystemWithWrongPassword(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongPassword).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong email and password")
    public Response loginToTheSystemWithWrongData(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongCredentials).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Change user email")
    public Response changeUserEmail(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeUserDataEmail).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user data without authorisation")
    public Response changeUserDataNoAuthorisation(){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.changeUserDataEmail).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Return data of the user to the old one")
    public Response changeDataBack(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.createUserWithCorrectData).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user name")
    public Response changeUserName(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeUserDataName).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change all data for user")
    public Response changeUserAllData(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeAllFields).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Logout from the system")
    public Response logoutFromTheSystem(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body("token"+"{{"+token+"}}").when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Get ingredients")
    public Response getIngredients() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).get(path.getBasePathUser());
        return response;
    }

    @Step("Create order with authorisation")
    public Response createOrderWithAuthorisation(String token){
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.loginWithWrongCredentials).when().post(path.getBasePathLoginUser());
        return response;
    }
}