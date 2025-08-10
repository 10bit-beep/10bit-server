package com.tenbit.beep.classroom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer studentNumber;  // 학번
    private String name;            // 이름
    private Integer grade;          // 학년
    private Integer classNumber;    // 반
    private String className;       // 예: "1학년 5반"
} 