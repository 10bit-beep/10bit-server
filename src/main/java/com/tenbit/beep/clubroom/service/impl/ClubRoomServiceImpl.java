package com.tenbit.beep.clubroom.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.clubroom.domain.ClubRoom;
import com.tenbit.beep.clubroom.dto.LookUpStudentsByClubRequest;
import com.tenbit.beep.clubroom.repository.ClubRoomRepository;
import com.tenbit.beep.clubroom.service.ClubRoomService;
import com.tenbit.beep.common.dto.StudentInfoResponse;
import com.tenbit.beep.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubRoomServiceImpl implements ClubRoomService {

    private final ClubRoomRepository clubRoomRepository;

//    @Override
//    public List<ClubRoom> lookUpStudentsByClubRoom(LookUpStudentsByClubRequest lookUpStudentsRequest) {
//        List<ClubRoom> students = clubRoomRepository.findByClubRoomName(lookUpStudentsRequest.getClubRoomName());
//
//        if (students.isEmpty()) {
//            lookUpStudentsByPrimaryClubRoom(lookUpStudentsRequest);
//        }
//
//        return students;
//    }

    @Override
    public List<StudentInfoResponse> lookUpStudentsByPrimaryClubRoom(LookUpStudentsByClubRequest lookUpStudentsRequest) {
        List<User> students = clubRoomRepository.findByPrimaryClubRoomName(lookUpStudentsRequest.getClubRoomName());

        if (students.isEmpty()) {

            throw new UserNotFoundException("학생이 조회 되지 않았습니다.");
        }

        return students.stream()
                .map(StudentInfoResponse::from)
                .collect(Collectors.toList());
    }
}
