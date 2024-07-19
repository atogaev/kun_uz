package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "attach")
@Getter
@Setter
public class AttachEntity {
    @Id
    private String id;// (uuid)
    @Column
    private String original_name;
    @Column
    private String path;
    @Column
    private Long size;
    @Column
    private String extension;
    @CreationTimestamp
    @Column
    private LocalDateTime created_date;
}
