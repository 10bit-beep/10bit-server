package com.tenbit.beep.attendance.repository;

import com.tenbit.beep.attendance.domain.Attend;
import com.tenbit.beep.auth.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {
    Optional<User> findByPublicId(String publicId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.attendance = :attendance")
    void updateAttendance(@Param("attendance") Attend attendance);
}
