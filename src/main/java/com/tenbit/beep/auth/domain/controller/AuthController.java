package com.tenbit.beep.auth.domain.controller;

import com.tenbit.beep.auth.domain.domain.Authority;
import com.tenbit.beep.auth.domain.domain.User;
import com.tenbit.beep.auth.domain.dto.LoginRequest;
import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.repository.UserRepository;
import com.tenbit.beep.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
        try {
            authService.signup(signupRequest);
            return ResponseEntity.ok(Map.of("publicId", signupRequest.getPublicId(), "success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestHeader("userAgent") String userAgent,
            @RequestBody LoginRequest loginRequest) {

        String token = authService.login(loginRequest);

        Optional<User> user = userRepository.findByPublicId(loginRequest.getPublicId());

        if (userAgent.contains("Android") || userAgent.contains("iOS")) {
            user.ifPresent(u -> {
                u.setAuthority(Authority.STUDENT);
                userRepository.save(u);
            });
        } else {
            user.ifPresent(u -> {
                u.setAuthority(Authority.TEACHER);
                userRepository.save(u);
            });
        }

        return ResponseEntity.ok(Map.of("token", token));
    }
}
