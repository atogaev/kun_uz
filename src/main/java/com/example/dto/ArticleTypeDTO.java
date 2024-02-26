package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    private Integer id;
    private String order_number;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
    private Long prtId;
    private LocalDateTime created_date;
}
