package com.tenbit.beep.auth.domain.jwt;

import com.tenbit.beep.auth.domain.exception.IllegalArgumentsException;
import com.tenbit.beep.auth.domain.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter extends JwtUtil {

    public JwtAuthenticationFilter(String secretKey, Long expiration) {
        super(secretKey, expiration);
    }

    public String innerIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException(e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentsException(e.getMessage());
        }
    }
}
