package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.dto.ClassRoomRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;

import java.util.List;

public interface ClassRoomService {

    List<StudentInfoResponse> lookUpStudentsByUserClass(ClassRoomRequest classRoomRequest);
}
