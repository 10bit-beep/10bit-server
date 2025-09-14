package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.dto.ClassroomRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;

import java.util.List;

public interface ClassRoomService {

    List<StudentInfoResponse> lookUpStudentsByUserClass(ClassroomRequest classroomRequest);
}
