package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update RegionEntity as r set r.orderNumber = ?2,r.nameUz = ?3,r.nameRu = ?4,r.nameEn =?5 where r.id = ?1")
    int updateById(Integer id, Integer orderNumber, String nameUz, String nameRu, String nameEn);
    @Query("select r from RegionEntity as r where r.id =:id")
    Optional<RegionEntity> getById(@Param("id") UUID id);
    @Transactional
    @Modifying
    @Query("delete RegionEntity as r where r.id = ?1")
    int delete(UUID id);
    // TODO
    @Query("select r.nameUz from RegionEntity as r")
    Optional<RegionEntity> getByNameUz();

    List<RegionEntity> findAllByVisibleTrue();
}
