package com.tenbit.beep.auth.repository;

import com.tenbit.beep.auth.domain.Attendance;
import com.tenbit.beep.auth.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 유저 조회 함수
    Optional<User> findByPublicId(String publicId);

    // 유저 존재 확인 함수
    boolean existsByPublicId(String publicId);
    boolean existsByEmail(String email);

    // 디비 관련 함수
    @Transactional
    void deleteByInnerId(Long innerId);
    @Transactional
    void deleteByPublicId(String publicId);

    // 출석 상태 초기화
    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.attendance = :status")
    void resetAllAttendance(@Param("status") Attendance attendance);
}
