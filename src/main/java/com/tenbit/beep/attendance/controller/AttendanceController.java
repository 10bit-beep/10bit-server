package com.tenbit.beep.attendance.controller;

import com.tenbit.beep.attendance.dto.AttendRequest;
import com.tenbit.beep.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check")
    public ResponseEntity<String> checkAttendance(@RequestBody AttendRequest attendRequest) {
        attendanceService.markAttendance(attendRequest);
        return ResponseEntity.ok("출석 완료");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetAttendance() {
        attendanceService.checkOutAttendance();
        return ResponseEntity.ok("퇴실 완료");
    }

}
