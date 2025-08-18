package com.tenbit.beep.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String publicId;
    private String password;
}
