package com.tenbit.beep.clubroom.service;

import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import com.tenbit.beep.clubroom.dto.ClubRoomRequest;

import java.util.List;

public interface ClubRoomService {

    List<StudentInfoResponse> lookUpStudentsByClub(ClubRoomRequest clubRoomRequest);
}
