package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.entity.CategoryEntity;
import com.example.exp.AppBadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.mapper.LanguageI;
import com.example.mapper.LanguageMapper;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDTO create(CategoryDTO dto, Long id) {

        CategoryEntity entity = new CategoryEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setPrtId(id);
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());
        return toDTO(entity);
    }
    public CategoryDTO update(CategoryDTO dto, Integer id) {
//TODO check
        CategoryEntity entity = get(id);
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        categoryRepository.save(entity);
        return toDTO(entity);
    }
    public Boolean deleteById(Integer id){
        categoryRepository.deleteById(id);
        return true;
    }
    public CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new AppBadRequestException("Profilr is not founded"));
    }
    public List<CategoryDTO> getAll() {
        List<CategoryEntity> entityList = categoryRepository.findAll();
        if (entityList.isEmpty()){
            throw new ItemNotFoundException("Category not found");
        }
        List<CategoryDTO> dtoList= new LinkedList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }

    public List<LanguageMapper> getByLanguage(String lang) {
        List<LanguageI> list = categoryRepository.getCategoryByLanguage(lang);
        List<LanguageMapper> mapperList = new ArrayList<>();
        list.forEach(entity -> {
            LanguageMapper mapper = new LanguageMapper();
            mapper.setId(entity.getId());
            mapper.setOrderNumber(entity.getOrder_number());
            mapper.setName(entity.getName());
            mapperList.add(mapper);
        });
        return mapperList;    }
    private CategoryDTO toDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        dto.setName_en(entity.getName_en());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setCreated_date(entity.getCreated_date());
        dto.setVisible(entity.getVisible());
        return dto;
    }
}
