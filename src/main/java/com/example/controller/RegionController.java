package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.enums.Language;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping(value = {"/admin","/admin/"})
    ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO){
        return ResponseEntity.ok(regionService.create(regionDTO));
    }
    @PutMapping(value = "/admin/update/{id}")
    ResponseEntity<Boolean> updateById(@PathVariable("id") Integer id,
                                 @RequestBody RegionDTO regionDTO){
        return ResponseEntity.ok(regionService.updateById(id,regionDTO));
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    ResponseEntity<?> deleteById(@PathVariable("id") UUID id){
        Boolean result = regionService.delete(id);
        if (result){
            return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not faund");
    }

    @GetMapping("/admin/all")
    ResponseEntity<List<RegionDTO>> getAll(){
        return ResponseEntity.ok(regionService.get());
    }

   //  TODO

    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>>  getBylanguage(@RequestParam(value = "lang",
                                            defaultValue = "uz") Language lang) {
        return ResponseEntity.ok(regionService.getByLanguageVisible(lang));
    }

}
