package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.StringReader;
import java.util.UUID;
@Getter
@Setter
public class ArticleDTO {
    private UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private StringReader content;
    private Long shared_count;
    private String image_id;
}
