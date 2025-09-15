package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.dto.ClassRoomRequest;
import com.tenbit.beep.classroom.dto.ClassRoomResponse;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoomResponse> lookUpStudentsByUserClass(ClassRoomRequest classRoomRequest);
}
