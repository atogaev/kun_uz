package com.example.entity;

import com.example.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Name;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description",
            columnDefinition = "TEXT")
    private String description;
    @Column(name = "content",
            columnDefinition = "TEXT")
    private String content;

    @Column(name = "shared_count")
    private Long shared_count;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.NOTPUBLISHED;//(Published,NotPublished)

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "published_date")
    private LocalDate published_date;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "view_count")
    private Long view_count;

    @Column(name = "prt_id")
    private Long prtId;

    @Column(name = "attach_id")
    private String  attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false,updatable = false)
    private AttachEntity attach;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",insertable = false,updatable = false)
    private RegionEntity region;

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;

    @Column(name = "articleType_id")
    private Integer articleTypeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleType_id",insertable = false,updatable = false)
    private ArticleTypeEntity articleType;

    @Column(name = "moderator_id")
    private Long moderator_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id",insertable = false,updatable = false)
    private ProfileEntity moderator;

    @Column(name = "publisher_id")
    private Long publisherId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",insertable = false,updatable = false)
    private ProfileEntity publisher;

}
