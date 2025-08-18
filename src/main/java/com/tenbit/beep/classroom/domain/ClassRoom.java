package com.tenbit.beep.classroom.domain;

import com.tenbit.beep.auth.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClassRoom extends User {

    @Column
    @Min(1)
    @Max(10)
    private String classRoomName;

    public ClassRoom(String classRoomName) {
        super();

        this.classRoomName = classRoomName;
    }
}
