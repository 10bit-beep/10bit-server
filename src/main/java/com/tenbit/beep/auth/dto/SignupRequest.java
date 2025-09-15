package com.tenbit.beep.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    // null값 방지
    @NotBlank
    private int studentNumber;
    @NotBlank
    private String publicId;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String club;
}
