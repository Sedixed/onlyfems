package fr.univrouen.onlyfems.constants;

import java.util.Set;

/**
 * Enum that contains API endpoints constants and associated permissions.
 */
public class APIEndpoints {

    /**
     * Login URL
     */
    public final static String LOGIN_URL = "/login";

    /**
     * Logout URL
     */
    public final static String LOGOUT_URL = "/logout";

    /**
     * URL to get the current user logged if there is one.
     */
    public final static String IS_AUTHENTICATED_URL = "/isauth";

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
