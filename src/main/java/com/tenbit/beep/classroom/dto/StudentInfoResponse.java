package com.tenbit.beep.classroom.dto;

import com.tenbit.beep.attendance.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentInfoResponse {

    private int studentNumber;
    private String classRoomName;
    private Attendance attendance;

    public static StudentInfoResponse from(User user) {
        return new StudentInfoResponse(
                user.getStudentNumber(),
                user.getPrimaryClassRoomName(),
                user.getAttendance()
        );
    }
}
