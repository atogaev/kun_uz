package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class CategoryDTO {

    private Integer id;
    private Integer order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible = Boolean.TRUE;
    private LocalDate created_date = LocalDate.now();
}
