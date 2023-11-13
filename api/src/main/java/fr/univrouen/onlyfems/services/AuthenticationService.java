package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.controllers.AuthenticationController;
import fr.univrouen.onlyfems.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;

    /**
     * Login a user provided a login request.
     *
     * @param loginRequest The login request containing the username and the password.
     */
    public void login(AuthenticationController.LoginRequest loginRequest) {

    }

    /**
     * Logout the user logged.
     */
    public void logout() {

    }

    /**
     * Return true if a user is authenticated, false otherwise.
     *
     * @return A boolean.
     */
    public boolean isAuthenticated() {

        return true;
    }

    /**
     * Return the user currently authenticated.
     * Can return an anonymous user if there is no user authenticated.
     *
     * @return The user.
     */
    public User getAuthenticatedUser() {


        return null;
    }
}
