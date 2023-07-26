package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Setter
@Getter
public class ProfileDTO {
    private final UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String status;
    private String role;
    private Boolean visible;
    private LocalDate created_date;
    private String photo_id;

}
