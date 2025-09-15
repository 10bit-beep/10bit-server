package com.tenbit.beep.auth.service.impl;

import com.tenbit.beep.auth.domain.Authority;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;
import com.tenbit.beep.auth.service.ValidationServeice;
import com.tenbit.beep.common.exception.AlreadyUsingIdException;
import com.tenbit.beep.common.exception.IllegalArgumentsException;
import com.tenbit.beep.common.exception.UserNotFoundException;
import com.tenbit.beep.common.exception.ValueMissingException;
import com.tenbit.beep.auth.jwt.JwtAuthenticationFilter;
import com.tenbit.beep.auth.jwt.JwtTokenProvider;
import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ValidationServeice validationServeice;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void signup(SignupRequest signupRequest) {
        // 웹에서 1차 검사 진행한 값을 가져옴

        // ""값 방지
        validationServeice.checkNull(signupRequest);

        // dto 값 추출
        int studentNumber = signupRequest.getStudentNumber();
        String publicId = signupRequest.getPublicId();
        String password = signupRequest.getPassword();
        String email = signupRequest.getEmail();
        String club = signupRequest.getClub();

        // 아이디 확인
        validationServeice.checkPublicId(publicId);

        // 비밀번호 확인
        validationServeice.checkPassword(password);

        // 학번 확인
        validationServeice.checkStudentNumber(studentNumber);

        // 이메일 확인
        validationServeice.checkEmail(email);

        // 실명 확인 생략

        // 중복 데이터 확인
        validationServeice.checkExistAccount(publicId, email);

        User user = new User(studentNumber, publicId, passwordEncoder.encode(password), email, club);
        userRepository.save(user);
    }

    @Override
    public String login(String userAgent, LoginRequest loginRequest) {
        // 웹에서 1차 검사 진행한 값을 가져옴

        // ""값 방지
        validationServeice.checkNull(loginRequest);

        String publicId = loginRequest.getPublicId();
        String password = loginRequest.getPassword();

        Optional<User> tempUser = userRepository.findByPublicId(publicId);

        // 실존 유저인지 확인
        if (tempUser.isEmpty()) {
            throw new UserNotFoundException("유저 조회 실패");
        }

        User user = tempUser.get();

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("유저 조회 실패");
        }

        // 로그인 환경 확인
        Authority authority;

        if (userAgent.contains("Android") || userAgent.contains("iOS")) {
            authority = Authority.STUDENT;
        } else {
            authority = Authority.TEACHER;
        }

        user.setAuthority(authority);
        return jwtTokenProvider.generateToken(String.valueOf(user.getInnerId()));
    }
}
