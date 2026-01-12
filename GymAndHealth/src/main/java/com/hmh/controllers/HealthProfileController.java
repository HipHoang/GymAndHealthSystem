/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.HealthProfile;
import com.hmh.services.HealthProfileService;
import com.hmh.services.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@Controller
public class HealthProfileController {

    @Autowired
    private HealthProfileService healthProfileService;

    @Autowired
    private UserService userService;

    @GetMapping("/health-profiles")
    public String addView(Model model) {
        model.addAttribute("healthProfile", new HealthProfile());
        model.addAttribute("users", userService.getAllUsers());
        return "healthprofiles";
    }
      
    @PostMapping("/health-profile/add")
    public String addHealthProfile(@ModelAttribute("healthProfile") HealthProfile hp, Model model) {
        try {
            hp.setUpdatedAt(new Date());
            healthProfileService.saveHealthProfile(hp);
            return "redirect:/";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("users", userService.getAllUsers());
            return "healthprofiles";
        }
    }

    
    @GetMapping("/health-profiles/{id}")
    public String updateView(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("healthProfile", this.healthProfileService.getHealthProfileById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "healthprofiles";
    }
}