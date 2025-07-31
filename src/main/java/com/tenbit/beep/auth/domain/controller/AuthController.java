package com.tenbit.beep.auth.domain.controller;

import com.tenbit.beep.auth.domain.dto.LoginRequest;
import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return "Signup success";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
}
