/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.Feedback;
import com.hmh.services.FeedbackService;
import com.hmh.services.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hieph
 */
@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @GetMapping("/feedback-add-view")
    public String addFeedbackView(Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("users", userService.getAllUsers()); 
        return "addfeedback";
    }

    @PostMapping("/feedbacks/add")
    public String addFeedback(@ModelAttribute("feedback") Feedback feedback, Model model) {
        try {
            feedback.setCreatedAt(new Date());
            feedbackService.addFeedback(feedback);
            return "redirect:/feedbacks";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("users", userService.getAllUsers());
            return "addfeedback";
        }
    }

    @GetMapping("/feedbacks/average-rating/{targetId}")
    public String getAverageRating(@PathVariable("targetId") int targetId, Model model) {
        Double avg = feedbackService.getAverageRatingByTarget(targetId);
        model.addAttribute("averageRating", avg);
        return "feedback";
    }
}
