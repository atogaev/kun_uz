package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class ProfileDTO {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRole role;
    private LocalDateTime created_date;
//    private String photo_id;

}
