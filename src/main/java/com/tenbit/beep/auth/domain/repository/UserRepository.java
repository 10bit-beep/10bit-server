package com.tenbit.beep.auth.domain.repository;

import com.tenbit.beep.auth.domain.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPublicId(String publicId);
    @Transactional
    void deleteByInnerId(Long innerId);
    @Transactional
    void deleteByPublicId(String publicId);
}
