package com.project.springboot.plantidati.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CSRF protection
        http.csrf(csrf ->
                // Exclude the "/auth/authenticate" endpoint from CSRF protection
                csrf.ignoringRequestMatchers(
                        new AntPathRequestMatcher("/auth/authenticate"),
                        new AntPathRequestMatcher("/auth/register")
                )
        );

        // Configure authorization rules
        http.authorizeHttpRequests(authorize -> {
            // Endpoints that are available for all users
            authorize
                    .requestMatchers("/", "/index", "/login", "/auth/isusernametaken",
                            "/auth/register", "/registrationpage", "/viewcalendar", "/data",
                            "/content", "/auth/authenticate", "/registrationsuccess").permitAll()
                    // Specify endpoints only accessable to authorised users
                    .requestMatchers("/profile/**", "/forum/**", "/createCalendar/**").authenticated()
                    // Authorise Static Resources
                    .requestMatchers("/css/**", "/images/**", "/scripts/**").permitAll()
                    // Deny all other requests
                    .anyRequest().denyAll();
        });

        // Configure session management
        http.sessionManagement(sessionManagement ->
                // Set session creation policy to stateless
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Configure the Authentication Provider and the JwtAuthenticationFilter
        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Build the SecurityFilterChain
        return http.build();
    }
}
