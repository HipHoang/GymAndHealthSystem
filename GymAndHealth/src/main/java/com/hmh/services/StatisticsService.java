/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author hieph
 */
public interface StatisticsService {
    List<Object[]> statsByWeek(int userId, LocalDate startDate, LocalDate endDate);
    List<Object[]> statsByMonth(int userId, LocalDate startDate, LocalDate endDate);
} 