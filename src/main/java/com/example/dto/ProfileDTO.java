package com.example.dto;

import com.example.enums.ProfileRoleEnum;
import com.example.enums.ProfileStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRoleEnum role;
    private LocalDateTime created_date;
    private ProfileStatusEnum status;
    private Long prtId;
    private String jwt;
//    private String photo_id;

}
