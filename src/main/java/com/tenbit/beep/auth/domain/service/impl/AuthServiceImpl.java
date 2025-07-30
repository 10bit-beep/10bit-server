package com.tenbit.beep.auth.domain.service.impl;

import com.tenbit.beep.auth.domain.domain.User;
import com.tenbit.beep.auth.domain.dto.LoginRequest;
import com.tenbit.beep.auth.domain.dto.SignupRequest;
import com.tenbit.beep.auth.domain.exception.AlreadyUsingIdException;
import com.tenbit.beep.auth.domain.exception.IllegalArgumentsException;
import com.tenbit.beep.auth.domain.exception.UserNotFoundException;
import com.tenbit.beep.auth.domain.exception.ValueMissingException;
import com.tenbit.beep.auth.domain.jwt.JwtAuthenticationFilter;
import com.tenbit.beep.auth.domain.jwt.JwtTokenProvider;
import com.tenbit.beep.auth.domain.repository.UserRepository;
import com.tenbit.beep.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
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

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void signup(SignupRequest signupRequest) {
        int studentNumber = signupRequest.getStudentNumber();
        String name = signupRequest.getName();
        String publicId = signupRequest.getPublicId();
        String password = signupRequest.getPassword();
        String email = signupRequest.getEmail();

        // null값 확인
        if (!(name != null && publicId != null && password != null && email != null)) {
            throw new ValueMissingException("빈 값을 넣을 수 없습니다.");
        }

        // 학번 확인
        int[] studentNumbers = new int[4];
        studentNumbers[0] = studentNumber / 1000;
        studentNumbers[1] = studentNumber % 1000 / 100;
        studentNumbers[2] = studentNumber % 100 / 10;
        studentNumbers[3] = studentNumber % 10;

        if ((studentNumbers[0] == 0 && studentNumbers[1] == 0 && studentNumbers[2] == 0 && studentNumbers[3] == 0)      // 교사일 경우
            || (studentNumbers[0] >= 1 && studentNumbers[0] <= 3         // 학년
                && studentNumbers[1] >= 1 && studentNumbers[1] <= 4      // 반
                && studentNumbers[2] >= 0 && studentNumbers[2] <= 1)) {  // 번호 앞자리
            if ((studentNumbers[2] == 1) && (studentNumbers[3] == 9)) {  // 번호 뒷자리
                throw new IllegalArgumentsException("올바르지 않은 학번입니다.");
            }
        }

        // 이름 확인
        if (name.length() >= 2 && name.length() <= 10) {
            for (char ch : name.toCharArray()) {
                if (ch < 0xAC00 || ch > 0xD7A3) {
                    throw new IllegalArgumentsException("이름은 한글만 가능합니다.");
                }
            }
        } else {
            throw new IllegalArgumentsException("이름은 2-10자만 가능합니다.");
        }

        // 아이디 확인
        if (publicId.length() >= 4 && publicId.length() <= 15) {
            boolean hasAlpha = false;
            boolean hasDigit = false;

            for (char ch : publicId.toCharArray()) {
                if (Character.isLetter(ch)) {
                    if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                        throw new IllegalArgumentsException("아이디는 영어와 숫자만 가능합니다.");
                    }
                    hasAlpha = true;
                } else if (Character.isDigit(ch)) {
                    hasDigit = true;
                } else {
                    throw new IllegalArgumentsException("아이디는 영어와 숫자만 가능합니다.");
                }
            }

            if (!(hasAlpha && hasDigit)) {
                throw new IllegalArgumentsException("아이디에 영어와 숫자를 하나씩은 포함해야됩니다.");
            }
        } else {
            throw new IllegalArgumentsException("아이디는 4-15자만 가능합니다.");
        }

        // 비밀번호 확인
        if (password.length() >= 8 && password.length() <= 20) {
            boolean hasAlpha = false;
            boolean hasDigit = false;
            boolean hasSpecial = false;

            String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>?/~`";
            char prevChar = '\0';
            int repeatCount = 1;

            for (char ch : password.toCharArray()) {
                if (ch == prevChar) {
                    repeatCount++;
                    if (repeatCount >= 3) {
                        throw new IllegalArgumentsException("같은 문자가 3번 이상 연속될 수 없습니다.");
                    }
                } else {
                    repeatCount = 1;
                }
                prevChar = ch;

                if (Character.isLetter(ch)) {
                    hasAlpha = true;
                }
                else if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
                else if (specialChars.contains(String.valueOf(ch))) {
                    hasSpecial = true;
                }
                else {
                    throw new IllegalArgumentsException("비밀번호는 영어, 숫자, 특수문자만 가능합니다.");
                }
            }

            if (!hasAlpha || !hasDigit || !hasSpecial) {
                throw new IllegalArgumentsException("비밀번호는 영어, 숫자, 특수문자를 최소 1개씩 포함해야 합니다.");
            }
        } else {
            throw new IllegalArgumentsException("비밀번호는 8~20자만 가능합니다.");
        }

        // 이메일 확인
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentsException("유효하지 않은 이메일 형식입니다.");
        }

        // 최종 확인 후 저장
        if (userRepository.findByPublicId(publicId).isEmpty()) {
            User user = new User(studentNumber, name, publicId, passwordEncoder.encode(password), email);
            userRepository.save(user);
        } else {
            throw new AlreadyUsingIdException("이미 사용중인 아이디입니다.");
        }
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
