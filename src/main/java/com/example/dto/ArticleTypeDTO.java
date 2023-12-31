package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class ArticleTypeDTO {
    private UUID id = UUID.randomUUID();
    private String order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private String visible;
    private LocalDateTime created_date = LocalDateTime.now();
}
