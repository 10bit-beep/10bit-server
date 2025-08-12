package com.tenbit.beep.classroom.dto;

import com.tenbit.beep.classroom.entity.ClassRoom;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClassRoomResponse {
    private Long id;
    private Integer studentNumber;
    private Integer grade;
    private Integer classNumber;
    private String className;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ClassRoomResponse from(ClassRoom room) {
        ClassRoomResponse response = new ClassRoomResponse();
        response.setId(room.getId());
        response.setStudentNumber(room.getStudentNumber());
        response.setGrade(room.getGrade());
        response.setClassNumber(room.getClassNumber());
        response.setClassName(room.getClassName());
        response.setCreatedAt(room.getCreatedAt());
        response.setUpdatedAt(room.getUpdatedAt());
        return response;
    }
}