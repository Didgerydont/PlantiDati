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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CSRF protection
        http
                .csrf().disable()
                // Configure CORS
                .cors().configurationSource(corsConfigurationSource())
                .and()
                // Configure authorization rules
                .authorizeHttpRequests(authorize -> {
                    // Endpoints that are available for all users
                    authorize
                            .requestMatchers("/", "/index", "/login", "/auth/isusernametaken",
                                    "/auth/register", "/registrationpage", "/viewcalendar", "/data",
                                    "/content", "/auth/authenticate", "/registrationsuccess",
                                    "/plant/getAll", "/variety/plant/{plantId}",
                                    "plant/varieties/{plantId}").permitAll()
                            // Specify endpoints only accessable to authorised users
                            .requestMatchers("/profile", "/auth/getProfile", "/forum",
                                    "/createCalendar", "/profile/uploadProfilePic",
                                    "/profile/{userId}/updatePassword",
                                    "/profile/{userId}/updateLocation",
                                    "/profile/{userId}/updateProfileCaption",
                                    "/calendarCreate", "/calendar/create",
                                    "/firstCalendarEntry", "/calendarentry/createfirstcalenderentry",
                                    "/variety/createVariety").authenticated()
                            // Authorise Static Resources
                            .requestMatchers("/css/**", "/images/**", "/scripts/**").permitAll()
                            // Deny all other requests
                            .anyRequest().denyAll();
                })

                // Configure session management
                .sessionManagement(sessionManagement ->
                        // Set session creation policy to stateless
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Configure the Authentication Provider and the JwtAuthenticationFilter
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Build the SecurityFilterChain
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
