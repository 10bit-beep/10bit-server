package com.tenbit.beep.auth.domain.controller;

import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
