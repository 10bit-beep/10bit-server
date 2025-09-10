package com.tenbit.beep.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public User(int studentNumber, String publicId, String password, String email) {
        this.studentNumber = studentNumber;
        this.publicId = publicId;
        this.password = password;
        this.email = email;
    }
}
