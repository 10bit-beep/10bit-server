package com.tenbit.beep.auth.global.config;

import com.tenbit.beep.auth.domain.jwt.JwtUtil;
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
}
