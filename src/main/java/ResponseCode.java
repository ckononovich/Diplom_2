public class ResponseCode {
    private static final int SUCCESSFULCODE = 200;
    private static final int FORBIDDENCODE = 403;
    private static final int UNAUTHORIZEDCODE = 401;
    private static final int BADREQUEST = 400;
    private static final int INTERNALSERVERERROR = 500;

    public int getSuccessfulCode() {
        return SUCCESSFULCODE;
    }

    public int getForbiddenCode() {
        return FORBIDDENCODE;
    }

    public int getUnauthorizedCode() {
        return UNAUTHORIZEDCODE;
    }

    public int getBadRequestCode() {
        return BADREQUEST;
    }

    public int getInternalServerError() {
        return INTERNALSERVERERROR;
    }
}