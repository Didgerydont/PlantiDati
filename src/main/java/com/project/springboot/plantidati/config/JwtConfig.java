package com.project.springboot.plantidati.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:env.properties")
public class JwtConfig {

    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    @Bean
    public String jwtSecretKey() {
        return jwtSecretKey;
    }

}

