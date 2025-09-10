package com.tenbit.beep.auth.service.impl;

import com.tenbit.beep.auth.service.ValidationServeice;
import com.tenbit.beep.common.exception.IllegalArgumentsException;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationServeice {

    @Override
    public void checkNull(String publicId, String password, String email, String club) {
        if (publicId.isEmpty() || password.isEmpty() || email.isEmpty() || club.isEmpty()) {
            throw new IllegalArgumentsException("빈 값 존재");
        }
    }

    @Override
    public void checkPublicId(String publicId) {
        if (publicId.length() < 4 || publicId.length() > 20) {
            throw new IllegalArgumentsException("길이가 잘못됨");
        }
        if (!publicId.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentsException("영어, 숫자 이외의 글자");
        }
        if (publicId.matches(".*(.)\\1{2,}.*")) {
            throw new IllegalArgumentsException("3글자 이상 연속");
        }
        if (!publicId.matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
            throw new IllegalArgumentsException("영어, 숫자 포함 안 됨");
        }
    }

    @Override
    public void checkPassword(String password) {
        if (password.length() < 6 || password.length() > 20) {
            throw new IllegalArgumentsException("길이가 잘못됨");
        }
        if (!password.matches("^[A-Za-z0-9@$!%*?&]+$")) {
            throw new IllegalArgumentsException("영어, 숫자, 특수문자 이외의 글자");
        }
        if (password.matches(".*(.)\\1{2,}.*")) {
            throw new IllegalArgumentsException("3글자 이상 연속");
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&]).+$")) {
            throw new IllegalArgumentsException("영어, 숫자, 특수문자를 포함 안 됨");
        }
    }

    @Override
    public void checkStudentNumber(int studentNumber) {

    }

    @Override
    public void checkEmail(String email) {

    }

    @Override
    public void checkClub(String club) {

    }

    @Override
    public void checkExistAccount(String publicId, String email) {

    }
}
