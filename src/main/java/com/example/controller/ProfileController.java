package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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
    @GetMapping(value = "/page")
    ResponseEntity<PageImpl<ProfileDTO>> pageResponseEntity(@RequestParam(value = "size",defaultValue = "10")int size,
                                                            @RequestParam(value = "page",defaultValue = "1") int page){
    PageImpl<ProfileDTO> response = profileService.profilePage(size,page);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping(value = "/delete")
    ResponseEntity<Boolean>delete(@PathVariable("id") UUID id){
        return ResponseEntity.ok(profileService.delete(id));
    }
    @PostMapping(value = "/filter")
    ResponseEntity<ProfileFilterDTO> filter(@RequestBody ProfileFilterDTO filterDTO){

        return ResponseEntity.ok((ProfileFilterDTO) profileService.filter(filterDTO));
    }


}
