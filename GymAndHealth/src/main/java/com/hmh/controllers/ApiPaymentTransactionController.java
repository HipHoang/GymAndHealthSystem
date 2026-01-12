/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.PaymentTransaction;
import com.hmh.pojo.User;
import com.hmh.services.PaymentTransactionService;
import com.hmh.services.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
public class ApiPaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/secure/transactions")
    public ResponseEntity<?> addTransaction(@RequestBody PaymentTransaction t, Principal principal) {
        String username = principal.getName();
        User u = userService.getUserByUsername(username);
        if (u == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

        t.setUserId(u);
        t.setCreatedAt(new Date());

        PaymentTransaction saved = paymentTransactionService.addTransaction(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/secure/transactions")
    public ResponseEntity<List<PaymentTransaction>> getTransactions(
            @RequestParam(required = false) Map<String, String> params) {
        return ResponseEntity.ok(paymentTransactionService.getTransactions(params));
    }

    @GetMapping("/secure/transactions/total-revenue")
    public ResponseEntity<Map<String, Double>> getTotalRevenue() {
        Double total = paymentTransactionService.getTotalRevenue();
        return ResponseEntity.ok(Map.of("totalRevenue", total));
    }
}
