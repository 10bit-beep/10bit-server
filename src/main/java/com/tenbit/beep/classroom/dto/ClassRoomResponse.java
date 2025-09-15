package com.tenbit.beep.classroom.dto;

import com.tenbit.beep.auth.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ClassRoomResponse {

    private int studentNumber;
    private String userClass;
    private Attendance attendance;

    public static ClassRoomResponse from(User user) {
        return new ClassRoomResponse(
                user.getStudentNumber(),
                user.getUserClass(),
                user.getAttendance()
        );
    }
}
