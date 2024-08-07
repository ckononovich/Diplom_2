public class ResponseCode {
    private static final int SUCCESSFULCODE = 200;
    private static final int FORBIDDENCODE = 403;
    private static final int UNAUTHORIZEDCODE = 401;

    public int getSuccessfulCode (){
        return SUCCESSFULCODE;
    }
    public int getForbiddenCode (){
        return FORBIDDENCODE;
    }
    public int getUnauthorizedCode(){
        return UNAUTHORIZEDCODE;
    }
}
