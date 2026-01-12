/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TrainingProgress;
import com.hmh.services.TrainingProgressService;
import com.hmh.services.UserService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@Controller
public class TrainingProgressController {

    @Autowired
    private TrainingProgressService trainingProgressService;

    @Autowired
    private UserService userService;

    @GetMapping("/training-progress/add")
    public String addView(Model model) {
        model.addAttribute("trainingProgress", new TrainingProgress());
        model.addAttribute("users", userService.getAllUsers());
        return "addtrainingprogress";
    }

    @PostMapping("/training-progress/add")
    public String addProgress(@ModelAttribute TrainingProgress progress, Model model) {
        try {
            progress.setDate(new Date());
            trainingProgressService.addProgress(progress);
            return "redirect:/training-progress";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("users", userService.getAllUsers());
            return "addtrainingprogress";
        }
    }
}
