package com.example.controller;

import com.example.dto.AttachDTO;
import com.example.dto.JwtDTO;
import com.example.enums.ProfileRoleEnum;
import com.example.service.AttachService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AttachDTO> upload(@RequestParam("file")MultipartFile file ){
        return ResponseEntity.ok().body(attachService.save(file));
    }
   /* @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] openImageById(@PathVariable("fileName") String fileName) {
        return attachService.loadImage(fileName);
    }*/
    @GetMapping(value = "/open/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] open(@PathVariable("id")String id){
        return attachService.loadImage(id);
    }
    @GetMapping(value = "/open/{id}/general", produces = MediaType.ALL_VALUE)
    public byte[] openByIdGeneral(@PathVariable("id") String id) {
        return attachService.loadByIdGeneral(id);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id")String id, HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRoleEnum.ROLE_ADMIN);
        return ResponseEntity.ok(attachService.deleteById(id));
    }

}
