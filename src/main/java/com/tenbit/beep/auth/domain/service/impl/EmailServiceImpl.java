package com.tenbit.beep.auth.domain.service.impl;

import com.tenbit.beep.auth.domain.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    protected String generateCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    @Override
    public String sendVerificationEmail(String email) {
        String code = generateCode();
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("[이메일 인증 코드]");
        message.setText("삑 인증코드: " + code);

        mailSender.send(message);
        return code;
    }
}
