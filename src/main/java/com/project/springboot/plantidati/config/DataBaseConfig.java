package com.project.springboot.plantidati.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:env.properties")
public class DataBaseConfig {

        @Value("${hostname}")
        private String dataSourceHostname;

        @Value("${username}")
        private String dataSourceUsername;

        @Value("${password}")
        private String dataSourcePassword;


}