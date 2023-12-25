package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.dto.authentication.LoginDTO;
import fr.univrouen.onlyfems.dto.user.SaveUserDTO;
import fr.univrouen.onlyfems.dto.user.UserDTO;
import fr.univrouen.onlyfems.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
     */
    public void login(LoginDTO loginDTO, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    /**
     * Register a new User and log it.
     *
     * @param userDTO Email of the user.
     * @return The user created.
     */
    public User register(SaveUserDTO userDTO, HttpServletRequest req) {
        if (!verifyEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email is not well formed.");
        }

        if (!verifyPassword(userDTO.getPassword())) {
            throw new IllegalArgumentException("Password is not well formed.");
        }

        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email " + userDTO.getEmail() + " already exists");
        }

        User user = new User(
                userDTO.getEmail(),
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getRoles()
        );
        User userCreated = userService.createOrUpdateUser(user);
        login(new LoginDTO(userCreated.getEmail(), userDTO.getPassword()), req);
        return user;
    }

    /**
     * Return the user currently authenticated.
     * Can return an anonymous user if there is no user authenticated.
     *
     * @return The user.
     */
    public UserDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDTO;
        if (isAuthenticated()) {
            userDTO = new UserDTO(
                    authentication.getName(),
                    userService.getUserByEmail(authentication.getName()).getUsername(),
                    authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
            );
        } else {
            userDTO = new UserDTO(
                    null,
                    null,
                    authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
            );
        }

        return userDTO;
    }


    // Private methods

    /**
     * Return true if a user is authenticated, false otherwise.
     *
     * @return A boolean.
     */
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return !(authentication instanceof AnonymousAuthenticationToken);
        } else {
            return false;
        }
    }

    /**
     * Private method to check if a string in parameter is an email.
     *
     * @param email Email to check.
     * @return true if parameter is an email, false otherwise.
     */
    private boolean verifyEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return email.matches(regex);
    }

    /**
     * Private method to check if a password contains at least one letter, one number,
     * and has at least 8 characters.
     *
     * @param password The password to check.
     * @return true if password is correct, false otherwise.
     */
    private boolean verifyPassword(String password) {
        return password.matches(".*[A-Za-z].*")
                && password.matches(".*[0-9].*")
                && password.length() >= 8;
    }
}
