package com.tenbit.beep.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 테이블 명세서 설명 참고
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inner_id", nullable = false)
    private Long innerId;

    @Column(name = "public_id", nullable = false, unique = true)
    private String publicId;

    @Column(name = "pw", nullable = false)
    private String password;

    @Column(name = "stu_num", nullable = false)
    private Integer studentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "club", nullable = false)
    private String club;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Attendance attendance;

    // 계정 생성시 이용
    public User(int studentNumber, String publicId, String password, String email, String club) {
        this.studentNumber = studentNumber;
        this.publicId = publicId;
        this.password = password;
        this.email = email;
        this.club = club;

        // 기본값 설정, NULL 방지
        this.authority = Authority.STUDENT;
        this.attendance = Attendance.ATTEND;
    }
}