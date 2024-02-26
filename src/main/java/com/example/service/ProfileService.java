package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatusEnum;
import com.example.exp.AppBadRequestException;
import com.example.repository.CustomProfileRepository;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
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
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CustomProfileRepository customProfileRepository;

    public ProfileDTO create(ProfileDTO dto,Long id) {

        isValid(dto);
        Optional<ProfileEntity> findByEmile = profileRepository.findByEmail(dto.getEmail());
        if (findByEmile.isPresent()) {
            throw new AppBadRequestException("This phone is already exist");
        }
        Optional<ProfileEntity> getByPhone = profileRepository.findByPhone(dto.getPhone());
        if (getByPhone.isPresent()) {
            throw new AppBadRequestException("This phone is already exist");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setStatus(ProfileStatusEnum.ACTIVE);
        entity.setRole(dto.getRole());
        entity.setPrtId(id);

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public Boolean update(Long profileId, ProfileDTO dto) {
        isValid(dto);
        ProfileEntity entity = get(profileId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        profileRepository.save(entity);
        return true;
    }

    public Boolean detailUpdate(Long profileId, ProfileDTO dto) {
        isValid2(dto);
        int effectedRows = profileRepository.detailUpdate(profileId, dto.getName(), dto.getSurname());
        return effectedRows == 1;
    }

    public PageImpl<ProfileDTO> profilePage(int size,int page){
        System.out.println(page +"  "+ size);
        Pageable pageable = PageRequest.of(page-1,size);
        Page<ProfileEntity> profileObg = profileRepository.findAll(pageable);
        List<ProfileEntity> entities = profileObg.getContent();

        List<ProfileDTO> profileDTOList = new LinkedList<>();
        entities.forEach(entity -> profileDTOList.add(toDTO(entity)));
        return new PageImpl<>(profileDTOList,pageable,profileObg.getTotalElements());
    }
    public Boolean delete(Long id){
        profileRepository.deleteById(id);
        return true;
    }
    public List<ProfileDTO> filter(ProfileFilterDTO filterDTO){
         List<ProfileEntity> entities = customProfileRepository.filter(filterDTO);
         List<ProfileDTO> dtoList = new LinkedList<>();
         entities.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ProfileEntity get(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> new AppBadRequestException("Profilr is not founded"));
    }
    private void isValid2(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getName().length() < 2){
            throw new AppBadRequestException("Name is not valid");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank() || dto.getSurname().length() < 2){
            throw new AppBadRequestException("Surnamer is not valid");
        }
    }

    void isValid(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getName().length() < 3) {
            throw new AppBadRequestException("This name is invald");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank() || dto.getSurname().length() < 3) {
            throw new AppBadRequestException("This Surname is Invald");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank() || dto.getPassword().length() < 8) {
            throw new AppBadRequestException("Password in not available");
        } else {
            for (int i = 0; i < dto.getPassword().length(); i++) {
                char c = dto.getPassword().charAt(i);
                if (Character.isLetter(c) || Character.isDigit(c)) {
                    System.out.println("Password true");
                }
            }
        }
        if (dto.getPhone() == null || dto.getPhone().isBlank() || dto.getPhone().length() != 13) {
            throw new AppBadRequestException("This phone is not available");
        } else {
            for (int i = 1; i < dto.getPhone().length(); i++) {
                char c = dto.getPhone().charAt(i);
                if (Character.isDigit(c)) {
                    System.out.println("Phone true");
                }
            }
        }
    }
    public ProfileDTO toDTO(ProfileEntity entity){
        ProfileDTO pDTO = new ProfileDTO();
        pDTO.setId(entity.getId());
        pDTO.setName(entity.getName());
        pDTO.setSurname(entity.getSurname());
        pDTO.setEmail(entity.getEmail());
        pDTO.setPassword(entity.getPassword());
        pDTO.setPhone(entity.getPhone());
        pDTO.setRole(entity.getRole());
        pDTO.setCreated_date(entity.getCreated_date());
        return pDTO;
    }
}
