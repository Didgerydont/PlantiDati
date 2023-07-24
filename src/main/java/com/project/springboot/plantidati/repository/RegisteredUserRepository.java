package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {
    Optional<RegisteredUser> findByUsername(String username);
    RegisteredUser findByUserId(int userId);
}