package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "")
    ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.create(dto));
    }
    @PutMapping(value = "/{id}")
    ResponseEntity<Boolean> update(@RequestBody ProfileDTO dto,
                                   @PathVariable("id") UUID id) {
        return ResponseEntity.ok(profileService.update(id,dto));
    }@PutMapping(value = "/detail/{id}")
    ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                   @PathVariable("id") UUID id) {
        return ResponseEntity.ok(profileService.detailUpdate(id,dto));
    }


}
