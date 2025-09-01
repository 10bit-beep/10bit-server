package com.tenbit.beep.clubroom.controller;

import com.tenbit.beep.clubroom.dto.LookUpStudentsByClubRequest;
import com.tenbit.beep.clubroom.service.ClubRoomService;
import com.tenbit.beep.common.dto.StudentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubroom")
public class ClubRoomController {

    private final ClubRoomService clubRoomService;

    @PostMapping("/lookup")
    public ResponseEntity<List<StudentInfoResponse>> lookup(
            @RequestBody LookUpStudentsByClubRequest lookUpStudentsRequest) {

        List<StudentInfoResponse> students = clubRoomService.lookUpStudentsByPrimaryClubRoom(lookUpStudentsRequest);

        return ResponseEntity.ok(students);
    }
}
