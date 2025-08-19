package com.tenbit.beep.classroom.service.impl;

import com.tenbit.beep.auth.domain.Authority;
import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.auth.repository.UserRepository;
import com.tenbit.beep.classroom.dto.LookUpStudentsRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import com.tenbit.beep.classroom.repository.ClassRoomRepository;
import com.tenbit.beep.classroom.service.ClassRoomService;
import com.tenbit.beep.common.exception.AuthenticationException;
import com.tenbit.beep.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final UserRepository userRepository;

//    @Override
//    public List<ClassRoom> lookUpStudentsByClassRoom(LookUpStudentsRequest lookUpStudentsRequest) {
//
//        List<ClassRoom> students = classRoomRepository.findByClassRoomName(lookUpStudentsRequest.getClassRoomName());
//
//        if (students.isEmpty()) {
//            lookUpStudentsByPrimaryClassRoom(lookUpStudentsRequest);
//        }
//
//        return students;
//    }

    @Override
    public List<StudentInfoResponse> lookUpStudentsByPrimaryClassRoom(LookUpStudentsRequest lookUpStudentsRequest) {

        List<User> students = classRoomRepository.findByPrimaryClassRoomName(lookUpStudentsRequest.getClassRoomName());

        if (students.isEmpty()) {

            throw new UserNotFoundException("학생이 조회 되지 않았습니다.");
        }

        return students.stream()
                .map(StudentInfoResponse::from)
                .collect(Collectors.toList());
    }
}
