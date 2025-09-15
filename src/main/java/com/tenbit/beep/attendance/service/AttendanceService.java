package com.tenbit.beep.attendance.service;

import com.tenbit.beep.attendance.dto.AttendRequest;

public interface AttendanceService {
    void markAttendance(AttendRequest attendRequest);
    void checkOutAttendance();
}
