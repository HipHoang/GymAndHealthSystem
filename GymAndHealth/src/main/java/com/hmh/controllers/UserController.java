/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.User;
import com.hmh.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hieph
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/user-add-view")
    public String addView(Model model) {
        model.addAttribute("users", new User());
        return "adduser";
    }

    @PostMapping(path = "/users/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)
    public String addUser(@RequestParam Map<String, String> params,
            @RequestParam("avatar") MultipartFile avatar,
            Model model) {

        try {
            userService.addUser(params, avatar);
            return "redirect:/users";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "adduser";
        }
    }

    @GetMapping("/users/{id}")
    public String updateView(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "updateuser"; 
        }
        return "redirect:/users";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") int id,
            @RequestParam Map<String, String> params,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar, Model model) {
        try {
            userService.updateUser(params, avatar, id);
            return "redirect:/users";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage()); 
            return "adduser"; 
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
}
