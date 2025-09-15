package com.tenbit.beep.clubroom.service.impl;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.clubroom.dto.ClubRoomRequest;
import com.tenbit.beep.clubroom.dto.ClubRoomResponse;
import com.tenbit.beep.clubroom.repository.ClubRoomRepository;
import com.tenbit.beep.clubroom.service.ClubRoomService;
import com.tenbit.beep.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubRoomServiceImpl implements ClubRoomService {

    private final ClubRoomRepository clubRoomRepository;

    @Override
    public List<ClubRoomResponse> lookUpStudentsByClub(ClubRoomRequest clubRoomRequest) {
        List<User> students = clubRoomRepository.findByClub(clubRoomRequest.getClub());

        if (students.isEmpty()) {
            throw new UserNotFoundException("학생이 조회 되지 않았습니다.");
        }

        return students.stream()
                .map(ClubRoomResponse::from)
                .collect(Collectors.toList());
    }
}
