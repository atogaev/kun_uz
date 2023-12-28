package com.example.service;

import com.example.dto.RegionDTO;
import com.example.enums.Language;
import com.example.entity.RegionEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO regionDTO) {
        isValid(regionDTO);

        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(regionDTO.getOrderNumber());
        entity.setNameEn(regionDTO.getNameEn());
        entity.setNameUz(regionDTO.getNameUz());
        entity.setNameRu(regionDTO.getNameRu());
        regionRepository.save(entity);
        regionDTO.setId(entity.getId());
        regionDTO.setCreatedDate(entity.getCreatedDate());
        return regionDTO;
    }
    public Boolean updateById(Integer id, RegionDTO dto){
        int effect = regionRepository.updateById(id, dto.getOrderNumber(), dto.getNameUz(), dto.getNameRu(), dto.getNameEn());
        return effect == 1;
    }
    public List<RegionDTO> get() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
    public Boolean delete(UUID id){
        int eff = regionRepository.delete(id);
        return eff == 1;
    }
    private void isValid(RegionDTO regionDTO) {
        if (regionDTO.getOrderNumber() == null) {
            throw new AppBadRequestException("OrderNumber is null");
        }
        if (regionDTO.getNameEn() == null || regionDTO.getNameEn().isBlank() || regionDTO.getNameEn().length() < 3) {
            throw new AppBadRequestException("Name_en is wrong");
        }
        if (regionDTO.getNameUz() == null || regionDTO.getNameUz().isBlank() || regionDTO.getNameUz().length() < 3) {
            throw new AppBadRequestException("Name_uz is wrong");
        }
        if (regionDTO.getNameRu() == null || regionDTO.getNameRu().isBlank() || regionDTO.getNameRu().length() < 3) {
            throw new AppBadRequestException("Name_ru is wrong");
        }
    }

//    public Boolean deleteById(UUID id) {
//        regionRepository.deleteById(id);
//        return null;
//    }

    public List<RegionDTO> getAll() {
       Iterable<RegionEntity> entities = regionRepository.findAll();

        return null;
    }

    private RegionDTO toDTO(RegionEntity entity){
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameEn(entity.getNameEn());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public List<RegionDTO> getByLanguageVisible(Language lang) {
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByVisibleTrue().forEach(entity -> {dtoList.add(toDTO2(entity,lang));});

        return dtoList;
    }

    private RegionDTO toDTO2(RegionEntity entity, Language lang) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        switch (lang){
            case En -> dto.setName(entity.getNameEn());
            case Ru -> dto.setNameRu(entity.getNameRu());
            case Uz -> dto.setNameUz(entity.getNameUz());
            default -> dto.setName(entity.getNameUz());
        }
        return dto;
    }
}
