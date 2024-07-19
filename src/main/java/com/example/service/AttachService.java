package com.example.service;

import com.example.dto.AttachDTO;
import com.example.entity.AttachEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;
    @Value("${attaches.folder.name}")
    private String folderName;
    @Value("${attach.url}")
    private String url;
   /* public String saveToSystem(MultipartFile file) {

        try {
            File myFile = new File("attach");
            if (!myFile.exists()) {
                myFile.mkdir();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get("attach/" + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }*/
    public AttachDTO save(MultipartFile file) {
        String pathFolder = getYmDString();

        File myFile = new File(folderName + "/" + pathFolder);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        String key = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folderName + "/" + pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(key);
            entity.setSize(file.getSize());
            entity.setOriginal_name(file.getOriginalFilename());
            entity.setExtension(extension);
            entity.setPath(pathFolder);
            attachRepository.save(entity);

            AttachDTO toDTO = new AttachDTO();
            toDTO.setId(entity.getId());
            toDTO.setOriginal_name(entity.getOriginal_name());
            toDTO.setPath(entity.getPath());
            toDTO.setUrl(getUrl(entity.getId()));
            return toDTO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public AttachDTO upload(MultipartFile file,String id){
        String type = getExtension(Objects.requireNonNull(file.getOriginalFilename()));
        var entity = get(id);
        entity.setSize(file.getSize());
        entity.setOriginal_name(file.getOriginalFilename());
        entity.setPath(entity.getPath());
        entity.setExtension(type);
return null;
    }
    public AttachDTO getAttachWithUrl(String id){
        if (id==null) throw new ItemNotFoundException("Image not found");
        AttachDTO img = new AttachDTO();
        var entity = get(id);
        img.setId(id);
        img.setUrl(getUrl(id));
        return img;
    }
    //    public byte[] loadImage(String fileName) {
//        try {
//            BufferedImage originalImage = ImageIO.read(new File("attach/" + fileName));
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(originalImage, "jpg", baos);
//
//            baos.flush();
//            byte[] imageInByte = baos.toByteArray();
//            baos.close();
//            return imageInByte;
//        } catch (Exception e) {
//            return new byte[0];
//        }
//    }
    public byte[] loadImage(String id) {
        AttachEntity entity = get(id);
        try {
            String url = folderName + "/" + entity.getPath() + "/" + id + "." + entity.getExtension();
            BufferedImage originalImage = ImageIO.read(new File(url));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage,entity.getExtension(),baos);

            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            return new byte[0];
        }
    }
    public String getUrl(String id){
        return url+"/open/"+id;
    }
    public byte[] loadByIdGeneral(String id) {
        AttachEntity entity = get(id);
        try {
            String url = folderName + "/" + entity.getPath() + "/" + id + "." + entity.getExtension();
            File file = new File(url);

            byte[] bytes = new byte[(int) file.length()];

            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
    public Boolean deleteById(String  id) {
        boolean t = false;
       var entity = get(id);
        try {
            if (Files.deleteIfExists(new File(folderName+"/"+entity.getPath() + "/" + id + "." + entity.getExtension()).toPath())) {
                attachRepository.deleteById(id);
                t = true;
            }
             return t;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("File not found"));
    }
    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }
    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        return year + "/" + month + "/" + day; // 2022/04/23
    }
}
