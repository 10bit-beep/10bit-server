package com.tenbit.beep.attendance.service.impl;


import com.tenbit.beep.attendance.service.AttendanceService;
import com.tenbit.beep.attendance.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;

    @Override
    public void markAttendance(String publicId) {
        User user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 찾을 수 없음."));
        user.setAttendance(Attendance.TRUE);
        userRepository.save(user);
    }

    @Override
    public void CheckOutAttendance() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.setAttendance(Attendance.FALSE);
        }
        userRepository.saveAll(users);

    }
}
