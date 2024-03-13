package com.example.otpgen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( (requests) -> requests
                        .requestMatchers("/", "/home", "/register", "/login", "/h2","*","/h2/","/h2/*", "/*",
                                "/verify/*", "/resend/*", "/userDetails/*", "/forum/*", "/postForm/*",
                                "/myPostForm/*", "/delete/*", "/deleteMine/*")
                        .permitAll()
                );

        return http.build();
    }

}
