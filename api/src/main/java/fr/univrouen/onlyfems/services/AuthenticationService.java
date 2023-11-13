package fr.univrouen.onlyfems.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    private AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
        response.put("role", authentication.getAuthorities().toString());

        return response;
    }
}
