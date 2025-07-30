package com.tenbit.beep.auth.domain.service.impl;

import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.exception.ValueMissingException;
import com.tenbit.beep.auth.domain.service.AuthService;

public class AuthServiceImpl implements AuthService {

    @Override
    public void signup(SignupRequest signupRequest) {
        int studentNumber = signupRequest.getStudentNumber();
        String name = signupRequest.getName();
        String publicId = signupRequest.getPublicId();
        String password = signupRequest.getPassword();
        String email = signupRequest.getEmail();

        // null값 확인
        if (!(name != null && publicId != null && password != null && email != null)) {
            throw new ValueMissingException();
        }

        // 학번 각자리 수 저장, 계산하기 쉽게 할려고 씀
        int[] studentNumbers = new int[4];
        studentNumbers[0] = studentNumber / 1000;
        studentNumbers[1] = studentNumber % 1000 / 100;
        studentNumbers[2] = studentNumber % 100 / 10;
        studentNumbers[3] = studentNumber % 10;

        if ((studentNumbers[0] == 0 && studentNumbers[1] == 0 && studentNumbers[2] == 0 && studentNumbers[3] == 0)      // 교사일 경우
            || (studentNumbers[0] >= 1 && studentNumbers[0] <= 3        // 학년
                && studentNumbers[1] >= 1 && studentNumbers[1] <= 4     // 반
                && studentNumbers[2] >= 0 && studentNumbers[2] <= 1)) {  // 번호 앞자리
            if ((studentNumbers[2] == 1) && (studentNumbers[3] == 9)) { // 번호 뒷자리
                throw new IllegalArgumentException();
            }

            for (char ch : name.toCharArray()) {
                if (ch < 0xAC00 || ch > 0xD7A3) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
