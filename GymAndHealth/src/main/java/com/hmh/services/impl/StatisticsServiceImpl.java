/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.services.impl;

import com.hmh.repositories.StatisticsRepository;
import com.hmh.services.StatisticsService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hieph
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statsRepo;

    public StatisticsServiceImpl(StatisticsRepository statsRepo) {
        this.statsRepo = statsRepo;
    }

    @Override
    public List<Object[]> statsByWeek(int userId, LocalDate startDate, LocalDate endDate) {
        return statsRepo.statsByWeek(userId, startDate, endDate);
    }

    @Override
    public List<Object[]> statsByMonth(int userId, LocalDate startDate, LocalDate endDate) {
        return statsRepo.statsByMonth(userId, startDate, endDate);
    }
}