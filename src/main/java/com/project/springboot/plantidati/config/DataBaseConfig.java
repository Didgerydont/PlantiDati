package com.project.springboot.plantidati.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class DataBaseConfig {

    @Configuration
    @PropertySource("classpath:env.properties")
    public class DatabaseConfig {

        @Value("${hostname}")
        private String dataSourceUrl;

        @Value("${username}")
        private String dataSourceUsername;

        @Value("${password}")
        private String dataSourcePassword;

    }
}