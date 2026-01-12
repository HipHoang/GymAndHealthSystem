/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.UserPackage;
import com.hmh.services.UserPackageService;
import java.util.*;
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
public class ApiUserPackageController {

    @Autowired
    private UserPackageService userPackageService;

    @GetMapping("/secure/user-packages")
    public ResponseEntity<List<UserPackage>> getUserPackages(@RequestParam Map<String, String> params) {
        List<UserPackage> result = userPackageService.getUserPackages(params);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/secure/user-packages")
    public ResponseEntity<?> createUserPackage(@RequestBody UserPackage up) {
        try {
            UserPackage saved = userPackageService.addOrUpdateUserPackage(up);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @DeleteMapping("/secure/user-packages/{id}")
    public ResponseEntity<?> deleteUserPackage(@PathVariable("id") int id) {
        boolean success = userPackageService.deleteUserPackage(id);
        if (success)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy gói người dùng để xóa");
    }
}