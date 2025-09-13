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
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final AttendRepository attendRepository;

    @Override
    public void markAttendance(AttendRequest attendRequest) {
        User user = userRepository.findByPublicId(attendRequest.getPublicId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 찾을 수 없음."));

        if (user.getAttendance().equals(Attendance.ATTEND)) {
            throw new AttendException("이미 출석함");
        }

        Attend attend = new Attend();
        attend.setUser(user);

        attendRepository.save(attend);
    }

    @Override
    public void checkOutAttendance() {
        userRepository.resetAllAttendance(Attendance.ABSENT);
    }
}
