package com.tenbit.beep.auth.service;

public interface ValidationServeice {

    void checkNull(String publicId, String password, String email, String club);
    void checkPublicId(String publicId);
    void checkPassword(String password);
    void checkStudentNumber(int studentNumber);
    void checkEmail(String email);
    void checkClub(String club);

    void checkExistAccount(String publicId, String email);
}
