package com.tenbit.beep.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private int studentNumber;
    private String publicId;
    private String password;
    private String email;
    private String club;
}
