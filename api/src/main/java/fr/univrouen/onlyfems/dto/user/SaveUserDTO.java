package fr.univrouen.onlyfems.dto.user;

import fr.univrouen.onlyfems.constants.Roles;

import java.util.List;

/**
 * DTO used to create or update users.
 */
public class SaveUserDTO {
    private final String email;
    private final String username;
    private final String password;
    private final List<Roles> roles;

    public SaveUserDTO(String email, String username, String password, List<Roles> roles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Roles> getRoles() {
        return roles;
    }
}
