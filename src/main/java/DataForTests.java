public class DataForTests {
    private final String email= "test119@email.com";
    private final String wrongEmail= "test11@email.com";
    private final String password = "5555888888";
    private final String wrongPassword = "555588888";
    private final String userName = "5555Test";
    UserData createUserWithCorrectData = new UserData(email, password,userName);
    UserData createUserWithWrongData = new UserData(email, password,"");
    LoginUser loginWithCorrectData = new LoginUser(email, password);
    LoginUser loginWithWrongEmail = new LoginUser(wrongEmail, password);
    LoginUser loginWithWrongPassword = new LoginUser(email,wrongPassword);
    LoginUser loginWithWrongCredentials = new LoginUser(wrongEmail,wrongPassword);
}
