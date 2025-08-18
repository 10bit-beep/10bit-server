package com.tenbit.beep.classroom.repository;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRespository extends JpaRepository<ClassRoom, Long> {

    List<ClassRoom> findByClassRoomName(String classRoomName);
    List<StudentInfoResponse> findByPrimaryClassRoomName(String primaryClassRoomName);
}
