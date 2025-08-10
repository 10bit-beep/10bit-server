package com.tenbit.beep.classroom.controller;

import com.tenbit.beep.classroom.dto.ClassRoomResponse;
import com.tenbit.beep.classroom.repository.ClassRoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    private final ClassRoomRepository repository;

    public ClassRoomController(ClassRoomRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ClassRoomResponse> getAll() {
        return repository.findAll().stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/grade/{grade}")
    public List<ClassRoomResponse> getByGrade(@PathVariable int grade) {
        return repository.findByGrade(grade).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/grade/{grade}/class/{classNumber}")
    public List<ClassRoomResponse> getByGradeAndClass(@PathVariable int grade, @PathVariable int classNumber) {
        return repository.findByGradeAndClassNumber(grade, classNumber).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ClassRoomResponse> searchByName(@RequestParam String name) {
        return repository.findByNameContaining(name).stream()
                .map(ClassRoomResponse::from)
                .collect(Collectors.toList());
    }
}