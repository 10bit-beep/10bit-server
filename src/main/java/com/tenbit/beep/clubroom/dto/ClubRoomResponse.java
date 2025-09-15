package com.tenbit.beep.clubroom.dto;

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
public class ClubRoomResponse {

    private int studentNumber;
    private String userClub;
    private Attendance attendance;

    public static ClubRoomResponse from(User user) {
        return new ClubRoomResponse(
                user.getStudentNumber(),
                user.getClub(),
                user.getAttendance()
        );
    }
}
