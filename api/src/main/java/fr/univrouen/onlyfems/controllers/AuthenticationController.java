package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.constants.APIEndpoints;
import fr.univrouen.onlyfems.dto.authentication.LoginDTO;
import fr.univrouen.onlyfems.dto.user.SaveUserDTO;
import fr.univrouen.onlyfems.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Login route.
     * <p>
     * Need a request body, example below :
     * {
     * "username":"user",
     * "password":"password"
     * }
     */
    @RequestMapping(
            value = APIEndpoints.LOGIN_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(allowCredentials = "true", exposedHeaders = {"Set-Cookie"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void login(@RequestBody LoginDTO loginRequest, HttpServletRequest req) {
        authenticationService.login(loginRequest, req);
    }

    /**
     * Logout route.
     */
    @RequestMapping(
            value = APIEndpoints.LOGOUT_URL,
            method = RequestMethod.POST
    )
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Register route.
     * <p>
     * Need a request body, example below :
     * {
     * "username": "username",
     * "password": "password",
     * "roles": ["ROLE_USER"]
     * }
     *
     * @return The user created.
     * Response example :
     * {
     * "id": 1,
     * "username": "username",
     * "password": "$2a$10$m7pwqKM45wJVnLt9p1lv0uUPyT4EMH4N7k/YZ0DOWYqIsxMSyE9fe",
     * "roles": [
     * "ROLE_USER"
     * ]
     * }
     */
    @RequestMapping(
            value = APIEndpoints.REGISTER_URL,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> register(@RequestBody SaveUserDTO registerRequest, HttpServletRequest req) {
        try {
            return ResponseEntity.ok(authenticationService.register(registerRequest, req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Route that returns the current user authenticated.
     */
    @RequestMapping(
            value = APIEndpoints.GET_AUTHENTICATED_USER,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(allowCredentials = "true", exposedHeaders = {"Access-Control-Allow-Credentials"})
    public ResponseEntity<Object> getUserAuthenticated() {
        Object response = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(response);
    }
}
