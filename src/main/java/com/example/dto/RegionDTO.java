package com.example.dto;

import java.time.LocalDate;
import java.util.UUID;

public class RegionDTO {

    private UUID id = UUID.randomUUID();
    private Integer order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible = Boolean.TRUE;
    private LocalDate created_date = LocalDate.now();
}
