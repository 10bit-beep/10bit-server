package com.tenbit.beep.auth.domain.service;

import com.tenbit.beep.auth.domain.dto.SendVerificationEmailRequest;

public interface EmailService {

    String sendVerificationEmail(SendVerificationEmailRequest emailRequest);
}