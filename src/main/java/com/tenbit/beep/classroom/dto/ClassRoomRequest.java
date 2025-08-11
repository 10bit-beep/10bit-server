package com.tenbit.beep.classroom.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomRequest {

    @NotNull(message = "학번은 필수입니다")
    @Min(value = 1, message = "학번은 1 이상이어야 합니다")
    private Integer studentNumber;

    @NotBlank(message = "이름은 필수입니다")
    @Size(min = 1, max = 50, message = "이름은 1자 이상 50자 이하여야 합니다")
    private String name;

    @NotNull(message = "학년은 필수입니다")
    @Min(value = 1, message = "학년은 1 이상이어야 합니다")
    @Max(value = 6, message = "학년은 6 이하여야 합니다")
    private Integer grade;

    @NotNull(message = "반은 필수입니다")
    @Min(value = 1, message = "반은 1 이상이어야 합니다")
    @Max(value = 20, message = "반은 20 이하여야 합니다")
    private Integer classNumber;

    @NotBlank(message = "클래스명은 필수입니다")
    @Size(min = 1, max = 100, message = "클래스명은 1자 이상 100자 이하여야 합니다")
    private String className;
}
