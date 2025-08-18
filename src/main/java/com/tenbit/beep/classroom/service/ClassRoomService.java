package com.tenbit.beep.classroom.service;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.LookUpStudentsRequest;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoom> lookUpStudentsByClassRoom(LookUpStudentsRequest lookUpStudentsRequest);
    List<User> lookUpStudentsByPrimaryClassRoom(LookUpStudentsRequest lookUpStudentsRequest);
}
