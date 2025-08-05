package com.stokmanager.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF koruması devre dışı
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Herkese izin ver
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
