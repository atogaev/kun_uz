package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadRequestException;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    /**
     * 1
     */
    public ProfileDTO create(ProfileDTO dto) {

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
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRole());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public Boolean update(UUID profileId, ProfileDTO dto) {
        isValid(dto);
        ProfileEntity entity = get(profileId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        profileRepository.save(entity);
        return true;
    }

    public Boolean detailUpdate(UUID profileId, ProfileDTO dto) {
        isValid2(dto);
        ProfileEntity entity = get(profileId);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        profileRepository.save(entity);
        int effectedRows = profileRepository.detailUpdate(profileId, dto.getName(), dto.getSurname());
        return effectedRows == 1;
    }

    private void isValid2(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank() || dto.getName().length() < 2){
            throw new AppBadRequestException("Name is not valid");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank() || dto.getSurname().length() < 2){
            throw new AppBadRequestException("Surnamer is not valid");
        }
    }

    public ProfileEntity get(UUID profileId) {
        return profileRepository.findById(profileId).orElseThrow(() -> new AppBadRequestException("Profilr is not founded"));
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
}
