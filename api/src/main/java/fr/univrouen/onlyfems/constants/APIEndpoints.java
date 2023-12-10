package fr.univrouen.onlyfems.constants;

/**
 * Enum that contains API endpoints constants and associated permissions.
 */
public class APIEndpoints {

    /**
     * Login URL
     */
    public final static String LOGIN_URL = "/authentication/login";

    /**
     * Logout URL
     */
    public final static String LOGOUT_URL = "/authentication/logout";

    /**
     * Register URL
     */
    public final static String REGISTER_URL = "authentication/register";

    /**
     * URL to get the info on the current user logged or not.
     */
    public final static String IS_AUTHENTICATED_URL = "/authentication/authenticated";

    /**
     * URL to get the current user logged.
     */
    public final static String GET_AUTHENTICATED_USER = "/authentication/user";

    /**
     * Front page of the portfolio URL
     */
    public final static String PORTFOLIO_URL = "/portfolio";

    /**
     * URL to get all the images
     */
    public final static String IMAGES_URL = "/images";

    /**
     * URL to get/modify/add/delete an image by its id
     */
    public final static String IMAGE_URL = "/image";
}
