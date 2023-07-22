package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.RegisteredUser;
import com.project.springboot.plantidati.repository.RegisteredUserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;
    private final StrongPasswordEncryptor strongEncryptor;

    @Autowired
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository, StrongPasswordEncryptor strongEncryptor) {
        this.registeredUserRepository = registeredUserRepository;
        this.strongEncryptor = strongEncryptor;
    }

    // Encrypt Password with Jaspyt
    public RegisteredUser registerNewUser(String username, String email, String password, String location, String profileCaption) {
        String encryptedPassword = strongEncryptor.encryptPassword(password);
        RegisteredUser newUser = new RegisteredUser();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(encryptedPassword);
        newUser.setLocation(location);
        newUser.setProfileCaption(profileCaption);
        return registeredUserRepository.save(newUser);
    }

    public boolean checkUserCredentials(String username, String password) {
        RegisteredUser user = registeredUserRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return strongEncryptor.checkPassword(password, user.getPassword());
    }

    public RegisteredUser setUserName(RegisteredUser user, String newUsername) {
        user.setUsername(newUsername);
        return registeredUserRepository.save(user);
    }

    public RegisteredUser setEmail(RegisteredUser user, String newEmail) {
        user.setEmail(newEmail);
        return registeredUserRepository.save(user);
    }

    public RegisteredUser setPassword(RegisteredUser user, String newPassword) {
        String encryptedPassword = strongEncryptor.encryptPassword(newPassword);
        user.setPassword(encryptedPassword);
        return registeredUserRepository.save(user);
    }

    public RegisteredUser setLocation(RegisteredUser user, String newLocation) {
        user.setLocation(newLocation);
        return registeredUserRepository.save(user);
    }

    public RegisteredUser setProfileCaption(RegisteredUser user, String newProfileCaption) {
        user.setProfileCaption(newProfileCaption);
        return registeredUserRepository.save(user);
    }

    // Image
    public RegisteredUser setProfilePic(RegisteredUser user, byte[] newProfilePic) {
        user.setProfilePic(newProfilePic);
        return registeredUserRepository.save(user);
    }



}

