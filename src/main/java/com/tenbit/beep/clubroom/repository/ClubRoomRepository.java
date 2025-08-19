package com.tenbit.beep.clubroom.repository;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.clubroom.domain.ClubRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRoomRepository extends JpaRepository<ClubRoom, Long> {

    List<ClubRoom> findByClubRoomName(String clubRoomName);
    List<User> findByPrimaryClubRoomName(String primaryClubRoomName);
}
