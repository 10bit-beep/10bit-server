package com.tenbit.beep.auth.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.dto.LoginRequest;
import com.tenbit.beep.auth.dto.SignupRequest;
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

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void signup(SignupRequest signupRequest) {
        // 웹에서 1차 검사 진행한 값을 가져옴

        // dto 값 추출
        int studentNumber = signupRequest.getStudentNumber();
        String publicId = signupRequest.getPublicId();
        String password = signupRequest.getPassword();
        String email = signupRequest.getEmail();
        String club = signupRequest.getClub();

        // ""값 방지
        if (publicId.isEmpty() || password.isEmpty() || email.isEmpty() || club.isEmpty()) {
            throw new ValueMissingException("빈 값이 존재");
        }

        // 아이디 유효성 검사
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

        // 비밀번호 유효성 검사
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

        // 학번 확인


        // 중복 데이터 확인
        if (!userRepository.existsByPublicId(publicId) ||
            !userRepository.existsByEmail(email)) {
            throw new AlreadyUsingIdException("이미 존재함");
        }

        User user = new User(studentNumber, publicId, passwordEncoder.encode(password), email, club);
        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        String publicId = loginRequest.getPublicId();
        String password = loginRequest.getPassword();

        // null값 확인
        if (!(publicId != null && password != null)) {
            throw new ValueMissingException("빈 값을 넣을 수 없습니다.");
        }

        Optional<User> optionalUser = userRepository.findByPublicId(publicId);

        // 실존 유저인지 확인
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(publicId + "의 유저를 찾을 수 없습니다.");
        }

        User user = optionalUser.get();

        // 비밀번호 확인
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtTokenProvider.generateToken(String.valueOf(user.getInnerId()));
        } else {
            throw new UserNotFoundException("비밀번호가 일치하지 않습니다.");
        }
    }
}
