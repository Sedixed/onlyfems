package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.constants.APIEndpoints;
import fr.univrouen.onlyfems.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

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
     *     "role": "[ROLE_USER]",
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
}
