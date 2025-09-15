package com.tenbit.beep.auth.service;

import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;
import com.tenbit.beep.common.exception.IllegalArgumentsException;

public interface AuthService {

    void signup(SignupRequest signupRequest);
    String login(String userAgent, LoginRequest loginRequest);
}
