package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.constants.APIEndpoints;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
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
        UsernamePasswordAuthenticationToken authReq
            = new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
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
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                response.put("authenticated", false);
            } else {
                response.put("authenticated", true);
            }
        } else {
            response.put("authenticated", false);
        }

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
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        response.put("username", authentication.getName());
        response.put("role", authentication.getAuthorities().toString());

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
