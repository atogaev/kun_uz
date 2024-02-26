package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.JwtDTO;
import com.example.enums.LanguageEnum;
import com.example.enums.ProfileRoleEnum;
import com.example.mapper.LanguageMapper;
import com.example.service.CategoryService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = {"/admin","/admin/"})
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto,
                                              HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(categoryService.create(dto,jwtDTO.getId()));
    }
    @PutMapping(value = "/admin/update/{id}")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto,
                                              @PathVariable Integer id,
                                              HttpServletRequest request){
        SecurityUtil.hasRole(request,ProfileRoleEnum.ROLE_ADMIN,ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(categoryService.update(dto,id));
    }
    @DeleteMapping(value = "/admin/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id,
                                          HttpServletRequest request){
        SecurityUtil.hasRole(request,ProfileRoleEnum.ROLE_ADMIN);
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
    @GetMapping(value = "/admin/getByOrderNum")
    public ResponseEntity<List<CategoryDTO>> getAll(HttpServletRequest request){
        SecurityUtil.hasRole(request,ProfileRoleEnum.ROLE_ADMIN,ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping(value = "/lang")
    public ResponseEntity<List<LanguageMapper>> GetByLang(@RequestParam(value = "lang")String lang){
        return ResponseEntity.ok(categoryService.getByLanguage(lang));
    }
}
