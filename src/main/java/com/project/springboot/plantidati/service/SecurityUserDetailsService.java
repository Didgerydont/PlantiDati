package com.project.springboot.plantidati.service;

import java.util.Optional;

import com.project.springboot.plantidati.model.RegisteredUser;
import com.project.springboot.plantidati.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private RegisteredUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        RegisteredUser user = userRepository.findByUsername(username)
                    .orElseThrow(() -< new UsernameNotFoundException("User not present"));
        return user;
    }

}