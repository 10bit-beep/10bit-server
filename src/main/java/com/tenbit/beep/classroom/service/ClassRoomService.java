package com.tenbit.beep.classroom.service;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoom> lookUpStudentsByClassRoom(String classRoomName);
    List<User> lookUpStudentsByPrimaryClassRoom(String primaryClassRoomName);
}
