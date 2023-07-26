package com.project.springboot.plantidati.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                // .requestMatchers("/index").authenticated()
//                .anyRequest().permitAll();
//        http.formLogin().loginPage("/login")
//                .and().logout().logoutSuccessUrl("/logout");
//        return http.build();
//    }

//    @Bean
//    InMemoryUserDetailsManagerConfigurer users() {
//        return new InMemoryUserDetailsManagerConfigurer(
//        );
//    }

//    @Bean
//    JdbcUserDetailsManager users(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.ignoringRequestMatchers())
//                .authorizeRequests(auth -> auth.anyRequest()
//                        .authenticated()
//                )
//                .formLogin(withDefaults())
//                .build();
//    }

}
