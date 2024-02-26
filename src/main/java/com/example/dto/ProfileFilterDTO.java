package com.example.dto;

import com.example.enums.ProfileRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String phone;
    private ProfileRoleEnum role;
    private LocalDateTime created_date_from;
    private LocalDateTime created_date_to;
}
