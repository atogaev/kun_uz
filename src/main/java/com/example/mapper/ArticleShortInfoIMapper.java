package com.example.mapper;

import java.time.LocalDate;

public interface ArticleShortInfoIMapper {
     String getId();
     String getTitle();
     String getDescription();
     String getAttach_id();
     LocalDate getPublished_date();
}
