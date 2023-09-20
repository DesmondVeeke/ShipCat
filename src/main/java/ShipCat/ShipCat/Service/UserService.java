package ShipCat.ShipCat.Service;

import ShipCat.ShipCat.Interface.UserRepository;
import ShipCat.ShipCat.Model.User;
import ShipCat.ShipCat.Validation.Exceptions.UserValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger;

    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, Logger logger, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.logger = logger;
        this.userValidator = userValidator;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        logger.info("Created: " + user.toString());
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());

            return userRepository.save(existingUser);
        }
        logger.error("Could not find user associated with the sent updated user: " + updatedUser.toString());
        return null;
    }

    public boolean deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true;
        }
        logger.error("Could not delete user associated with the sent id: " + id + ". It was probably not found.");
        return false;
    }
}
