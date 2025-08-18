package com.tenbit.beep.classroom.controller;

import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.LookUpStudentsRequest;
import com.tenbit.beep.classroom.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PostMapping("/lookup")
    public ResponseEntity<Object> lookup(@RequestBody LookUpStudentsRequest lookUpStudentsRequest) {

        try {
            classRoomService.lookUpStudentsByPrimaryClassRoom(lookUpStudentsRequest);
        } catch (Exception e) {}

        return ResponseEntity.ok(Map.of(
                "Look up class", lookUpStudentsRequest.getClassRoomName(),
                "result", "success"
        ));
    }
}
