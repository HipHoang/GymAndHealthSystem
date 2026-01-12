package com.hmh.controllers;

import com.hmh.pojo.User;
import com.hmh.services.UserService;
import com.hmh.services.PaymentTransactionService;
import com.hmh.services.UserPackageService;
import com.hmh.services.BookingScheduleService;
import com.hmh.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiStatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private UserService userService;

    public ApiStatisticsController(StatisticsService statisticsService, UserService userService) {
        this.statisticsService = statisticsService;
        this.userService = userService;
    }

    @GetMapping("/secure/stats/week")
    public ResponseEntity<List<Object[]>> statsByWeek(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Principal principal) {

        User currentUser = userService.getUserByUsername(principal.getName());
            return ResponseEntity.ok(statisticsService.statsByWeek(currentUser.getId(), startDate, endDate));
    }

    @GetMapping("/secure/stats/month")
    public ResponseEntity<List<Object[]>> statsByMonth(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Principal principal) {

        User currentUser = userService.getUserByUsername(principal.getName());

        if (currentUser.getUserRole().equals("TRAINER")) {
            List<Object[]> results = new ArrayList<>();
            return ResponseEntity.ok(results);
        } else {
            return ResponseEntity.ok(statisticsService.statsByMonth(currentUser.getId(), startDate, endDate));
        }
    }
}