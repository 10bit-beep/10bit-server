package com.tenbit.beep.classroom.controller;

import com.tenbit.beep.classroom.dto.ClassRoomRequest;
import com.tenbit.beep.classroom.dto.ClassRoomResponse;
import com.tenbit.beep.classroom.entity.ClassRoom;
import com.tenbit.beep.classroom.repository.ClassRoomRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomRepository repository;

    @GetMapping
    public ResponseEntity<List<ClassRoomResponse>> getAll() {
        List<ClassRoomResponse> classRooms = repository.findAll().stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classRooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> getById(@PathVariable Long id) {
        ClassRoom classRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassRoom not found"));
        return ResponseEntity.ok(ClassRoomResponse.from(classRoom));
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<ClassRoomResponse>> getByGrade(@PathVariable int grade) {
        List<ClassRoomResponse> classRooms = repository.findByGrade(grade).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classRooms);
    }

    @GetMapping("/grade/{grade}/class/{classNumber}")
    public ResponseEntity<List<ClassRoomResponse>> getByGradeAndClass(@PathVariable int grade,
            @PathVariable int classNumber) {
        List<ClassRoomResponse> classRooms = repository.findByGradeAndClassNumber(grade, classNumber).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classRooms);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClassRoomResponse>> searchByName(@RequestParam String name) {
        List<ClassRoomResponse> classRooms = repository.findByNameContaining(name).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(classRooms);
    }

    @PostMapping
    public ResponseEntity<ClassRoomResponse> create(@Valid @RequestBody ClassRoomRequest request) {
        ClassRoom classRoom = ClassRoom.builder()
                .studentNumber(request.getStudentNumber())
                .name(request.getName())
                .grade(request.getGrade())
                .classNumber(request.getClassNumber())
                .className(request.getClassName())
                .build();

        ClassRoom savedClassRoom = repository.save(classRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClassRoomResponse.from(savedClassRoom));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomResponse> update(@PathVariable Long id,
            @Valid @RequestBody ClassRoomRequest request) {
        ClassRoom classRoom = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ClassRoom not found"));

        classRoom.setStudentNumber(request.getStudentNumber());
        classRoom.setName(request.getName());
        classRoom.setGrade(request.getGrade());
        classRoom.setClassNumber(request.getClassNumber());
        classRoom.setClassName(request.getClassName());

        ClassRoom updatedClassRoom = repository.save(classRoom);
        return ResponseEntity.ok(ClassRoomResponse.from(updatedClassRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("ClassRoom not found");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}