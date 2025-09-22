package com.tenbit.beep.attendance.service.impl;


import com.tenbit.beep.attendance.domain.Attend;
import com.tenbit.beep.attendance.dto.AttendRequest;
import com.tenbit.beep.attendance.repository.AttendRepository;
import com.tenbit.beep.attendance.service.AttendanceService;
import com.tenbit.beep.auth.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.common.exception.AttendException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final AttendRepository attendRepository;
    private final ThreadPoolTaskScheduler taskScheduler;

    @Override
    @Transactional
    public void markAttendance(AttendRequest attendRequest) {
        String nfcTag = attendRequest.getNfcTag();

        if (nfcTag == null || nfcTag.trim().isEmpty()) {
            throw new AttendException("NFC태그 값이 비어있음");
        }

        User user = userRepository.findByPublicId(attendRequest.getPublicId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 찾을 수 없음."));

        if (user.getAttendance().equals(Attendance.ATTEND)) {
            throw new AttendException("이미 출석함");
        }

        if (!nfcTag.equals(user.getUserClass()) && !nfcTag.equals(user.getClub())) {
            System.out.println(user.getUserClass());
            System.out.println(user.getClub());
            System.out.println(nfcTag);
            throw new AttendException("잘못된 실");
        }

        user.setAttendance(Attendance.ATTEND);
        userRepository.save(user);

        Attend attend = Attend.builder()
                .user(user)
                .build();

        // 자동 퇴실
        attendRepository.save(attend);

        taskScheduler.schedule(
                () -> {
                    user.setAttendance(Attendance.ABSENT);
                    userRepository.save(user);
                },
                java.util.Date.from(java.time.Instant.now().plusSeconds(30))
        );

    }

    @Override
    @Transactional
    public void checkOutAttendance() {
        userRepository.resetAllAttendance(Attendance.ABSENT);
    }
}
