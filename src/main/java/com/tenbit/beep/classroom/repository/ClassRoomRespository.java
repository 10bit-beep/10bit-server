package com.tenbit.beep.classroom.repository;

import com.tenbit.beep.classroom.domain.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRespository extends JpaRepository<ClassRoom, Long> {

    List<ClassRoom> findByClassRoomNameAndAuthority(String classRoomName, String authority);
}
