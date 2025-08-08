package com.tenbit.beep.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check")
    public ResponseEntity<String> checkAttendance(@RequestParam String publicId) {
        attendanceService.markAttendance(publicId);
        return ResponseEntity.ok("출석 완료");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetAttendance() {
        attendanceService.resetAllAttendance();
        return ResponseEntity.ok("초기화 완료");
    }

}
