package com.tenbit.beep.classroom.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.LookUpStudentsRequest;
import com.tenbit.beep.classroom.repository.ClassRoomRespository;
import com.tenbit.beep.classroom.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRespository classRoomRespository;

    @Override
    public List<ClassRoom> lookUpStudentsByClassRoom(LookUpStudentsRequest lookUpStudentsRequest) {

        List<ClassRoom> students = classRoomRespository.findByClassRoomName(lookUpStudentsRequest.getClassRoomName());

        if (students.isEmpty()) {
            lookUpStudentsByPrimaryClassRoom(lookUpStudentsRequest);
        }

        return students;
    }

    @Override
    public List<User> lookUpStudentsByPrimaryClassRoom(LookUpStudentsRequest lookUpStudentsRequest) {

        List<User> students = classRoomRespository.findByPrimaryClassRoomName(lookUpStudentsRequest.getClassRoomName());

        return students;
    }
}
