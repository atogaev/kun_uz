package com.example.entity;

import com.example.enums.ProfileRoleEnum;
import com.example.enums.ProfileStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatusEnum status = ProfileStatusEnum.ACTIVE;
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRoleEnum role = ProfileRoleEnum.ROLE_USER;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "created_date")
    private LocalDateTime created_date = LocalDateTime.now();
    @Column(name = "prt_id")
    private Long prtId;
//    @Column(name = "photo_id")
//    private String photo_id;
}
