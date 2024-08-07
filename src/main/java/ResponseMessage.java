public class ResponseMessage {
    private static final Boolean TRUECREATIONOFTHEUSER = true;
    private static final String USERALREDYEXIST = "User already exists";
    private static final String NOTENOUGHDATA = "Email, password and name are required fields";

    public Boolean getTrueUserRegistration(){
        return TRUECREATIONOFTHEUSER;
    }
    public String getUserAlreadyExistMessage(){
        return USERALREDYEXIST;
    }
    public String getNotEnoughData(){
        return NOTENOUGHDATA;
    }
}