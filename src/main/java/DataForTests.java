public class DataForTests {
    private final String email= "test136@email.com";
    private final String newEmail= "testQA90@email.com";
    private final String wrongEmail= "test11@email.com";
    private final String password = "5555888888";
    private final String newPassword = "998877777";
    private final String wrongPassword = "555588888";
    private final String userName = "5555Test";
    private final String newUserName = "Test2233";
    UserData createUserWithCorrectData = new UserData(email, password,userName);
    UserData createUserWithWrongData = new UserData(email, password,"");
    UserData changeUserDataEmail = new UserData (newEmail, "","");
    UserData changeUserDataName = new UserData(newUserName);
    UserData changeAllFields = new UserData(newEmail,newPassword, newUserName);
    LoginUser loginWithCorrectData = new LoginUser(email, password);
    LoginUser loginWithWrongEmail = new LoginUser(wrongEmail, password);
    LoginUser loginWithWrongPassword = new LoginUser(email,wrongPassword);
    LoginUser loginWithWrongCredentials = new LoginUser(wrongEmail,wrongPassword);
}
