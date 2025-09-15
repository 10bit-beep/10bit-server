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

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final AttendRepository attendRepository;
    private final ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void markAttendance(AttendRequest attendRequest) {
        String nfcTag = getNfcTagFromReader();

        User user = userRepository.findByPublicId(attendRequest.getPublicId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 찾을 수 없음."));

        if (user.getAttendance().equals(Attendance.ATTEND)) {
            throw new AttendException("이미 출석함");
        }

        if (!nfcTag.equals(user.getUserClass()) && !nfcTag.equals(user.getClub())) {
            System.out.println(user.getUserClass());
            System.out.println(nfcTag);
            throw new AttendException("잘못된 실");
        }

        Attend attend = new Attend();
        attend.setUser(user);

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
    public void checkOutAttendance() {
        userRepository.resetAllAttendance(Attendance.ABSENT);
    }

    private String getNfcTagFromReader() {
        // 임시값
        return "1학년 0반";
    }
}
