package com.example.dto;

import com.example.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Long shared_count;
    private String  imageId;
    private Integer regionId;
    private Integer categoryId;
    private List<Integer> articleType;
    private Long moderatorId;
    private Long publisher;
    private StatusEnum status; //(Published,NotPublished)
    private LocalDate created_date;
    private LocalDate updated_date;
    private LocalDate published_date;
    private Boolean visible;
    private Long view_count;
}
