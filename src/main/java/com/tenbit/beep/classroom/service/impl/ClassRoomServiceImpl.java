package com.tenbit.beep.classroom.service.impl;

import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.service.ClassRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Override
    public List<ClassRoom> lookUpStudentsByClassRoom(String classRoomName) {

        return null;
    }
}
