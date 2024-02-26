package com.example.controller;

import com.example.service.AttachService;
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
    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam("file")MultipartFile file ){
        String fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }
    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] openImageById(@PathVariable("fileName") String fileName) {
        return attachService.loadImage(fileName);
    }
}
