package com.tenbit.beep.auth.domain.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long innerId;

    @Column(nullable = false)
    @Min(0)
    @Max(4000)
    // 선생님은 0000
    private int studentNumber;

    @Column(unique = true, nullable = false, length = 10)
    @Size(min = 2, max = 10)
    private String name;

    @Column(unique = true, nullable = false, length = 15)
    @Size(min = 4, max = 15)
    private String publicId;

    @Column(nullable = false, length = 255)
    @Size(min = 8, max = 255)
    private String password;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Attendance attendance;

    public User(int studentNumber, String name, String publicId, String password, String email) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.publicId = publicId;
        this.password = password;
        this.email = email;
        this.attendance = Attendance.FALSE;
    }
}
