package fr.univrouen.onlyfems.constants;

import java.util.Set;

/**
 * Enum that contains API endpoints constants and associated permissions.
 */
public enum APIEndpoints {

    /**
     * Login URL
     * Can only be accessed by not authenticated users.
     */
    LOGIN_URL("/login", Set.of(Roles.ANONYMOUS)),

    /**
     * Logout URL
     * Can be accessed by authenticated users.
     */
    LOGOUT_URL("/logout", Set.of(Roles.USER, Roles.ADMIN)),

    /**
     * URL to get the current user logged if there is one.
     */
    IS_AUTHENTICATED_URL("/isauth", Set.of(Roles.ALL));


    private final String url;
    private final Set<Roles> permissions;

    APIEndpoints(String url, Set<Roles> permissions) {
        this.url = url;
        this.permissions = permissions;
    }

    public String getUrl() {
        return url;
    }

    public Set<Roles> getPermissions() {
        return permissions;
    }
}
