package com.project.springboot.plantidati.repository;

import com.project.springboot.plantidati.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {

}