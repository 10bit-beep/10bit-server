package com.tenbit.beep.classroom.dto;

import com.tenbit.beep.classroom.entity.ClassRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ClassRoomResponse {
    private Long id;
    private Integer studentNumber;
    private String name;
    private Integer grade;
    private Integer classNumber;
    private String className;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ClassRoomResponse from(ClassRoom room) {
        return new ClassRoomResponse(
                room.getId(),
                room.getStudentNumber(),
                room.getName(),
                room.getGrade(),
                room.getClassNumber(),
                room.getClassName(),
                room.getCreatedAt(),
                room.getUpdatedAt());
    }
}