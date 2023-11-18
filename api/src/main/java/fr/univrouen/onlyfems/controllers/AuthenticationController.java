package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.constants.APIEndpoints;
import fr.univrouen.onlyfems.entities.User;
import fr.univrouen.onlyfems.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Login route.
     *
     * Need a request body, example below :
     * {
     *     "username":"user",
     *     "password":"password"
     * }
     */
    @RequestMapping(
            value = APIEndpoints.LOGIN_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest req) {
        authenticationService.login(loginRequest.username, loginRequest.password, req);
    }

    /**
     * Register route.
     *
     * Need a request body, example below :
     * {
     *     "username": "username",
     *     "password": "password",
     *     "roles": ["ROLE_USER"]
     * }
     *
     * @return The user created.
     * Response example :
     * {
     *     "id": 1,
     *     "username": "username",
     *     "password": "$2a$10$m7pwqKM45wJVnLt9p1lv0uUPyT4EMH4N7k/YZ0DOWYqIsxMSyE9fe",
     *     "roles": [
     *         "ROLE_USER"
     *     ]
     * }
     */
    @RequestMapping(
            value = APIEndpoints.REGISTER_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest req) {
        try {
            return ResponseEntity.ok(authenticationService.register(registerRequest.username, registerRequest.password, registerRequest.roles, req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Route that returns true if a user is authenticated.
     *
     * Response example :
     * {
     *     "authenticated": true
     * }
     */
    @RequestMapping(
            value = APIEndpoints.IS_AUTHENTICATED_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> authenticated() {
        Map<String, Object> response = authenticationService.isAuthenticated();
        return ResponseEntity.ok(response);
    }

    /**
     * Route that returns the current user authenticated.
     *
     * Response example :
     * {
     *     "roles": [ "[ROLE_USER]" ],
     *     "username": "user"
     * }
     */
    @RequestMapping(
            value = APIEndpoints.GET_AUTHENTICATED_USER,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getUserAuthenticated() {
        Map<String, Object> response = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(response);
    }

    /**
     * Contains the username and the password for a login request.
     *
     * @param username Username of a user.
     * @param password Password of a user.
     */
    public record LoginRequest(String username, String password) {
    }

    /**
     * Contains the username, password and role for a register request.
     *
     * @param username Username of a user.
     * @param password Password of a user.
     * @param roles Roles of a user.
     */
    public record RegisterRequest(String username, String password, List<String> roles) {

    }
}
