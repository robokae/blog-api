package com.robokae.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfig {

    @Value("${security.enable-csrf}")
    private boolean enableCsrf;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        if (!enableCsrf) {
            http.csrf(AbstractHttpConfigurer::disable);
        }

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").authenticated()
                .requestMatchers("/api/**").permitAll());

        return http.build();
    }
}
