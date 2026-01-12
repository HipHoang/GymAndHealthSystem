/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TrainingPackage;
import com.hmh.services.TrainingPackageService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hieph
 */
@Controller
public class TrainingPackageController {

    @Autowired
    private TrainingPackageService trainingPackageService;
   
    @GetMapping("/training-packages-add-view")
    public String addView(Model model) {
        model.addAttribute("trainingPackage", new TrainingPackage());
        return "addtrainingpackage";
    }

    @PostMapping("/training-packages/add")
    public String addPackage(@RequestParam Map<String, String> params,
            @RequestParam("image") MultipartFile image,
            Model model) {
        try {
            trainingPackageService.addOrUpdateTrainingPackage(params, image);
            return "redirect:/training-packages";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("trainingPackage", new TrainingPackage());
            return "addtrainingpackage";
        }
    }

  
    @GetMapping("/training-packages/{id}")
    public String updateView(Model model, @PathVariable("id") int id) {
        TrainingPackage tp = trainingPackageService.getPackageById(id);
        if (tp == null) {
            return "redirect:/training-packages";
        }

        model.addAttribute("trainingPackage", tp);
        return "addtrainingpackage";
    }

  
    @PostMapping("/training-packages/{id}")
    public String updatePackage(@PathVariable("id") int id,
            @RequestParam Map<String, String> params,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Model model) {
        try {
            params.put("id", String.valueOf(id));
            trainingPackageService.addOrUpdateTrainingPackage(params, image);
            return "redirect:/training-packages";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("trainingPackage", trainingPackageService.getPackageById(id));
            return "addtrainingpackage";
        }
    }

    @DeleteMapping("/training-packages/delete/{id}")
    @ResponseBody
    public String deletePackage(@PathVariable("id") int id) {
        return trainingPackageService.deleteTrainingPackage(id) ? "OK" : "FAIL";
    }
}
