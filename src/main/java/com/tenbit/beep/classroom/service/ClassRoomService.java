package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.dto.LookUpStudentsByClassRequest;
import com.tenbit.beep.common.dto.StudentInfoResponse;

import java.util.List;

public interface ClassRoomService {

//    List<ClassRoom> lookUpStudentsByClassRoom(LookUpStudentsRequest lookUpStudentsRequest);
    List<StudentInfoResponse> lookUpStudentsByPrimaryClassRoom(LookUpStudentsByClassRequest lookUpStudentsRequest);
}
