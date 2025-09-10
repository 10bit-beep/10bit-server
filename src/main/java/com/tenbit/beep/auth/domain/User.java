package com.tenbit.beep.auth.domain;

import com.tenbit.beep.attendance.domain.Attendance;
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
@Table(name = "users")
public class User {

    @Id
    @Column(name = "inner_id")
    private Long innerId;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "pw")
    private String password;

    @Column(name = "stu_num")
    private Integer studentNumber;

    @Column(name = "authority")
    private Authority authority;

    @Column(name = "email")
    private String email;

    @Column(name = "club")
    private String club;

    public User(int studentNumber, String publicId, String password, String email) {

    }
}