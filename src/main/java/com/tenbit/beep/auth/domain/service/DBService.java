package com.tenbit.beep.auth.domain.service;

public interface DBService {

    void resetDB();
    void deleteByName(String name);
    void deleteByPublicId(String publicId);
    void deleteByInnerId(Long innerId);
}
