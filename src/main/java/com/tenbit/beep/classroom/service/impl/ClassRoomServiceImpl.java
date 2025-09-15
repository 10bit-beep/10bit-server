package com.tenbit.beep.classroom.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.dto.ClassRoomRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import com.tenbit.beep.classroom.repository.ClassRoomRepository;
import com.tenbit.beep.classroom.service.ClassRoomService;
import com.tenbit.beep.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;

    @Override
    public List<StudentInfoResponse> lookUpStudentsByUserClass(ClassRoomRequest classRoomRequest) {

        List<User> students = classRoomRepository.findByUserClass(classRoomRequest.getUserClass());

        if (students.isEmpty()) {
            throw new UserNotFoundException("학생이 조회 되지 않았습니다.");
        }

        return students.stream()
                .map(StudentInfoResponse::from)
                .collect(Collectors.toList());
    }
}
