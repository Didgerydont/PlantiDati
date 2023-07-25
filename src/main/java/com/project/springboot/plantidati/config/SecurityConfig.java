package com.project.springboot.plantidati.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // .requestMatchers("/index").authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/logout");
        return http.build();
    }


}
