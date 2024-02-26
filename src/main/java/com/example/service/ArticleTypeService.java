package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.enums.LanguageEnum;
import com.example.exp.AppBadRequestException;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto, Long id) {
        Optional<ArticleTypeEntity> order_num = articleTypeRepository.findByOrder_number(dto.getOrder_number());
        if (order_num.isPresent()) {
            throw new AppBadRequestException("Already this type created");
        }

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setPrtId(id);
        articleTypeRepository.save(entity);
        return toDTO(entity);
    }
    public ArticleTypeDTO update(ArticleTypeDTO dto,Integer id) {
        ArticleTypeEntity entity = get(id);
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        articleTypeRepository.save(entity);
        return toDTO(entity);
    }

    public Boolean delete(Integer id) {
         ArticleTypeEntity entity = get(id);
        entity.setVisible(false);
        articleTypeRepository.save(entity);
        return true;
    }
    public ArticleTypeEntity get(Integer id) {
        return articleTypeRepository.findById(id).orElseThrow(() -> new AppBadRequestException("Id is not founded"));
    }
    private ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setOrder_number(entity.getOrder_number());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());
        dto.setPrtId(entity.getPrtId());
        return dto;
    }

    public PageImpl<ArticleTypeDTO> pageable(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<ArticleTypeEntity> pageObj = articleTypeRepository.findAll(pageable);
        List<ArticleTypeDTO> articleTypeDTOList = pageObj
                .stream()
                .map(this::toDTO)
                .toList();
        return new PageImpl<>(articleTypeDTOList,pageable,pageObj.getTotalElements());
    }

    public List<ArticleTypeDTO> getByLang(LanguageEnum languageEnum) {
    List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity:articleTypeRepository.findAll()) {
            ArticleTypeDTO dto = new ArticleTypeDTO();
            dto.setId(entity.getId());
            dto.setOrder_number(entity.getOrder_number());
            switch (languageEnum){
                case Uz -> dto.setName_uz(entity.getName_uz());
                case Ru -> dto.setName_ru(entity.getName_ru());
                case En -> dto.setName_en(entity.getName_en());
            }
            dtoList.add(dto);

        }
        return dtoList;
    }
}
