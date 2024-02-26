package com.example.controller;

import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.enums.ProfileRoleEnum;
import com.example.service.ProfileService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping(value = {"", "/"})
    ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @PutMapping(value = "/update/{id}")
    ResponseEntity<Boolean> update(@RequestBody ProfileDTO dto,
                                   @PathVariable("id") Long id,
                                   HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @PutMapping(value = "/detail")
    ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                         HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(profileService.detailUpdate(jwtDTO.getId(),dto));
    }

    @GetMapping(value = "/page")
    ResponseEntity<PageImpl<ProfileDTO>> pageResponseEntity(@RequestParam(value = "size", defaultValue = "10") int size,
                                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                                            HttpServletRequest request) {
        SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        PageImpl<ProfileDTO> response = profileService.profilePage(size, page);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id,
                                   HttpServletRequest request) {
        SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(profileService.delete(id));
    }

    @PostMapping(value = "/filter")
    ResponseEntity<ProfileDTO> filter(@RequestBody ProfileFilterDTO filterDTO,
                                      HttpServletRequest request) {
        SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok((ProfileDTO) profileService.filter(filterDTO));
    }


}
