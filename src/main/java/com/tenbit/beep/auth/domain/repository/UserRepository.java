package com.tenbit.beep.auth.domain.repository;

import com.tenbit.beep.auth.domain.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPublicId(String publicId);
}
