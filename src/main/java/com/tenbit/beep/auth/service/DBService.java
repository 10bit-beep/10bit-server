package com.tenbit.beep.auth.service;

public interface DBService {

    void resetDB();
    void deleteByPublicId(String publicId);
    void deleteByInnerId(Long innerId);
}
