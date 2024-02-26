package com.example.repository;

import com.example.entity.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachRepository extends JpaRepository<AttachEntity, UUID> {
}
