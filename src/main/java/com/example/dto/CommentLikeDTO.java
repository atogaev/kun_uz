package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class CommentLikeDTO {
    private UUID id = UUID.randomUUID();
    private UUID profile_id;
    private UUID comment_id;
    private LocalDateTime created_date = LocalDateTime.now();
    private String status;
}
