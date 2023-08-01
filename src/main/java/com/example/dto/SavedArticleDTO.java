package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class SavedArticleDTO {
    private UUID id = UUID.randomUUID();
    private UUID profile_id;
    private UUID article_id;
    private LocalDateTime created_date = LocalDateTime.now();
}
