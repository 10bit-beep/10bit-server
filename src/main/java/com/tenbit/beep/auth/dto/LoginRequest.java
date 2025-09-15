package com.tenbit.beep.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    // null값 방지
    @NotBlank
    private String publicId;
    @NotBlank
    private String password;
}
