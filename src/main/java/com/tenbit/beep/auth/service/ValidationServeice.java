package com.tenbit.beep.auth.service;

import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;

public interface ValidationServeice {

    void checkNull(SignupRequest signupRequest);
    void checkNull(LoginRequest loginRequest);
    void checkPublicId(String publicId);
    void checkPassword(String password);
    void checkStudentNumber(int studentNumber);
    void checkEmail(String email);

    void checkExistAccount(String publicId, String email);
}
