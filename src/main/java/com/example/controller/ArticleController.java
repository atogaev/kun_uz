package com.example.controller;

import com.example.dto.ApiResponseDTO;
import com.example.dto.ArticleDTO;
import com.example.dto.AuthDTO;
import com.example.dto.JwtDTO;
import com.example.enums.ProfileRoleEnum;
import com.example.service.ArticleService;
import com.example.service.AuthService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/article")
public class ArticleController {
    @Autowired
   private ArticleService articleService;

    @PostMapping(value = {"/moderator","/moderator/"})
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO dto,
                                             HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,ProfileRoleEnum.ROLE_ADMIN);
        return ResponseEntity.ok(articleService.create(dto,jwtDTO.getId()));
    }
}
