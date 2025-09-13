package com.tenbit.beep.attendance.repository;

import com.tenbit.beep.attendance.domain.Attend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendRepository extends JpaRepository<Attend, Long> {


}
