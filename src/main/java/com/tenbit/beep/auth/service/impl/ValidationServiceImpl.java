package com.tenbit.beep.auth.service.impl;

import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.auth.service.ValidationServeice;
import com.tenbit.beep.common.exception.AlreadyUsingIdException;
import com.tenbit.beep.common.exception.IllegalArgumentsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationServeice {

    private final UserRepository userRepository;

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
        if (studentNumber == 1000) {
            return;
        }

        if (studentNumber < 1101 || studentNumber > 3418) {
            throw new IllegalArgumentsException("학번 오류");
        }

        int grade = studentNumber / 1000;             // 첫째 자리
        int classNum = (studentNumber / 100) % 10;    // 둘째 자리
        int number = studentNumber % 100;             // 셋째, 넷째 자리

        if (grade < 1 || grade > 3) {
            throw new IllegalArgumentsException("학번 오류");
        }
        if (classNum < 1 || classNum > 4) {
            throw new IllegalArgumentsException("학번 오류");
        }
        if (number < 1 || number > 18) {
            throw new IllegalArgumentsException("학번 오류");
        }
    }

    @Override
    public void checkEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentsException("이메일 오류");
        }
    }

    @Override
    public void checkClub(String club) {

    }

    @Override
    public void checkExistAccount(String publicId, String email) {
        if (!userRepository.existsByPublicId(publicId) ||
                !userRepository.existsByEmail(email)) {
            throw new AlreadyUsingIdException("이미 존재함");
        }
    }
}
