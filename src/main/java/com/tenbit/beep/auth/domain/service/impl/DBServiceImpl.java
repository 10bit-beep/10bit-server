package com.tenbit.beep.auth.domain.service.impl;

import com.tenbit.beep.auth.domain.repository.UserRepository;
import com.tenbit.beep.auth.domain.service.DBService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBServiceImpl implements DBService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void resetDB() {
        userRepository.deleteAll();
    }

//    @Override
//    @Transactional
//    public void deleteByName(String name) {
//        userRepository.deleteByName(name);
//    }

    @Override
    @Transactional
    public void deleteByPublicId(String publicId) {
        userRepository.deleteByPublicId(publicId);
    }

    @Override
    @Transactional
    public void deleteByInnerId(Long innerId) {
        userRepository.deleteByInnerId(innerId);
    }
}
