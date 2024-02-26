package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachService {

    public String saveToSystem(MultipartFile file) {

        try {
        File myFile = new File("attach");
        if (!myFile.exists()){
            myFile.mkdir();
        }
        byte[] bytes = file.getBytes();
            Path path = Paths.get("attach/"+file.getOriginalFilename());
            Files.write(path,bytes);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public byte[] loadImage(String fileName) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("attach/" + fileName));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);

            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
