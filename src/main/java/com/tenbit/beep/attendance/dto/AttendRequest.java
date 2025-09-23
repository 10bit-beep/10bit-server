package com.tenbit.beep.attendance.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AttendRequest {
    @NonNull
    private String publicId;
    private String nfcTag;
}
