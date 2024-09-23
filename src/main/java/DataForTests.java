public class DataForTests {
    private final String email = "test136@email.com";
    private final String newEmail = "testQA90@email.com";
    private final String wrongEmail = "test11@email.com";
    private final String password = "5555888888";
    private final String newPassword = "998877777";
    private final String wrongPassword = "555588888";
    private final String userName = "5555Test";
    private final String newUserName = "Test2233";
    private final String ingredients = "61c0c5a71d1f82001bdaaa6d";
    private final String wrongHashIngredients = "61c0c5a71d1f82001bdaaa6";
    protected UserData createUser = new UserData(email, password, userName);
    protected UserData createUserWithWrongData = new UserData(email, password, "");
    protected UserData changeUserDataEmail = new UserData(newEmail, password, userName);
    protected UserData changeUserDataPassword = new UserData(email, newPassword, userName);
    protected UserData changeUserDataName = new UserData(email, password, newUserName);
    protected UserData changeAllFields = new UserData(newEmail, newPassword, newUserName);
    protected LoginUser loginWithCorrectData = new LoginUser(email, password);
    protected LoginUser loginWithWrongEmail = new LoginUser(wrongEmail, password);
    protected LoginUser loginWithWrongPassword = new LoginUser(email, wrongPassword);
    protected LoginUser loginWithWrongCredentials = new LoginUser(wrongEmail, wrongPassword);
    protected Order addingIngredients = new Order(ingredients);
    protected Order withoutIngredients = new Order();
    protected Order withWrongHashIngredients = new Order(wrongHashIngredients);
}