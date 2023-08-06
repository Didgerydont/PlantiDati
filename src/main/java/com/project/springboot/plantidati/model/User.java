package com.project.springboot.plantidati.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "User")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    // Relationship with ForumThread
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-thread")
    private List<ForumThread> threads;

    // Relationship with ForumComment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-comment")
    private List<ForumComment> comments;

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