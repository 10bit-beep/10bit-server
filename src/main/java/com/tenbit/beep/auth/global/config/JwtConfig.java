package com.tenbit.beep.auth.global.config;

import com.tenbit.beep.auth.domain.jwt.JwtAuthenticationFilter;
import com.tenbit.beep.auth.domain.jwt.JwtFilter;
import com.tenbit.beep.auth.domain.jwt.JwtTokenProvider;
import com.tenbit.beep.auth.domain.jwt.JwtUtil;
import io.jsonwebtoken.Jwt;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {

    private String secret;
    private Long expiration;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret, expiration);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(secret, expiration);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(secret, expiration);
    }

    @Bean
    public JwtFilter jwtFilter() {
        System.out.println("JWT 필터 동작");
        return new JwtFilter(jwtAuthenticationFilter());
    }
}
