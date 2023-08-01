package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Setter
@Getter
public class CommentDTO {
    private UUID id = UUID.randomUUID();
    private LocalDate created_date = LocalDate.now();
    private LocalDate update_date = LocalDate.now();
    private UUID profile_id;
    private String content;
    private UUID article_id;
    private UUID reply_id;
    private Boolean visible;
}
