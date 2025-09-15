package com.tenbit.beep.clubroom.controller;

import com.tenbit.beep.clubroom.dto.ClubRoomRequest;
import com.tenbit.beep.clubroom.dto.ClubRoomResponse;
import com.tenbit.beep.clubroom.service.ClubRoomService;
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
    public ResponseEntity<List<ClubRoomResponse>> lookup(
            @RequestBody ClubRoomRequest clubRoomRequest) {

        List<ClubRoomResponse> students = clubRoomService.lookUpStudentsByClub(clubRoomRequest);

        return ResponseEntity.ok(students);
    }
}
