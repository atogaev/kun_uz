package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.StringReader;
import java.util.UUID;
@Getter
@Setter
//@Entity
//@Table(name = "Article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    private StringReader content;
    private Long shared_count;
    private String image_id;
}
