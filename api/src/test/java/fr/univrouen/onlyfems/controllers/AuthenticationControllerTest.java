package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.entities.User;
import fr.univrouen.onlyfems.services.AuthenticationService;
import fr.univrouen.onlyfems.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AuthenticationControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setup() {
        User userTest = new User("test", "test", List.of("ROLE_USER"));

        when(userService.getUserById(anyInt())).thenReturn(userTest);
        when(userService.listUsers()).thenReturn(List.of(userTest));
        when(userService.createOrUpdateUser(userTest)).thenReturn(userTest);
    }
}