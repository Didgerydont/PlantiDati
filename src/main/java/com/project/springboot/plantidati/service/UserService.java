package com.project.springboot.plantidati.service;

import com.project.springboot.plantidati.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(int userId);

    Optional<User> findUserByUsername(String username);
}
