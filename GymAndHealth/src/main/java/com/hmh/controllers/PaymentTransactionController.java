/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.PaymentTransaction;
import com.hmh.services.PaymentTransactionService;
import com.hmh.services.UserService;
import com.hmh.services.TrainingPackageService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@Controller
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainingPackageService trainingPackageService;

    @GetMapping("/payment-transactions")
    public String listTransactions(@RequestParam(required = false) Map<String, String> params, Model model) {
        List<PaymentTransaction> transactions = paymentTransactionService.getTransactions(params);
        model.addAttribute("transactions", transactions);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("packages", trainingPackageService.getTrainingPackages(null));

        Double total = paymentTransactionService.getTotalRevenue();
        model.addAttribute("totalRevenue", total);

        return "paymenttransaction";
    }
}
