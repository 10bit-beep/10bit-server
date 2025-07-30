package com.tenbit.beep.auth.domain.service;

import com.tenbit.beep.auth.domain.dto.SignupRequest;

public interface AuthService {

    void signup(SignupRequest signupRequest);
}
