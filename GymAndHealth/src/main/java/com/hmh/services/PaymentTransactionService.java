/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import com.hmh.pojo.PaymentTransaction;
import java.util.List;
import java.util.Map;
/**
 *
 * @author hieph
 */

public interface PaymentTransactionService {
    PaymentTransaction addTransaction(PaymentTransaction t);
    List<PaymentTransaction> getTransactions(Map<String, String> params);
    Double getTotalRevenue();
}