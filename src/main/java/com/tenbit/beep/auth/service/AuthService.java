package com.tenbit.beep.auth.service;

import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;

public interface AuthService {

    void signup(SignupRequest signupRequest);
    String login(LoginRequest loginRequest);
}
