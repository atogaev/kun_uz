package com.example.repository;

import com.example.entity.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String > {
    @Query("from ArticleEntity as a inner join a.articleType as at where a.visible = true and a.status = 'PUBLISHED' and at.id=?1 order by a.published_date limit ?2")
    List<ArticleEntity> getByType(Integer typeId,Integer lim);
}
