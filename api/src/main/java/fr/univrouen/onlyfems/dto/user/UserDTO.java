package fr.univrouen.onlyfems.dto.user;

import java.util.List;

public class UserDTO {
    private final String email;
    private final String username;
    private final List<String> roles;

    public UserDTO(String email, String username, List<String> roles) {
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
