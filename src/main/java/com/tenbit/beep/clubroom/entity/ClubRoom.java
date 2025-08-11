package com.tenbit.beep.clubroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "동아리명은 필수입니다")
    @Size(min = 1, max = 100, message = "동아리명은 1자 이상 100자 이하여야 합니다")
    private String clubName; // 동아리명

    @NotBlank(message = "동아리 설명은 필수입니다")
    @Size(min = 1, max = 500, message = "동아리 설명은 1자 이상 500자 이하여야 합니다")
    private String description; // 동아리 설명

    @NotNull(message = "최대 인원은 필수입니다")
    @Min(value = 1, message = "최대 인원은 1명 이상이어야 합니다")
    @Max(value = 100, message = "최대 인원은 100명 이하여야 합니다")
    private Integer maxMembers; // 최대 인원

    @NotNull(message = "현재 인원은 필수입니다")
    @Min(value = 0, message = "현재 인원은 0명 이상이어야 합니다")
    private Integer currentMembers; // 현재 인원

    @NotBlank(message = "동아리장 이름은 필수입니다")
    @Size(min = 1, max = 50, message = "동아리장 이름은 1자 이상 50자 이하여야 합니다")
    private String leaderName; // 동아리장 이름

    @NotBlank(message = "동아리장 학번은 필수입니다")
    @Size(min = 1, max = 20, message = "동아리장 학번은 1자 이상 20자 이하여야 합니다")
    private String leaderStudentNumber; // 동아리장 학번

    @NotBlank(message = "동아리 카테고리는 필수입니다")
    @Size(min = 1, max = 50, message = "동아리 카테고리는 1자 이상 50자 이하여야 합니다")
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
