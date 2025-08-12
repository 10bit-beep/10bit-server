package com.tenbit.beep.clubroom.dto;

import com.tenbit.beep.clubroom.entity.ClubRoom;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
        ClubRoomResponse response = new ClubRoomResponse();
        response.setId(clubRoom.getId());
        response.setClubName(clubRoom.getClubName());
        response.setDescription(clubRoom.getDescription());
        response.setMaxMembers(clubRoom.getMaxMembers());
        response.setCurrentMembers(clubRoom.getCurrentMembers());
        response.setLeaderName(clubRoom.getLeaderName());
        response.setLeaderStudentNumber(clubRoom.getLeaderStudentNumber());
        response.setCategory(clubRoom.getCategory());
        response.setCreatedAt(clubRoom.getCreatedAt());
        response.setUpdatedAt(clubRoom.getUpdatedAt());
        return response;
    }
}
