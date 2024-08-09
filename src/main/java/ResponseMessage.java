public class ResponseMessage {
    private static final Boolean TRUERESPOSE = true;
    private static final String USERALREDYEXIST = "User already exists";
    private static final String NOTENOUGHDATA = "Email, password and name are required fields";
    private static final String EMAILPASSWORDINCORRECT = "email or password are incorrect";
    private static final String NOAUTHORISATION ="You should be authorised";
    private static final String NOINGREDIENTS ="Ingredient ids must be provided";

    public Boolean getTrueResponse(){
        return TRUERESPOSE;
    }
    public String getUserAlreadyExistMessage(){
        return USERALREDYEXIST;
    }
    public String getNotEnoughData(){
        return NOTENOUGHDATA;
    }
    public String getEmailPasswordIncorrect(){
        return EMAILPASSWORDINCORRECT;
    }
    public String getNoAuthorisation(){
        return NOAUTHORISATION;
    }
    public String getNoIngredients(){
        return NOINGREDIENTS;
    }
}