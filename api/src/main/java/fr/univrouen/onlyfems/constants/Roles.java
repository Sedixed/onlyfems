package fr.univrouen.onlyfems.constants;

/**
 * Enum that stock the list of roles.
 */
public enum Roles {

    /**
     * role of a user that is not authenticated.
     */
    ANONYMOUS,

    /**
     * Role of a lambda user connected.
     */
    USER,

    /**
     * Role of a user connected with administrator permissions.
     */
    ADMIN,

    /**
     * All type of users.
     */
    ALL
}
