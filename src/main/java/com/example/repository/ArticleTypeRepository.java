package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity,Integer>,
        PagingAndSortingRepository<ArticleTypeEntity,Integer> {
    @Query("from ArticleTypeEntity where order_number = ?1")
    Optional<ArticleTypeEntity> findByOrder_number(String orderNumber);

//    @Transactional
//    @Modifying
//    @Query("update ArticleTypeEntity set order_number =: order_number,name_uz =:name_uz,name_ru =:name_ru,name_en =:name_en where id =: id")
//    Optional<ArticleTypeEntity> findById(Long id, String order_number, String name_uz, String name_ru, String name_en);
}
