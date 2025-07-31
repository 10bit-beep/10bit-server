package com.tenbit.beep.auth.domain.service;

public interface EmailService {

    public String sendVerificationEmail(String email);
    protected String generateCode();
}
