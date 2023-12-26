package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.dto.authentication.LoginDTO;
import fr.univrouen.onlyfems.dto.user.SaveUserDTO;
import fr.univrouen.onlyfems.dto.user.UserDTO;
import fr.univrouen.onlyfems.entities.User;
import fr.univrouen.onlyfems.repositories.UserRepository;
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

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * Service containing all methods bound to authentication.
 */
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
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
     * Return the user currently authenticated.
     * Can return an anonymous user if there is no user authenticated.
     *
     * @return The user.
     */
    public UserDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO userDTO;
        if (isAuthenticated()) {
            User user = userRepository.findByEmail(authentication.getName());
            userDTO = new UserDTO(
                    user.getId(),
                    authentication.getName(),
                    user.getEmail(),
                    authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
            );
        } else {
            userDTO = new UserDTO(
                    null,
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
}
