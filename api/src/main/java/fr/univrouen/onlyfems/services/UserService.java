package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.entities.User;
import fr.univrouen.onlyfems.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to interact with users in database.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Return the list of users in database.
     *
     * @return The list of users.
     */
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     * Return a user using an ID.
     *
     * @param id ID of a user.
     * @return The user if found.
     */
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    /**
     * Delete a user in database using an ID.
     *
     * @param id ID of a user.
     */
    public void deleteUserWithId(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Update a user in database.
     *
     * @param user User to update with updated values.
     * @return The user updated.
     */
    public User updateUser(User user) {
        User userToUpdate = getUserById(user.getId());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(userToUpdate.getPassword());
        userToUpdate.setRole(userToUpdate.getRole());
        return userRepository.save(userToUpdate);
    }
}
