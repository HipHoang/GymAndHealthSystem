/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.UserPackage;
import com.hmh.services.TrainingPackageService;
import com.hmh.services.UserPackageService;
import com.hmh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@Controller
public class UserPackageController {

    @Autowired
    private UserPackageService userPackageService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainingPackageService trainingPackageService;

    @GetMapping("/user-packages/add")
    public String addUserPackageView(Model model) {
        model.addAttribute("userPackage", new UserPackage());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("packages", trainingPackageService.getTrainingPackages(null));
        return "adduserpackage";
    }

    @PostMapping("/user-packages/add")
    public String addUserPackage(@ModelAttribute UserPackage up, Model model) {
        try {
            userPackageService.addOrUpdateUserPackage(up);
            return "redirect:/user-packages";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("packages", trainingPackageService.getTrainingPackages(null));
            return "adduserpackage";
        }
    }

    @GetMapping("/user-packages/delete/{id}")
    public String deleteUserPackage(@PathVariable("id") int id) {
        userPackageService.deleteUserPackage(id);
        return "redirect:/user-packages";
    }
}