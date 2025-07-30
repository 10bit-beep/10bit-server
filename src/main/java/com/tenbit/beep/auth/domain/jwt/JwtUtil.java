package com.tenbit.beep.auth.domain.jwt;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private final Key key;
    private final Long expiration;

    public JwtUtil(String secretKey, Long expiration) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.expiration = expiration;
    }
}
