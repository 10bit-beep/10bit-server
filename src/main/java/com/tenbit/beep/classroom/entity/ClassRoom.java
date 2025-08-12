package com.tenbit.beep.classroom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "class_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "학번은 필수입니다")
    @Min(value = 1, message = "학번은 1 이상이어야 합니다")
    private Integer studentNumber; // 학번

    @NotBlank(message = "이름은 필수입니다")
    @Size(min = 1, max = 50, message = "이름은 1자 이상 50자 이하여야 합니다")
    private String name; // 이름

    @NotNull(message = "학년은 필수입니다")
    @Min(value = 1, message = "학년은 1 이상이어야 합니다")
    @Max(value = 6, message = "학년은 6 이하여야 합니다")
    private Integer grade; // 학년

    @NotNull(message = "반은 필수입니다")
    @Min(value = 1, message = "반은 1 이상이어야 합니다")
    @Max(value = 20, message = "반은 20 이하여야 합니다")
    private Integer classNumber; // 반

    @NotBlank(message = "클래스명은 필수입니다")
    @Size(min = 1, max = 100, message = "클래스명은 1자 이상 100자 이하여야 합니다")
    private String className; // 예: "1학년 5반"

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