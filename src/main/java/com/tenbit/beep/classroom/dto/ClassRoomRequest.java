package com.tenbit.beep.classroom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRoomRequest {

    private Integer studentNumber;
    private String className;

    // 학번을 기반으로 학년, 반, 번호를 계산하는 메서드
    public Integer getGrade() {
        if (studentNumber == null) return null;
        return studentNumber / 1000; // 학번의 첫 번째 자리
    }

    public Integer getClassNumber() {
        if (studentNumber == null) return null;
        return (studentNumber % 1000) / 10; // 학번의 두 번째, 세 번째 자리
    }

    public Integer getStudentNumberInClass() {
        if (studentNumber == null) return null;
        return studentNumber % 10; // 학번의 마지막 자리
    }
}
