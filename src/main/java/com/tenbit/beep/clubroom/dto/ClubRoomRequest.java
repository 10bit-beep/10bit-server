package com.tenbit.beep.clubroom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubRoomRequest {

    private String clubName;
    private String description;
    private Integer maxMembers;
    private Integer currentMembers;
    private String leaderName;
    private String leaderStudentNumber;
    private String category;
}
