import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Steps {
    DataForTests dataForTests = new DataForTests();
    Path path = new Path();

    //General steps
    @Step("Check the response from the server")
    public void checkResponse(Response response, int code, Boolean message) {
        response.then().statusCode(code).and().assertThat().body("success", equalTo(message));
    }

    @Step("Check the response from the server")
    public void checkResponseOtherData(Response response, int code, String message) {
        response.then().statusCode(code).and().assertThat().body("message", equalTo(message));
    }

    @Step("Check the response from the server")
    public void checkStatusCode(Response response, int code) {
        response.then().statusCode(code);
    }

    @Step("Print the response body to console")
    public void printResponseBodyToConsole(Response response) {
        System.out.println(response.body().asString());
    }

    //Registration of the user
    @Step("Successful registration")
    public Response registerNewUser() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.createUser).when().post(path.getBasePathCreateUser());
        return response;
    }

    @Step("Unsuccessful registration due to missing data")
    public Response wrongRegistration() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.createUserWithWrongData).when().post(path.getBasePathCreateUser());
        return response;
    }

    //Delete user
    @Step("Delete the created user")
    public Response deleteUser(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).when().delete(path.getBasePathUser());
        return response;
    }

    //Login user
    @Step("Login to the system by registered user")
    public Response loginToTheSystem() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithCorrectData).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong email")
    public Response loginToTheSystemWithWrongEmail() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongEmail).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong password")
    public Response loginToTheSystemWithWrongPassword() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongPassword).when().post(path.getBasePathLoginUser());
        return response;
    }

    @Step("Login to the system with wrong email and password")
    public Response loginToTheSystemWithWrongData() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.loginWithWrongCredentials).when().post(path.getBasePathLoginUser());
        return response;
    }

    //Change users data
    @Step("Change user email")
    public Response changeUserEmail(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeUserDataEmail).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user email")
    public Response changeUserPassword(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeUserDataPassword).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user email without authorisation")
    public Response changeUserEmailNoAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.changeUserDataEmail).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user password without authorisation")
    public Response changeUserPasswordNoAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.changeUserDataPassword).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change user name without authorisation")
    public Response changeUserNameNoAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.changeUserDataName).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change all data without authorisation")
    public Response changeAllDataNoAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.changeAllFields).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Return data of the user to the old one")
    public Response changeDataBack(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.createUser).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change the user name")
    public Response changeUserName(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeUserDataName).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Change all data for the user")
    public Response changeUserAllData(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.changeAllFields).when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Logout from the system")
    public Response logoutFromTheSystem(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body("token" + "{{" + token + "}}").when().patch(path.getBasePathUser());
        return response;
    }

    @Step("Get ingredients")
    public Response getIngredients() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).get(path.getBasePathGetIngredients());
        return response;
    }

    //Create an order
    @Step("Create an order with authorisation")
    public Response createOrderWithAuthorisation(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.addingIngredients).when().post(path.getBasePathCreateOrder());
        return response;
    }

    @Step("Create an order without authorisation")
    public Response createOrderWithoutAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).and().body(dataForTests.addingIngredients).when().post(path.getBasePathCreateOrder());
        return response;
    }

    @Step("Create an order without ingredients")
    public Response createOrderWithoutIngredients(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.withoutIngredients).when().post(path.getBasePathCreateOrder());
        return response;
    }

    @Step("Create an order with wrong hash of ingredients")
    public Response createOrderWrongHashIngredients(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).and().body(dataForTests.withWrongHashIngredients).when().post(path.getBasePathCreateOrder());
        return response;
    }

    //Get orders data
    @Step("Get orders with authorisation")
    public Response getOrdersWithAuthorisation(String token) {
        Response response = given().spec(BaseHttpClient.baseRequestSpecWithToken(token)).get(path.getBasePathCreateOrder());
        return response;
    }

    @Step("Get orders without authorisation")
    public Response getOrdersWithoutAuthorisation() {
        Response response = given().spec(BaseHttpClient.baseRequestSpec()).get(path.getBasePathCreateOrder());
        return response;
    }
}