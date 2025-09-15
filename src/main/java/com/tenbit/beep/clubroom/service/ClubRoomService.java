package com.tenbit.beep.clubroom.service;

import com.tenbit.beep.clubroom.dto.ClubRoomRequest;
import com.tenbit.beep.clubroom.dto.ClubRoomResponse;

import java.util.List;

public interface ClubRoomService {

    List<ClubRoomResponse> lookUpStudentsByClub(ClubRoomRequest clubRoomRequest);
}
