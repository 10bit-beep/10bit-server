package com.tenbit.beep.clubroom.dto;

import com.tenbit.beep.clubroom.entity.ClubRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ClubRoomResponse {
    private Long id;
    private String clubName;
    private String description;
    private Integer maxMembers;
    private Integer currentMembers;
    private String leaderName;
    private String leaderStudentNumber;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ClubRoomResponse from(ClubRoom clubRoom) {
        return new ClubRoomResponse(
                clubRoom.getId(),
                clubRoom.getClubName(),
                clubRoom.getDescription(),
                clubRoom.getMaxMembers(),
                clubRoom.getCurrentMembers(),
                clubRoom.getLeaderName(),
                clubRoom.getLeaderStudentNumber(),
                clubRoom.getCategory(),
                clubRoom.getCreatedAt(),
                clubRoom.getUpdatedAt());
    }
}
