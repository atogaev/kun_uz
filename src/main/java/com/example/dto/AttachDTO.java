package com.example.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class AttachDTO {
    private UUID id = UUID.randomUUID();// (uuid)
    private String original_name;
    private String path;
    private Long size;
    private String extension;
    private LocalDateTime created_date = LocalDateTime.now();
}
