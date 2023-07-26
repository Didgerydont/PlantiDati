package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.exception.UsernameAlreadyExistsException;
import com.project.springboot.plantidati.model.User;
import com.project.springboot.plantidati.repository.UserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StrongPasswordEncryptor strongEncryptor;

    @Autowired
    public UserService(UserRepository userRepository, StrongPasswordEncryptor strongEncryptor) {
        this.userRepository = userRepository;
        this.strongEncryptor = strongEncryptor;
    }

    // Encrypt Password with Jaspyt
    public User registerNewUser(String username, String email, String password, String location, String profileCaption) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }
        String encryptedPassword = strongEncryptor.encryptPassword(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setLocation(location);
        newUser.setProfileCaption(profileCaption);
        return userRepository.save(newUser);
    }

    public boolean checkUserCredentials(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        return strongEncryptor.checkPassword(password, user.getPassword());
    }

    public User setUserName(User user, String newUsername) {
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    public User setEmail(User user, String newEmail) {
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

    public User setPassword(User user, String newPassword) {
        String encryptedPassword = strongEncryptor.encryptPassword(newPassword);
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    public User setLocation(User user, String newLocation) {
        user.setLocation(newLocation);
        return userRepository.save(user);
    }

    public User setProfileCaption(User user, String newProfileCaption) {
        user.setProfileCaption(newProfileCaption);
        return userRepository.save(user);
    }

    // Image
    public User setProfilePic(User user, byte[] newProfilePic) {
        user.setProfilePic(newProfilePic);
        return userRepository.save(user);
    }

    public User findUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}

