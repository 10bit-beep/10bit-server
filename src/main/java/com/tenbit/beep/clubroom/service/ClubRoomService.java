package com.tenbit.beep.clubroom.service;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.dto.LookUpStudentsByClassRequest;
import com.tenbit.beep.clubroom.domain.ClubRoom;
import com.tenbit.beep.clubroom.dto.LookUpStudentsByClubRequest;
import com.tenbit.beep.common.dto.StudentInfoResponse;

import java.util.List;

public interface ClubRoomService {

//    List<ClubRoom> lookUpStudentsByClubRoom(LookUpStudentsByClubRequest lookUpStudentsRequest);
    List<StudentInfoResponse> lookUpStudentsByPrimaryClubRoom(LookUpStudentsByClubRequest lookUpStudentsRequest);
}
