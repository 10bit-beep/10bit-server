package com.tenbit.beep.auth.controller;

import com.tenbit.beep.auth.domain.Authority;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;
import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    // login, signup 함수 가져옴
    private final AuthService authService;
    // Jpa 함수 가져옴
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
        return ResponseEntity.ok(Map.of("publicId", signupRequest.getPublicId(), "success", true));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestHeader("userAgent") String userAgent, // 웹, 앱 구분하는 부분 받아옴
            @RequestBody LoginRequest loginRequest) {
        String token = authService.login(userAgent, loginRequest);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
