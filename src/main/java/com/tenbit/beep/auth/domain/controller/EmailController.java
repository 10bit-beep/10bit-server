package com.tenbit.beep.auth.domain.controller;

import com.tenbit.beep.auth.domain.dto.SendVerificationEmailRequest;
import com.tenbit.beep.auth.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class EmailController {

    private final EmailService emailService;

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

    @GetMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String inputCode) {
        if (emailService.verifyCode(email, inputCode)) {
            return ResponseEntity.ok("인증 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패");
        }
    }
}
