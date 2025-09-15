package com.tenbit.beep.attendance.domain;

import com.tenbit.beep.auth.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attendances")
@AllArgsConstructor
@Builder
public class Attend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id", nullable = false)
    private Long attendanceId;

    @Column(name = "att_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime attendanceDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inner_id")
    private User user;
}
