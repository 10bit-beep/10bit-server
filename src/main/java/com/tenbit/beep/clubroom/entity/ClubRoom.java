package com.tenbit.beep.clubroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "club_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String clubName; // 동아리명

    @Column(nullable = false, length = 500)
    private String description; // 동아리 설명

    @Column(nullable = false)
    private Integer maxMembers; // 최대 인원

    @Column(nullable = false)
    private Integer currentMembers; // 현재 인원

    @Column(nullable = false, length = 50)
    private String leaderName; // 동아리장 이름

    @Column(nullable = false, unique = true, length = 20)
    private String leaderStudentNumber; // 동아리장 학번

    @Column(nullable = false, length = 50)
    private String category; // 동아리 카테고리 (예: 학술, 문화, 스포츠 등)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
