package com.example.repository;

import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Long>,
        PagingAndSortingRepository<ProfileEntity,Long> {

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByPhone(String phone);

    @Transactional
    @Modifying
    @Query("update ProfileEntity as p set p.name = ?2,p.surname = ?3 where p.id = ?1")
    int  detailUpdate(Long id,String name, String surname);

    Optional<ProfileEntity> findByPhoneAndPassword(String phone, String encode);
}
