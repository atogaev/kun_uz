package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.dto.ArticleShortInfo;
import com.example.entity.ArticleEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.RegionEntity;
import com.example.enums.StatusEnum;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private AttachService attachService;


    public ArticleDTO create(ArticleDTO dto, Long id) {
        CategoryEntity category = categoryService.get(dto.getCategoryId());
        if (category == null){
            throw new ItemNotFoundException("Category not found");
        }

        RegionEntity region = regionService.get(dto.getRegionId());
        if (region == null){
            throw new ItemNotFoundException("Region not found");
        }

        ProfileEntity moderator = profileService.get(dto.getModeratorId());
        if (moderator == null){
            throw new ItemNotFoundException("Moderator not found");
        }
        ProfileEntity publisher = profileService.get(dto.getPublisher());
        if (publisher == null){
            throw new ItemNotFoundException("Moderator not found");
        }
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setAttachId(dto.getImageId());
        entity.setRegionId(region.getId());
        entity.setCategoryId(category.getId());

        entity.setModerator_id(moderator.getId());
        entity.setPublisherId(publisher.getId());
        entity.setPrtId(id);
        articleRepository.save(entity);
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setCreated_date(entity.getCreated_date());
        dto.setVisible(entity.getVisible());
        return toDTO(entity);
    }
    public Boolean deleteById(String id){
        Boolean t = false;
      var entity = get(id);
      if (entity.getVisible().equals(Boolean.TRUE)){
          articleRepository.deleteById(id);
          t = true;
      }
        return t;
    }
    public ArticleDTO changeStatus(String id, StatusEnum statusEnum){
        var entity = get(id);
        entity.setStatus(statusEnum);
        articleRepository.save(entity);
        return toDTO(entity);
    }
    public List<ArticleShortInfo> getLastFive(Integer id,Integer limit){
        List<ArticleShortInfo> shortInfos = new ArrayList<>();
        List<ArticleEntity> entities = articleRepository.getByType(id,limit);
        entities.forEach(entity -> {
            var shortInfo= ArticleShortInfo.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .imageId(attachService.getUrl(entity.getAttachId()))
                    .publishedDate(entity.getPublished_date())
                    .build();
        }) ;
return null;
    }
    public ArticleEntity get(String id){
        return articleRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Article not found"));
    }

    private ArticleDTO toDTO(ArticleEntity entity){
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setImageId(entity.getAttachId());
        dto.setStatus(entity.getStatus());
        dto.setCreated_date(entity.getCreated_date());
        dto.setVisible(entity.getVisible());
        return dto;
    }
}
