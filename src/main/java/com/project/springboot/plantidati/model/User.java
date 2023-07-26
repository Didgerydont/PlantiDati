package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "User")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    private int userId;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password must be created")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Location cannot be blank.")
    @Column(name = "location")
    private String location;

    @Column(name = "profile_caption", columnDefinition = "TEXT")
    private String profileCaption;

    @Lob
    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Enumerated(EnumType.STRING)
    private Role role;

    // UserDetails overrides
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPassword() {
        return password;
    }


    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileCaption() {
        return profileCaption;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileCaption(String profileCaption) {
        this.profileCaption = profileCaption;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }


}