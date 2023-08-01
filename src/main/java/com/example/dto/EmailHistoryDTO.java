package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class EmailHistoryDTO {
    private UUID id = UUID.randomUUID();
    private String message;
    private String email;
    private LocalDateTime created_data = LocalDateTime.now();
}
