package com.tenbit.beep.classroom.repository;

import com.tenbit.beep.classroom.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

    List<ClassRoom> findByGrade(int grade);

    List<ClassRoom> findByGradeAndClassNumber(int grade, int classNumber);

    List<ClassRoom> findByNameContaining(String name);
}