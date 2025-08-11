package com.tenbit.beep.clubroom.repository;

import com.tenbit.beep.clubroom.entity.ClubRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRoomRepository extends JpaRepository<ClubRoom, Long> {

    List<ClubRoom> findByCategory(String category);

    List<ClubRoom> findByClubNameContaining(String clubName);
}
