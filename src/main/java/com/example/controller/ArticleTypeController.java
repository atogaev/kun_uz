package com.example.controller;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.JwtDTO;
import com.example.enums.LanguageEnum;
import com.example.enums.ProfileRoleEnum;
import com.example.service.ArticleTypeService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping(value = {"","/"})
    ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                          HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(articleTypeService.create(dto,jwtDTO.getId()));
    }
    @PutMapping(value = "/update/{id}")
    ResponseEntity<ArticleTypeDTO> update(@RequestBody ArticleTypeDTO dto,
                                          @PathVariable Integer id,
                                          HttpServletRequest request){
        SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_MODERATOR, ProfileRoleEnum.ROLE_ADMIN);
        return ResponseEntity.ok(articleTypeService.update(dto,id));
    }
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Integer id,
                                   HttpServletRequest request){
        SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN, ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(articleTypeService.delete(id));
    }
    @GetMapping(value = "/pegination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>> filter(@RequestParam(value = "page",defaultValue = "1") int page,
                                                           @RequestParam(value = "size",defaultValue = "10") int size,
                                                           HttpServletRequest request){
        SecurityUtil.hasRole(request,ProfileRoleEnum.ROLE_ADMIN,ProfileRoleEnum.ROLE_MODERATOR);
        return ResponseEntity.ok(articleTypeService.pageable(page - 1,size));
    }
    @GetMapping(value = "/getByLang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLang(@RequestParam(value = "lang")LanguageEnum languageEnum){
        return ResponseEntity.ok(articleTypeService.getByLang(languageEnum));
    }

}
