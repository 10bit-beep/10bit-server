package com.tenbit.beep.clubroom.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRoomRequest {

    @NotBlank(message = "동아리명은 필수입니다")
    @Size(min = 1, max = 100, message = "동아리명은 1자 이상 100자 이하여야 합니다")
    private String clubName;

    @NotBlank(message = "동아리 설명은 필수입니다")
    @Size(min = 1, max = 500, message = "동아리 설명은 1자 이상 500자 이하여야 합니다")
    private String description;

    @NotNull(message = "최대 인원은 필수입니다")
    @Min(value = 1, message = "최대 인원은 1명 이상이어야 합니다")
    @Max(value = 100, message = "최대 인원은 100명 이하여야 합니다")
    private Integer maxMembers;

    @NotNull(message = "현재 인원은 필수입니다")
    @Min(value = 0, message = "현재 인원은 0명 이상이어야 합니다")
    private Integer currentMembers;

    @NotBlank(message = "동아리장 이름은 필수입니다")
    @Size(min = 1, max = 50, message = "동아리장 이름은 1자 이상 50자 이하여야 합니다")
    private String leaderName;

    @NotBlank(message = "동아리장 학번은 필수입니다")
    @Size(min = 1, max = 20, message = "동아리장 학번은 1자 이상 20자 이하여야 합니다")
    private String leaderStudentNumber;

    @NotBlank(message = "동아리 카테고리는 필수입니다")
    @Size(min = 1, max = 50, message = "동아리 카테고리는 1자 이상 50자 이하여야 합니다")
    private String category;
}
