package com.tenbit.beep.classroom.service;

import com.tenbit.beep.classroom.domain.ClassRoom;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoom> lookUpStudentsByClassRoom(String classRoomName);
}
