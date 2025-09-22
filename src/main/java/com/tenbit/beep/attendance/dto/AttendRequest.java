package com.tenbit.beep.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendRequest {

    private String publicId;
    private String nfcTag;
}
