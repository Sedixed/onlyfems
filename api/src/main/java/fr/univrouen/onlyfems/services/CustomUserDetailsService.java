package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.entities.User;
import fr.univrouen.onlyfems.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom UserDetailsService used in SecurityConfig to load users.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Method to load a user in database using the username.
     *
     * @param username Username of the user.
     * @return UserDetails object.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userRepo = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User userResponse = new org.springframework.security.core.userdetails.User(
                userRepo.getUsername(),
                userRepo.getPassword(),
                getAuthorities(userRepo.getRoles())
        );

        return userResponse;
    }

    /**
     * Private function to get a list of GrantedAuthority object with a list of String object.
     *
     * @param roles The list of roles to convert.
     * @return The roles converted in GrantedAuthority.
     */
    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
