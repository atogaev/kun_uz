package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ArticleRepository extends CrudRepository<ArticleEntity, UUID> {
}
