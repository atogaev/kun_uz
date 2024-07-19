package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {
    private String  id;// (uuid)
    private String original_name;
    private String path;
    private Long size;
    private String extension;
    private LocalDateTime created_date;
    private String url;
}
