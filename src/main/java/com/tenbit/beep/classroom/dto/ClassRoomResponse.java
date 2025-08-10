package com.tenbit.beep.classroom.dto;

import com.tenbit.beep.classroom.entity.ClassRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassRoomResponse {
    private Integer studentNumber;
    private String name;
    private Integer grade;
    private Integer classNumber;
    private String className;

    public static ClassRoomResponse from(ClassRoom room) {
        return new ClassRoomResponse(
                room.getStudentNumber(),
                room.getName(),
                room.getGrade(),
                room.getClassNumber(),
                room.getClassName()
        );
    }
}