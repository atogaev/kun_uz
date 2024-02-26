package com.example.repository;

import com.example.entity.CategoryEntity;
import com.example.enums.LanguageEnum;
import com.example.mapper.LanguageI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    @Query(value = "select id, order_number , case :lang when 'Uz' then name_uz when 'Ru' then name_ru when 'En' then name_en end as name from category", nativeQuery = true)
    List<LanguageI> getCategoryByLanguage(@Param("lang")String lang);
}
