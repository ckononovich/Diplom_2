public class Path {
    private static final String BASEPATHCREATEUSER = "/api/auth/register";
    private static final String BASEPATHLOGINUSER = "/api/auth/login";
    private static final String BASEPATHCREATEORDER = "/api/orders";
    private static final String BASEPATHGETINGREDIENTS = "/api/ingredients";
    private static final String BASEPATHUSER = "/api/auth/user";

    public String getBasePathCreateUser() {
        return BASEPATHCREATEUSER;
    }

    public String getBasePathUser() {
        return BASEPATHUSER;
    }

    public String getBasePathLoginUser() {
        return BASEPATHLOGINUSER;
    }

    public String getBasePathCreateOrder() {
        return BASEPATHCREATEORDER;
    }

    public String getBasePathGetIngredients() {
        return BASEPATHGETINGREDIENTS;
    }
}