/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TrainingProgress;
import com.hmh.services.TrainingProgressService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTrainingProgressController {

    @Autowired
    private TrainingProgressService trainingProgressService;

    @GetMapping("/secure/training-progress")
    public ResponseEntity<List<TrainingProgress>> getProgress(@RequestParam Map<String, String> params) {
        List<TrainingProgress> progressList = trainingProgressService.getProgress(params);
        return ResponseEntity.ok(progressList);
    }

    @PostMapping("/secure/training-progress")
    public ResponseEntity<?> addProgress(@RequestBody TrainingProgress p) {
        try {
            TrainingProgress saved = trainingProgressService.addProgress(p);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }
}