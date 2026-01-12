/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.pojo.PaymentTransaction;
import com.hmh.repositories.PaymentTransactionRepository;
import com.hmh.services.PaymentTransactionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author hieph
 */
@Service
@Transactional
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Override
    public PaymentTransaction addTransaction(PaymentTransaction t) {
        return this.paymentTransactionRepository.addTransaction(t);
    }

    @Override
    public List<PaymentTransaction> getTransactions(Map<String, String> params) {
        return this.paymentTransactionRepository.getTransactions(params);
    }

    @Override
    public Double getTotalRevenue() {
        return this.paymentTransactionRepository.getTotalRevenue();
    }
}