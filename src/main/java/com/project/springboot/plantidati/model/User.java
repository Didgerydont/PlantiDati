package com.project.springboot.plantidati.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }


}