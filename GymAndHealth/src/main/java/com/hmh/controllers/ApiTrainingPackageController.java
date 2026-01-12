/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TrainingPackage;
import com.hmh.services.TrainingPackageService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hieph
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTrainingPackageController {

    @Autowired
    private TrainingPackageService trainingPackageService;

    @GetMapping("/secure/training-packages")
    public ResponseEntity<List<TrainingPackage>> getPackages(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(trainingPackageService.getTrainingPackages(params));
    }

    @GetMapping("/secure/training-packages/{id}")
    public ResponseEntity<TrainingPackage> getById(@PathVariable("id") int id) {
        TrainingPackage p = trainingPackageService.getPackageById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/secure/training-packages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestParam Map<String, String> params,
                                    @RequestParam("image") MultipartFile image) {
        try {
            TrainingPackage newPkg = trainingPackageService.addOrUpdateTrainingPackage(params, image);
            return new ResponseEntity<>(newPkg, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PutMapping(value = "/secure/training-packages/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") int id,
                                    @RequestParam Map<String, String> params,
                                    @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            params.put("id", String.valueOf(id));
            TrainingPackage updated = trainingPackageService.addOrUpdateTrainingPackage(params, image);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/secure/training-packages/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (trainingPackageService.deleteTrainingPackage(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}