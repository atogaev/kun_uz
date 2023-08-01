package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, UUID> {

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByPhone(String phone);
    @Transactional
    @Modifying
    @Query("update ProfileEntity set name = ?2,surname = ?3 where id = ?1")
    int  detailUpdate(UUID profileId, String name, String surname);

}
