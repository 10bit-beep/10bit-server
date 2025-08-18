package com.tenbit.beep.classroom.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.repository.ClassRoomRespository;
import com.tenbit.beep.classroom.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRespository classRoomRespository;

    @Override
    public List<ClassRoom> lookUpStudentsByClassRoom(String classRoomName) {

        List<ClassRoom> students = classRoomRespository.findByClassRoomName(classRoomName);

        if (students.isEmpty()) {
            lookUpStudentsByPrimaryClassRoom(classRoomName);
        }

        return students;
    }

    @Override
    public List<User> lookUpStudentsByPrimaryClassRoom(String primaryClassRoomName) {

        List<User> students = classRoomRespository.findByPrimaryClassRoomName(primaryClassRoomName);

        return students;
    }
}
