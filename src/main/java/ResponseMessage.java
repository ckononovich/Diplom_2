public class ResponseMessage {
    private static final Boolean TRUECREATIONOFTHEUSER = true;
    private static final String USERALREDYEXIST = "User already exists";
    private static final String NOTENOUGHDATA = "Email, password and name are required fields";
    private static final String EMAILPASSWORDINCORRECT = "email or password are incorrect";
    private static final String EDITUSERPROFILENOAUTHORISATION ="You should be authorised";

    public Boolean getTrueUserRegistration(){
        return TRUECREATIONOFTHEUSER;
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
    public String getEditUserProfileNoAuthorisation(){
        return EDITUSERPROFILENOAUTHORISATION;
    }
}