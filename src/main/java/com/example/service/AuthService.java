package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;

    public ApiResponseDTO login(AuthDTO dto){
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndPassword(dto.getPhone(), MD5Util.encode(dto.getPassword()));
        ProfileEntity entity = optional.get();
        if (optional.isEmpty() || (!entity.getPassword().equals(MD5Util.encode(dto.getPassword()))) ){
            return new ApiResponseDTO(false,"Login or Password is wrong");
        }
        if (!entity.getStatus().equals(ProfileStatus.ACTIVE) || !entity.getVisible()){
            return new ApiResponseDTO(false,"Your status is not active. Please connect with support");
        }

        ProfileDTO response = new ProfileDTO();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setSurname(entity.getSurname());
        response.setStatus(entity.getStatus());
        response.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
        return new ApiResponseDTO(true,response);
    }
}
