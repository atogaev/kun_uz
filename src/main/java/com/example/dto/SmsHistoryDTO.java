package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class SmsHistoryDTO {
    private UUID id = UUID.randomUUID();
    private String phone;
    private String message;
    private String status;
    private String type;//(if necessary)
    private LocalDateTime created_date = LocalDateTime.now();//(used_date if necessary)
}
