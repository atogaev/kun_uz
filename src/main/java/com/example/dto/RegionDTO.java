package com.example.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class RegionDTO {

    private UUID id = UUID.randomUUID();
    private Integer order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible = Boolean.TRUE;
    private LocalDateTime created_date = LocalDateTime.now();
}
