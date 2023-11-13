package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.constants.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    //private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(
            value = APIEndpoints.LOGIN_URL,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("ui");
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        // ...

        return ResponseEntity.ok("ui");
    }

    public record LoginRequest(String username, String password) {
    }

    /*public ResponseEntity<Map<String, Object>> login(@RequestParam(defaultValue = "user") String username, @RequestParam(defaultValue = "password") String password) {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication == null);

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated("ui", "ui");
        System.out.println("ui");
        Authentication authenticationResponse = this.authenticationManager.authenticate();

        System.out.println(authenticationResponse.isAuthenticated());

        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("pw", password);
        //response.put("isAuthenticated", authentication.isAuthenticated());
        /*if (authentication.isAuthenticated()) {
            System.out.println("ui");
            System.out.println(authentication.getName());
            System.out.println(authentication.getAuthorities().toString());
            if (authentication instanceof AnonymousAuthenticationToken) {
                System.out.println("anonymmmmmm");
            }
        }*/

        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password))
        );

        Map<String, Object> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("pw", password);
        return ResponseEntity.ok().body(response);
    }*/
}
