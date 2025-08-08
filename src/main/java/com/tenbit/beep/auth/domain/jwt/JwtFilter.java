package com.tenbit.beep.auth.domain.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuthenticationFilter jwtAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.startsWith("/auth/") ||
                path.startsWith("/email/") ||
                path.startsWith("/last/") ||
                path.startsWith("/main/") ||
                path.startsWith("/img/") ||
                path.startsWith("/login/") ||
                path.startsWith("/logout/") ||
                path.startsWith("/room/") ||
                path.startsWith("/class/") ||
                path.startsWith("/signup/")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(path + ": wrong");

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                jwtAuth.validateToken(token);
                String innerId = jwtAuth.innerIdFromToken(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
