/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.User;
import com.hmh.services.BookingScheduleService;
import com.hmh.services.FeedbackService;
import com.hmh.services.HealthProfileService;
import com.hmh.services.TrainingPackageService;
import com.hmh.services.TrainingProgressService;
import com.hmh.services.UserPackageService;
import com.hmh.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 *
 * @author hieph
 */
@Controller
public class IndexController {

    @Autowired
    private HealthProfileService healthProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainingPackageService trainingPackageService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserPackageService userPackageService;

    @Autowired
    private BookingScheduleService bookingScheduleService;

    @Autowired
    private TrainingProgressService trainingProgressService;

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("healthProfiles", healthProfileService.getHealthProfiles(params));
        return "index";
    }

    @GetMapping("/users")
    public String userList(@RequestParam(name = "username", required = false) String username, Model model) {
        List<User> users;
        if (username != null && !username.isEmpty()) {
            users = userService.searchUsersByUsername(username);
        } else {
            users = userService.getAllUsers();
        }
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/training-packages")
    public String trainingPackageList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("trainingPackages", this.trainingPackageService.getTrainingPackages(params));
        return "trainingpackage";
    }

    @GetMapping("/feedbacks")
    public String feedbackList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("feedbacks", this.feedbackService.getFeedbacks(params));
        return "feedback";
    }

    @GetMapping("/user-packages")
    public String userPackageList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("userPackages", this.userPackageService.getUserPackages(params));
        return "userpackage";
    }

    @GetMapping("/booking-schedules")
    public String bookingList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("bookings", this.bookingScheduleService.getBookingSchedules(params));
        return "bookingschedule";
    }

    @GetMapping("/training-progress")
    public String trainingProgressList(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("progresses", this.trainingProgressService.getProgress(params));
        return "trainingprogress";
    }

}
