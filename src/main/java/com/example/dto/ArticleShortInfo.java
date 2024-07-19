package com.example.dto;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ArticleShortInfo(
        String id,
        String title,
        String description,
        String imageId,
        LocalDate publishedDate
) {
}
