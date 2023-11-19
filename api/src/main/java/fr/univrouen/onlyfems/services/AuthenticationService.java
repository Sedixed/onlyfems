package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * Service containing all methods bound to authentication.
 */
@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Login a user provided a login request.
     *
     */
    public void login(String username, String password, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    /**
     * Register a new User and log it.
     *
     * @param username Username of the user.
     * @param password Password of the user.
     * @param roles Roles of the user.
     * @return The user created.
     */
    public User register(String username, String password, List<String> roles, HttpServletRequest req) {
        User user = userService.createOrUpdateUser(new User(username, passwordEncoder.encode(password), roles));
        login(username, password, req);
        return user;
    }

    /**
     * Return true if a user is authenticated, false otherwise.
     *
     * @return A boolean.
     */
    public Map<String, Object> isAuthenticated() {
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
        return response;
    }

    /**
     * Return the user currently authenticated.
     * Can return an anonymous user if there is no user authenticated.
     *
     * @return The user.
     */
    public Map<String, Object> getAuthenticatedUser() {
        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        response.put("username", authentication.getName());
        response.put("roles", authentication.getAuthorities());

        return response;
    }
}
