package com.tenbit.beep.classroom.repository;

import com.tenbit.beep.auth.domain.User;
import com.tenbit.beep.classroom.domain.ClassRoom;
import com.tenbit.beep.classroom.dto.StudentInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<User, Long> {

//    List<ClassRoom> findByClassRoomName(String classRoomName);
    List<User> findByPrimaryClassRoomName(String primaryClassRoomName);
}
