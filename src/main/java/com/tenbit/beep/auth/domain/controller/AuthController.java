package com.tenbit.beep.auth.domain.controller;

import com.tenbit.beep.auth.domain.dto.LoginRequest;
import com.tenbit.beep.auth.domain.dto.SendVerificationEmailRequest;
import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.service.AuthService;
import com.tenbit.beep.auth.domain.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;

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

    @GetMapping("/email")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        try {
            SendVerificationEmailRequest emailRequest = new SendVerificationEmailRequest();
            emailRequest.setEmail(email);

            emailService.sendVerificationEmail(emailRequest);
            return ResponseEntity.ok("전송 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("전송 실패" + e.getMessage());
        }
    }
}
