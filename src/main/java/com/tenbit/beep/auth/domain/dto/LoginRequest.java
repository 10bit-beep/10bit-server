package com.tenbit.beep.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String publicId;
    private String password;
}
