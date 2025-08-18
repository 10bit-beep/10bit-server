package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.LookUpStudentsRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoom> lookUpStudentsByClassRoom(LookUpStudentsRequest lookUpStudentsRequest);
    List<StudentInfoResponse> lookUpStudentsByPrimaryClassRoom(LookUpStudentsRequest lookUpStudentsRequest);
}
