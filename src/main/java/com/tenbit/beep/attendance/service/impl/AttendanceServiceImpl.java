package com.tenbit.beep.attendance.service.impl;


import com.tenbit.beep.attendance.service.AttendanceService;
import com.tenbit.beep.attendance.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final ThreadPoolTaskScheduler taskScheduler;


    @Override
    public void markAttendance(String publicId) {
        User user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음."));
        user.setAttendance(Attendance.TRUE);
        userRepository.save(user);

        taskScheduler.schedule(
                () -> {
                    user.setAttendance(Attendance.FALSE);
                    userRepository.save(user);
                },
                java.util.Date.from(java.time.Instant.now().plusSeconds(30))
        );
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
