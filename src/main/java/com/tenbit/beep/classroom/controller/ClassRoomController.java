package com.tenbit.beep.classroom.controller;

import com.tenbit.beep.classroom.dto.ClassRoomRequest;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import com.tenbit.beep.classroom.service.ClassRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    @PostMapping("/lookup")
    public ResponseEntity<List<StudentInfoResponse>> lookup(
            @RequestBody ClassRoomRequest classRoomRequest) {

        List<StudentInfoResponse> students = classRoomService.lookUpStudentsByUserClass(classRoomRequest);

        return ResponseEntity.ok(students);
    }
}
